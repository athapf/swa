package de.thaso.swa.be.common.service;

/**
 * BaseData
 *
 * @author thaler
 * @since 21.09.16
 */
public abstract class RecordBase extends DataBase {

    private static final long serialVersionUID = 1744601181537103935L;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final RecordBase recordBase = (RecordBase) o;

        return id != null ? id.equals(recordBase.id) : false;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
