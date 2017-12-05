package de.thaso.swa.be.product.service;

import java.util.List;

/**
 * ProductService
 *
 * @author thaler
 * @since 21.09.16
 */
public interface ProductService {

    void storeProduct(ProductData productData);

    List<ProductData> findByCategory(Long category);
}
