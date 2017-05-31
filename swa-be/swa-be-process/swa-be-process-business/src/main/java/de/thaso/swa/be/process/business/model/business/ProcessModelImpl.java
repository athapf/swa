package de.thaso.swa.be.process.business.model.business;

import de.thaso.swa.be.process.business.model.ProcessModel;
import de.thaso.swa.be.process.business.model.business.element.Model;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

/**
 * ProcessModelImpl
 *
 * @author thaler
 * @since 2017-05-30
 */
@ApplicationScoped
public class ProcessModelImpl implements ProcessModel {

    private Map<String, Model> modelMap = new HashMap<>();

    @PostConstruct
    void prepareModels() {

    }
}
