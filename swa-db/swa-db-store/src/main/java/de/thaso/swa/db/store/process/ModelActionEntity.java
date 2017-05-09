package de.thaso.swa.db.store.process;

import de.thaso.swa.db.store.base.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
 * ModelActionEntity
 *
 * @author thaler
 * @since 2017-04-25
 */
@Entity
@Table(name = "T_MODEL_ACTION")
@NamedQueries({
        @NamedQuery(name = ModelActionEntity.FIND_ALL_POSSIBLE_ACTIONS, query = "select a from ModelActionEntity a where a.edgeId.fromId.state = :state and a.edgeId.fromId.graph = :graph"),
        @NamedQuery(name = ModelActionEntity.FIND_POSSIBLE_PUBLIC_ACTIONS, query = "select a from ModelActionEntity a where a.edgeId.fromId.state = :state and a.edgeId.fromId.graph = :graph and a.type = ActionTypeEnum.PUBLIC")
})
public class ModelActionEntity extends EntityBase {

    private static final long serialVersionUID = 8257407971767493594L;

    public static final String FIND_ALL_POSSIBLE_ACTIONS = "FIND_ALL_POSSIBLE_ACTIONS";
    public static final String FIND_POSSIBLE_PUBLIC_ACTIONS = "FIND_POSSIBLE_PUBLIC_ACTIONS";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ModelActionSequence")
    @SequenceGenerator(name = "ModelActionSequence", sequenceName = "SEQ_ID_MODEL_ACTION", allocationSize = 10)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ACTION")
    @NotNull
    private String action;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    @NotNull
    private ActionTypeEnum type;

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

    public String getAction() {
        return action;
    }

    public void setAction(final String action) {
        this.action = action;
    }

    public ActionTypeEnum getType() {
        return type;
    }

    public void setType(final ActionTypeEnum type) {
        this.type = type;
    }

    public ModelEdgeEntity getEdgeId() {
        return edgeId;
    }

    public void setEdgeId(final ModelEdgeEntity edgeId) {
        this.edgeId = edgeId;
    }
}
