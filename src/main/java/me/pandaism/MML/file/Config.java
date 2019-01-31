package me.pandaism.MML.file;

import java.io.*;
import java.util.Properties;

/**
 * @author Pandaism
 * @date 1/30/2019 : 10:31 PM
 */
public class Config {
    private Properties properties;
    private final File config = new File("./loader.properties");

    public Config() {
        this.properties = new Properties();
    }

    public void create() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.config));

        this.properties.setProperty("url", "http://localhost/");
        this.properties.store(writer, "Please keep the 'http\\:' or ''https\\:'' as is while changing url.");
        writer.close();
    }

    public void load() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(this.config));
        this.properties.load(reader);
        reader.close();
    }

    public String getURL() {
        return this.properties.getProperty("url");
    }

    public boolean exists() {
        return this.config.exists();
    }
}
