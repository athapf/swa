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

/**
 * ModelEdgeEntity
 *
 * @author thaler
 * @since 2017-04-25
 */
@Entity
@Table(name = "T_MODEL_EDGE")
public class ModelEdgeEntity extends EntityBase {

    private static final long serialVersionUID = 2887689882507812281L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ModelEdgeSequence")
    @SequenceGenerator(name = "ModelEdgeSequence", sequenceName = "SEQ_ID_MODEL_EDGE", allocationSize = 10)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "FROM_ID")
    private ModelStateEntity fromId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "TO_ID")
    private ModelStateEntity toId;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ModelStateEntity getFromId() {
        return fromId;
    }

    public void setFromId(final ModelStateEntity fromId) {
        this.fromId = fromId;
    }

    public ModelStateEntity getToId() {
        return toId;
    }

    public void setToId(final ModelStateEntity toId) {
        this.toId = toId;
    }
}
