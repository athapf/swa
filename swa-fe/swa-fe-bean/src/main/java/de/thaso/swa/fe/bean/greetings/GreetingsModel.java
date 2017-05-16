package de.thaso.swa.fe.bean.greetings;

import javax.enterprise.inject.Any;
import java.io.Serializable;
import java.util.List;

/**
 * GreetingsModel
 *
 * @author thaler
 * @since 27.09.16
 */
@Any
public class GreetingsModel implements Serializable {

    private String simpleMessage;

    public String getSimpleMessage() {
        return simpleMessage;
    }

    public void setSimpleMessage(final String simpleMessage) {
        this.simpleMessage = simpleMessage;
    }
}
