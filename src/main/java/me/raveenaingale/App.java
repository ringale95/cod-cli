package me.raveenaingale;

import me.raveenaingale.config.Configuration;
import me.raveenaingale.mapper.OutcomeOptionMapper;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) { OutcomeOptionMapper.getInstance().run(args);}
}
