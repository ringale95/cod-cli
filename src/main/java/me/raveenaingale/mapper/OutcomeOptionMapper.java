package me.raveenaingale.mapper;

import me.raveenaingale.config.Configuration;
import me.raveenaingale.manager.OutcomeOptionManager;
import me.raveenaingale.model.Outcome;
import me.raveenaingale.model.impl.ProjectOutcome;
import org.apache.commons.cli.*;

public class OutcomeOptionMapper {
    private static OutcomeOptionMapper instance;
    private Configuration configuration;
    private OutcomeOptionManager manager;
    private OutcomeOptionMapper() {
        configuration = Configuration.getInstance();
        manager = OutcomeOptionManager.getInstance();
        manager.setOptions(configuration.getOptions());
        manager.setParser(configuration.getParser());
    }
    public static synchronized OutcomeOptionMapper getInstance() {
        if (instance == null) {
            instance = new OutcomeOptionMapper();
        }
        return instance;
    }
    public void run(String args[]){
        globalOutcomeOptions();
        executeOptions(args);
    }
    private void executeOptions(String[] args) {
        manager.findOptionAndExecuteOutcome(args);
    }
    public void globalOutcomeOptions() {
        // Option 0 - Enable verbose mode
        manager.createOption("v", "verbose", false, "Enable verbose mode.");

        // Option 1 - Generate Project
        Option genPro = manager.createOption("project", "Generate projects..", "projectName");
        manager.mapOptionToOutcome(genPro, ProjectOutcome.class);
    }

}
