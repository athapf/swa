package de.thaso.swa.db.store.cart;

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
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * ShoppingCartEntity
 *
 * @author thaler
 * @since 13.09.16
 */
@Entity
@Table(name = "T_SHOPPING_CART")
@NamedQueries({
    @NamedQuery(name = ShoppingCartEntity.FIND_BY_NAMES, query = "select sc from ShoppingCartEntity sc where sc.name = :name order by sc.since desc"),
})
public class ShoppingCartEntity extends EntityBase {

    private static final long serialVersionUID = -4319045348350461073L;

    public static final String FIND_BY_NAMES = "FIND_BY_NAMES";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ShoppingCartSequence")
    @SequenceGenerator(name = "ShoppingCartSequence", sequenceName = "SEQ_ID_SHOPPING_CART", allocationSize = 10)
    @Column(name = "ID")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "SINCE")
    @NotNull
    private Date since;

    @Column(name = "NAME")
    @Size(min=1, max=30)
    @NotNull
    private String name;

    @Column(name = "TENANT")
    private Integer tenant;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getSince() {
        return since;
    }

    public void setSince(final Date since) {
        this.since = since;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getTenant() {
        return tenant;
    }

    public void setTenant(final Integer tenant) {
        this.tenant = tenant;
    }
}
