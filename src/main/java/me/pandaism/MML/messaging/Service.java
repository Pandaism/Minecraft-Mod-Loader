package me.pandaism.MML.messaging;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import me.pandaism.MML.parser.json.ModList;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Set;

/**
 * @author Pandaism
 * @date 1/30/2019 : 4:51 PM
 */
public class Service {
    private String url = "http://localhost/";

    private String getJson(String key) throws IOException {
        URLConnection connection = new URL(this.url + "in.php?key=" + key + "&action=get").openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        return reader.readLine();
    }

    private void installMod(String mod, File modFolder) throws IOException {
        URL connection = new URL(this.url + "mods/" + mod);
        ReadableByteChannel readableByteChannel = Channels.newChannel(connection.openStream());
        FileOutputStream outputStream = new FileOutputStream(modFolder.getAbsolutePath() + "/" + mod);

        outputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        outputStream.close();

        System.out.printf("%s was fetched...", mod);
    }

    public void getMods(String key) throws IOException {
        final File modFolder = new File(System.getenv("APPDATA") + "/.minecraft/mods/");
        if (!modFolder.exists()) {
            if(modFolder.createNewFile()) {
                System.out.printf("Mod folder was created: %s", modFolder.getAbsolutePath());
            }
        }

        String json = getJson(key);
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<ModList> adapter = moshi.adapter(ModList.class);

        ModList modList = adapter.fromJson(json);

        if(modList != null) {
            Set<String> mods = modList.getMods();

            for(String mod : mods) {
                installMod(mod, modFolder);
            }
        }
    }
}
