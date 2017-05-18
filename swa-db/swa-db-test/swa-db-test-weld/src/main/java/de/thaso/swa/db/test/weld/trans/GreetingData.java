package de.thaso.swa.db.test.weld.trans;

/**
 * GreetingData
 *
 * @author thaler
 * @since 2017-05-18
 */
public class GreetingData implements DataBase {

    private String greeting;
    private String name;

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(final String greeting) {
        this.greeting = greeting;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
