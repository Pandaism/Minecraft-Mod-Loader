package me.pandaism.MML.parser;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Pandaism
 * @date 1/30/2019 : 4:52 PM
 */
public class ArgumentParser {
    private Map<String, String> arguments;

    public ArgumentParser(String[] args) {
        this.arguments = new HashMap<>();

        for(int i = 0; i < args.length; i += 2) {
            this.arguments.put(args[i], args[i + 1]);
        }
    }

    public boolean hasArguments() {
        return arguments.size() > 0;
    }

    public String getValue(String arg) {
        return arguments.get(arg);
    }

    public boolean contains(String arg) {
        return arguments.containsKey(arg);
    }
}
