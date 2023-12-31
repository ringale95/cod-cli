package me.raveenaingale.model.impl;

import me.raveenaingale.helper.DirectoryMaker;
import me.raveenaingale.helper.MvcControllerMaker;
import me.raveenaingale.helper.SpringBootProjectMaker;
import me.raveenaingale.model.Outcome;

public class ProjectOutcome implements Outcome {
    @Override
    public void outcome(String optionValue) {
        System.out.println("Generating Project: " + optionValue);
        SpringBootProjectMaker.createSpringBootProject(optionValue, "me.raveenaingale", "Spring Boot Project via COD-CLI");
            MvcControllerMaker.createMVCController("HelloWorld",optionValue,"me.raveenaingale");
    }

}
