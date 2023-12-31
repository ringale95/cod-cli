package me.raveenaingale.manager;

import me.raveenaingale.model.Outcome;
import org.apache.commons.cli.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class OutcomeOptionManager {
    private static OutcomeOptionManager instance;
    private Map<Option, Class<? extends Outcome>> map;
    private Options options;
    private CommandLineParser parser;

    private OutcomeOptionManager() {
        this.map = new HashMap<>();
    }

    public static synchronized OutcomeOptionManager getInstance() {
        if (instance == null) {
            instance = new OutcomeOptionManager();
        }
        return instance;
    }

    public void mapOptionToOutcome(Option option, Class<? extends Outcome> outcomeClass) {
        map.put(option, outcomeClass);
    }

    public Option createOption(String longOpt, String desc) {
        Option option = Option.builder()
                .longOpt(longOpt)
                .desc(desc)
                .build();
        options.addOption(option);
        return option;
    }

    public Option createOption(String longOpt, String desc, String argName) {
        Option option = Option.builder()
                .longOpt(longOpt)
                .desc(desc)
                .hasArg()
                .argName(argName)
                .build();
        options.addOption(option);
        return option;
    }

    public void createOption(String opt, String longOpt, boolean hasArgs, String desc) {
        options.addOption(opt, longOpt, hasArgs, desc);
    }

    public void setOptions(Options options){
        this.options = options;
    }

    public void setParser(CommandLineParser parser){
        this.parser = parser;
    }

    public void findOptionAndExecuteOutcome(String[] args) {
        try {
            CommandLine cmd = parser.parse(options, args);
            Option option = findOption(cmd);
            if (option != null) {
                Class<? extends Outcome> outcomeClass = map.get(option);
                Outcome outcomeInstance = outcomeClass.getDeclaredConstructor().newInstance();
                String optionValue = (String) cmd.getParsedOptionValue(option.getLongOpt());
                System.out.println("Option Value: " + optionValue);
                outcomeInstance.outcome(optionValue);
            } else {
                System.out.println("No valid option provided.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Option findOption(CommandLine cmd){
        Option[] parsedOptions = cmd.getOptions();
        if (parsedOptions.length > 0) {
            return parsedOptions[0];  // Assuming you want the first option
        } else {
            return null;
        }
    }
}
