package me.pandaism.MML.messaging;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import me.pandaism.MML.file.Config;
import me.pandaism.MML.parser.json.ModList;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Pandaism
 * @date 1/30/2019 : 4:51 PM
 */
public class Service {
    private String url;
    private String key;

    public Service(Config config) {
        this.key = "guest";
        this.url = config.getURL();
    }

    private String getJson() throws IOException {
        URLConnection connection = new URL(this.url + "in.php?key=" + this.key + "&action=get").openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        return reader.readLine();
    }

    private void installMods(String mod, File modFolder) throws IOException {
        URL connection = new URL(this.url + "mods/" + mod);
        ReadableByteChannel readableByteChannel = Channels.newChannel(connection.openStream());
        FileOutputStream outputStream = new FileOutputStream(modFolder.getAbsolutePath() + "/" + mod);

        outputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        outputStream.close();

        System.out.printf("%s was fetched...\n", mod);
    }

    public void getMods() throws IOException {
        final File modFolder = new File(System.getenv("APPDATA") + "/.minecraft/mods/");
        if (!modFolder.exists()) {
            if(modFolder.createNewFile()) {
                System.out.printf("Mod folder was created: %s\n", modFolder.getAbsolutePath());
            }
        }

        String json = getJson();
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<ModList> adapter = moshi.adapter(ModList.class);

        ModList modList = adapter.fromJson(json);

        if(modList != null) {
            Set<String> mods = modList.getMods();

            for(String mod : mods) {
                installMods(mod, modFolder);
            }
        }
    }

    private String checkContent(URLConnection connection) throws IOException {
        InputStream inputStream = connection.getInputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[8192];
        int length;

        while((length = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, length);
        }

        String contentType = connection.getContentType();
        String encoding = contentType.substring(contentType.indexOf("=") + 1);
        return new String(byteArrayOutputStream.toByteArray(), encoding);
    }

    private void uploadMods(String json) throws IOException {
        URL url = new URL(this.url + "in.php?key=" + this.key + "&action=post&json=" + json);
        URLConnection connection = url.openConnection();

        String content = checkContent(connection);

        if(content.equals(json)) {
            System.out.println("Mod list updated successfully.");
        } else {
            System.out.println("Mod list updated unsuccessful.");
        }

    }

    public void postMods(String fileName) throws IOException {
        File file = new File(fileName);

        if(file.exists()) {
            Set<String> mods = new HashSet<>();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while((line = reader.readLine()) != null) {
                mods.add(line);
            }

            ModList modList = new ModList(mods);
            Moshi moshi = new Moshi.Builder().build();
            JsonAdapter<ModList> adapter = moshi.adapter(ModList.class);
            String json = adapter.toJson(modList);

            uploadMods(json);
        } else {
            System.out.println("Proposed file does not exist");
        }
    }

    public void setKey(String key) {
        this.key = key;
    }
}
