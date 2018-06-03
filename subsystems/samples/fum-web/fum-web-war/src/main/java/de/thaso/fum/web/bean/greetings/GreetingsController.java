package de.thaso.fum.web.bean.greetings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * GreetingsController
 *
 * @author thaler
 * @since 23.09.16
 */
@Named
@RequestScoped
public class GreetingsController {

    private static final Logger LOG = LoggerFactory.getLogger(GreetingsController.class);

    @Inject
    private GreetingsModel greetingsModel;

    public void doSomething() {
        LOG.info("do something ...");
    }
}
