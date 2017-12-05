package de.thaso.swa.be.product.service;

import java.io.Serializable;
import java.util.Date;

/**
 * ProductData
 *
 * @author thaler
 * @since 21.09.16
 */
public class ProductData implements Serializable {

    private static final long serialVersionUID = -7049489388682590929L;

    private Long id;
    private Long categorie;
//    private ProductColorEnum color;
    private Date desiredDelivery;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategorie() {
        return categorie;
    }

    public void setCategorie(Long categorie) {
        this.categorie = categorie;
    }

//    public ProductColorEnum getColor() {
//        return color;
//    }

//    public void setColor(ProductColorEnum color) {
//        this.color = color;
//    }

    public Date getDesiredDelivery() {
        return desiredDelivery;
    }

    public void setDesiredDelivery(Date desiredDelivery) {
        this.desiredDelivery = desiredDelivery;
    }
}
