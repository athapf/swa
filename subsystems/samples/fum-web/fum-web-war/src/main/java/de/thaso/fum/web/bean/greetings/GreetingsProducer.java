package de.thaso.fum.web.bean.greetings;

import de.thaso.fum.web.ra.api.FumWebLoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * GreetingsProducer
 *
 * @author thaler
 * @since 27.09.16
 */
public class GreetingsProducer {

    private static final Logger LOG = LoggerFactory.getLogger(GreetingsProducer.class);

    @Inject
    private FumAccessBean fumAccessBean;

    @Produces
    @RequestScoped
    @Named("greetingsModel")
    public GreetingsModel produceGreetingsModel(@New GreetingsModel greetingsModel) {
        LOG.info("produce greetings model ...");
        LOG.info("call: " + fumAccessBean.connect());
        FumWebLoginUser loginUser = fumAccessBean.findLoginUser();
        LOG.info("call: " + (loginUser != null ? loginUser.getLoginName() : "null"));
        LOG.info("call: " + fumAccessBean.disconnect());
        greetingsModel.setSimpleMessage("Hello, i'm the controller!");
        return greetingsModel;
    }
}
