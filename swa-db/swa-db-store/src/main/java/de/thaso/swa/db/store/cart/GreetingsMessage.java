package de.thaso.swa.db.store.cart;

import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;

/**
 * GreetingsMessage
 *
 * @author thaler
 * @since 2017-05-10
 */
@ApplicationScoped
public class GreetingsMessage {

    public String createGreeting(final String name) {
        if (StringUtils.isEmpty(name)) {
            return "Hello, World!";
        } else {
            return "Hello, " + name;
        }
    }
}
