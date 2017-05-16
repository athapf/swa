package de.thaso.swa.fe.bean.greetings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 * GreetingsProducer
 *
 * @author thaler
 * @since 27.09.16
 */
public class GreetingsProducer {

    private static final Logger LOG = LoggerFactory.getLogger(GreetingsProducer.class);

    @Produces
    @RequestScoped
    @Named("greetingsModel")
    public GreetingsModel produceGreetingsModel(@New GreetingsModel greetingsModel) {
        LOG.info("produce greetings model ...");
        greetingsModel.setSimpleMessage("Hello, i'm the controller!");
        return greetingsModel;
    }
}
