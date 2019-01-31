package me.pandaism.MML;

import me.pandaism.MML.messaging.Service;
import me.pandaism.MML.parser.ArgumentParser;

import java.io.IOException;

/**
 * @author Pandaism
 * @date 1/30/2019 : 4:51 PM
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Service service = new Service();
        ArgumentParser argumentParser = new ArgumentParser(args);

        if(argumentParser.hasArguments()) {
            String key = "-k";
            String action = "-a";
            if(argumentParser.contains(key) && argumentParser.contains(action)) {
                switch (argumentParser.getValue(action)) {
                    default:
                        service.getMods(argumentParser.getValue(key));
                }
            }
        } else {
            service.getMods("");
        }
    }
}
