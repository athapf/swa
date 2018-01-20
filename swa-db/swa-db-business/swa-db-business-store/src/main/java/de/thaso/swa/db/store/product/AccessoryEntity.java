package de.thaso.swa.db.store.product;

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

/**
 * AccessoryEntity
 *
 * @author thaler
 * @since 2017-06-04
 */
@Entity
@Table(name = "T_ACCESSORY")
@NamedQueries({
        @NamedQuery(name = AccessoryEntity.FIND_BY_PRODUCT_ID, query = "select a from AccessoryEntity a where a.productId = :productId"),
})
public class AccessoryEntity extends EntityBase {

    private static final long serialVersionUID = -7001560236158482552L;

    public static final String FIND_BY_PRODUCT_ID = "FIND_BY_PRODUCT_ID";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AccessorySequence")
    @SequenceGenerator(name = "AccessorySequence", sequenceName = "SEQ_ID_ACCESSORY", allocationSize = 10)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "SPECIAL")
    private Boolean special;

    @Column(name = "DESCRIPTION")
    private String description;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(final Long productId) {
        this.productId = productId;
    }

    public Boolean getSpecial() {
        return special;
    }

    public void setSpecial(final Boolean special) {
        this.special = special;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
