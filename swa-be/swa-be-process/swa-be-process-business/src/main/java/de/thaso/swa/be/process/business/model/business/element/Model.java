package de.thaso.swa.be.process.business.model.business.element;

import de.thaso.swa.be.process.service.Action;
import de.thaso.swa.be.process.service.State;

import java.util.List;
import java.util.Map;

/**
 * ModelDefinition
 *
 * @author thaler
 * @since 2017-05-30
 */
public class Model {

    private String modelName;

    private List<Action> startActionList;

    private List<State> endStateList;

    private Map<State, Map<Action, Edge>> edgeMap;
}
