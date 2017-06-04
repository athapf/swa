package de.thaso.swa.db.store.order;

import de.thaso.swa.db.store.base.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * OrderEntity
 *
 * @author thaler
 * @since 2017-05-10
 */
@Entity
@Table(name = "T_ORDER")
@NamedQueries({
        @NamedQuery(name = OrderEntity.FIND_OPEN_ORDERS, query = "select o from OrderEntity o where o.completed is null order by o.created desc"),
})
public class OrderEntity extends EntityBase {

    private static final long serialVersionUID = -8836233993826476466L;

    public static final String FIND_OPEN_ORDERS = "FIND_OPEN_ORDERS";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OrderSequence")
    @SequenceGenerator(name = "OrderSequence", sequenceName = "SEQ_ID_ORDER", allocationSize = 10)
    @Column(name = "ID")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED")
    @NotNull
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "COMPLETED")
    private Date completed;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "APPROVED")
    private Date approved;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PROCESSED")
    private Date processed;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FINISHED")
    private Date finished;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(final Date created) {
        this.created = created;
    }

    public Date getCompleted() {
        return completed;
    }

    public void setCompleted(final Date completed) {
        this.completed = completed;
    }

    public Date getApproved() {
        return approved;
    }

    public void setApproved(final Date approved) {
        this.approved = approved;
    }

    public Date getProcessed() {
        return processed;
    }

    public void setProcessed(Date processed) {
        this.processed = processed;
    }

    public Date getFinished() {
        return finished;
    }

    public void setFinished(Date finished) {
        this.finished = finished;
    }
}
