package de.thaso.swa.db.store.process;

import de.thaso.swa.db.common.base.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * ModelStateEntity
 *
 * @author thaler
 * @since 2017-04-25
 */
@Entity
@Table(name = "T_MODEL_STATE")
@NamedQueries({
        @NamedQuery(name = ModelStateEntity.FIND_BY_STATE, query = "select s from ModelStateEntity s where s.state = :state and s.graph = :graph")
})
public class ModelStateEntity extends EntityBase {

    private static final long serialVersionUID = 2887689882507812281L;

    public static final String FIND_BY_STATE = "FIND_BY_STATE";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ModelStateSequence")
    @SequenceGenerator(name = "ModelStateSequence", sequenceName = "SEQ_ID_MODEL_STATE", allocationSize = 10)
    @Column(name = "ID")
    private Long id;

    @Column(name = "STATE")
    @NotNull
    private String state;

    @Column(name = "GRAPH")
    @NotNull
    private String graph;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(final String state) {
        this.state = state;
    }

    public String getGraph() {
        return graph;
    }

    public void setGraph(final String graph) {
        this.graph = graph;
    }
}
