package de.thaso.swa.be.product.service;

import java.util.List;

/**
 * ProductService
 *
 * @author thaler
 * @since 21.09.16
 */
public interface ProductService {

    void storeNickName(ProductData productData);

    List<ProductData> findByName(String name);

    List<ProductData> findByNickName(String name, String nick);
}
