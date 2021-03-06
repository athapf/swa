package de.thaso.swa.be.process.internal;

import de.thaso.swa.be.common.service.RecordBase;
import de.thaso.swa.be.common.service.RecordObject;

/**
 * ProcessInfoData
 *
 * @author thaler
 * @since 2017-05-29
 */
public class ProcessInfoData extends RecordBase {

    private static final long serialVersionUID = -7937415226763826790L;

    private Model model;
    private String instanzeName;
    
    public ProcessInfoData(final ProcessModel model, final RecordBase recordBase) {
        this.model = model;
        this.instanzeName = recordBase.getClass().getSimpleName();
        this.setId(recordBase.getId());
    }

    public ProcessInfoData(final ProcessModel model, final RecordObject recordObject) {
        this.model = model;
        this.instanzeName = recordObject.getInstanceName();
        this.setId(recordObject.getId());
    }

    public ProcessModel getModel() {
        return model;
    }

    public void setModel(final ProcessModel model) {
        this.model = model;
    }

    public String getInstanzeName() {
        return instanzeName;
    }

    public void setInstanzeName(final String instanzeName) {
        this.instanzeName = instanzeName;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        final ProcessInfoData that = (ProcessInfoData) o;

        if (model != that.model) return false;
        return instanzeName != null ? instanzeName.equals(that.instanzeName) : that.instanzeName == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (instanzeName != null ? instanzeName.hashCode() : 0);
        return result;
    }
}
