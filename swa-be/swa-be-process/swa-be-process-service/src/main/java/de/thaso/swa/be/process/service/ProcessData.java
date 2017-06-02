package de.thaso.swa.be.process.service;

import de.thaso.swa.be.common.service.RecordBase;

/**
 * ProcessData
 *
 * @author thaler
 * @since 21.09.16
 */
public class ProcessData extends RecordBase {

    private static final long serialVersionUID = -7049489388682590929L;

    private String model;
    private Long objectId;
    private ProcessData parent;
    private String state;

    public String getModel() {
        return model;
    }

    public void setModel(final String model) {
        this.model = model;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(final Long objectId) {
        this.objectId = objectId;
    }

    public ProcessData getParent() {
        return parent;
    }

    public void setParent(final ProcessData parent) {
        this.parent = parent;
    }

    public String getState() {
        return state;
    }

    public void setState(final String state) {
        this.state = state;
    }
}
