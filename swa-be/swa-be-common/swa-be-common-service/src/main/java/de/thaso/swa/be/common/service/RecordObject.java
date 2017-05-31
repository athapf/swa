package de.thaso.swa.be.common.service;

import javax.validation.constraints.NotNull;

/**
 * RecordObject
 *
 * @author thaler
 * @since 2017-05-28
 */
public class RecordObject<T extends RecordBase> extends DataBase {

    private static final long serialVersionUID = -2846969381350880393L;

    @NotNull
    private Long id;

    @NotNull
    private String instanceName;

    public RecordObject(final Long id, final Class<T> clazz) {
        this.id = id;
        this.instanceName = clazz.getSimpleName();
    }

    public RecordObject(final T recordData) {
        this.id = recordData.getId();
        this.instanceName = recordData.getClass().getSimpleName();
    }

    public Long getId() {
        return id;
    }

    public String getInstanceName() {
        return instanceName;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final RecordObject<?> that = (RecordObject<?>) o;

        if (!id.equals(that.id)) return false;
        return instanceName.equals(that.instanceName);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + instanceName.hashCode();
        return result;
    }
}
