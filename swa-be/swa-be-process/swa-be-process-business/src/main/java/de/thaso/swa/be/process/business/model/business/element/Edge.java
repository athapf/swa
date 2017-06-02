package de.thaso.swa.be.process.business.model.business.element;

import de.thaso.swa.be.process.model.Action;

import java.util.List;

/**
 * Edge
 *
 * @author thaler
 * @since 2017-05-30
 */
public class Edge {

    private Action action;
    private List<Condition> conditionList;
    private List<Trigger> triggerList;
}
