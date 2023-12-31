package me.raveenaingale.model.impl;

import me.raveenaingale.model.Outcome;

public class TestEnvironmentOutcome implements Outcome {
    @Override
    public void outcome(String optionValue) {
        // Connect to DB
        // Clean all tables
        // Insert data from .csv
    }
}
