package me.raveenaingale.config;

import lombok.Getter;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

public class Configuration {
    private static Configuration instance;
    @Getter
    private Options options;
    @Getter
    private CommandLineParser parser;

    private Configuration() {
        // Private constructor to prevent instantiation outside of this class
        options = new Options();
        parser = new DefaultParser();
    }

    public static synchronized Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }

}
