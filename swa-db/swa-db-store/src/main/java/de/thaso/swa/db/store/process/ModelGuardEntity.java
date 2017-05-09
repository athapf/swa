package de.thaso.swa.db.store.process;

import de.thaso.swa.db.store.base.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * ModelGuardEntity
 *
 * @author thaler
 * @since 2017-04-25
 */
@Entity
@Table(name = "T_MODEL_GUARD")
public class ModelGuardEntity extends EntityBase {

    private static final long serialVersionUID = -4946806295504968923L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ModelGuardSequence")
    @SequenceGenerator(name = "ModelGuardSequence", sequenceName = "SEQ_ID_MODEL_GUARD", allocationSize = 10)
    @Column(name = "ID")
    private Long id;

    @Column(name = "GUARD")
    @NotNull
    private String guard;

    @ManyToOne(optional = false)
    @JoinColumn(name = "EDGE_ID")
    private ModelEdgeEntity edgeId;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGuard() {
        return guard;
    }

    public void setGuard(final String guard) {
        this.guard = guard;
    }

    public ModelEdgeEntity getEdgeId() {
        return edgeId;
    }

    public void setEdgeId(final ModelEdgeEntity edgeId) {
        this.edgeId = edgeId;
    }
}
