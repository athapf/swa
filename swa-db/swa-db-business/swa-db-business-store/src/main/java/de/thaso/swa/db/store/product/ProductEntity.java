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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * ProductEntity
 *
 * @author thaler
 * @since 2017-06-04
 */
@Entity
@Table(name = "T_PRODUCT")
@NamedQueries({
        @NamedQuery(name = ProductEntity.FIND_BY_CATEGORIE, query = "select p from ProductEntity o where o.categorie = :categorie"),
})
public class ProductEntity extends EntityBase {

    private static final long serialVersionUID = -6334928390327339579L;

    public static final String FIND_BY_CATEGORIE = "FIND_BY_CATEGORIE";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ProductSequence")
    @SequenceGenerator(name = "ProductSequence", sequenceName = "SEQ_ID_PRODUCT", allocationSize = 10)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CATEGORIE")
    @NotNull
    private Long categorie;

    @Column(name = "COLOR")
    private ProductColorEnum color;

    @Temporal(TemporalType.DATE)
    @Column(name = "DESIRED_DELIVERY")
    private Date desiredDelivery;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getCategorie() {
        return categorie;
    }

    public void setCategorie(final Long categorie) {
        this.categorie = categorie;
    }

    public ProductColorEnum getColor() {
        return color;
    }

    public void setColor(final ProductColorEnum color) {
        this.color = color;
    }

    public Date getDesiredDelivery() {
        return desiredDelivery;
    }

    public void setDesiredDelivery(final Date desiredDelivery) {
        this.desiredDelivery = desiredDelivery;
    }
}
