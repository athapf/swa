package de.thaso.swa.be.common.service;

import javax.validation.constraints.NotNull;

/**
 * RecordVersionObject
 *
 * @author thaler
 * @since 2017-05-28
 */
public class RecordVersionObject<T extends RecordBase> extends RecordObject<T> {

    private static final long serialVersionUID = 7195751159379921871L;

    @NotNull
    private long version;

    public RecordVersionObject(final Long id, final Class<T> clazz, final long version) {
        super(id, clazz);
        this.version = version;
    }

    public long getVersion() {
        return version;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof RecordVersionObject)) return false;
        if (!super.equals(o)) return false;

        final RecordVersionObject<?> that = (RecordVersionObject<?>) o;

        return version == that.version;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (version ^ (version >>> 32));
        return result;
    }
}
