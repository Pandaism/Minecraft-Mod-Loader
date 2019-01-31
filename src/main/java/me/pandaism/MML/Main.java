package me.pandaism.MML;

import me.pandaism.MML.file.Config;
import me.pandaism.MML.messaging.Service;
import me.pandaism.MML.parser.ArgumentParser;

import java.io.IOException;

/**
 * @author Pandaism
 * @date 1/30/2019 : 4:51 PM
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Config config = new Config();

        if(!config.exists()) {
            config.create();
        } else {
            config.load();
        }

        Service service = new Service(config);
        ArgumentParser argumentParser = new ArgumentParser(args);

        if(argumentParser.hasArguments()) {
            String key = "-k";
            String action = "-a";
            if(argumentParser.contains(key) && argumentParser.contains(action)) {
                service.setKey(argumentParser.getValue(key));

                switch (argumentParser.getValue(action)) {
                    case "post":
                        String file = "-f";
                        if(argumentParser.contains(file)) {
                            service.postMods(argumentParser.getValue(file));
                        }
                        break;
                    default:
                        service.getMods();
                }
            }
        } else {
            service.getMods();
        }
    }
}
