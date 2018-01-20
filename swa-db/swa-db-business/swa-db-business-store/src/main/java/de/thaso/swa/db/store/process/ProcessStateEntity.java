package de.thaso.swa.db.store.process;

import de.thaso.swa.db.common.base.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * ProcessEntity
 *
 * @author thaler
 * @since 13.09.16
 */
@Entity
@Table(name = "T_PROCESS_STATE")
@NamedQueries({
    @NamedQuery(name = ProcessStateEntity.FIND_BY_DOMAINOBJECT, query = "select p from ProcessStateEntity p where p.domainobjectName = :objname and p.domainobjectId = :objid"),
})
public class ProcessStateEntity extends EntityBase {

    private static final long serialVersionUID = -6143480073466651969L;

    public static final String FIND_BY_DOMAINOBJECT = "FIND_BY_DOMAINOBJECT";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ProcessSequence")
    @SequenceGenerator(name = "ProcessSequence", sequenceName = "SEQ_ID_PROCESS_STATE", allocationSize = 10)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DOMAINOBJECT_NAME")
    @NotNull
    private String domainobjectName;

    @Column(name = "DOMAINOBJECT_ID")
    @NotNull
    private Long domainobjectId;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private ProcessStateEntity parentId;

    @ManyToOne
    @JoinColumn(name = "STATE_ID")
    private ModelStateEntity state;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDomainobjectName() {
        return domainobjectName;
    }

    public void setDomainobjectName(final String domainobjectName) {
        this.domainobjectName = domainobjectName;
    }

    public Long getDomainobjectId() {
        return domainobjectId;
    }

    public void setDomainobjectId(final Long domainobjectId) {
        this.domainobjectId = domainobjectId;
    }

    public ProcessStateEntity getParentId() {
        return parentId;
    }

    public void setParentId(final ProcessStateEntity parentId) {
        this.parentId = parentId;
    }

    public ModelStateEntity getState() {
        return state;
    }

    public void setState(final ModelStateEntity state) {
        this.state = state;
    }
}
