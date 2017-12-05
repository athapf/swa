package de.thaso.swa.be.business.mapper;

import de.thaso.swa.be.product.service.ProductData;
import de.thaso.swa.db.store.product.ProductEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * ProductMapper
 *
 * @author thaler
 * @since 22.09.16
 */
@Mapper
public interface ProductMapper {

    ProductData productToDO(ProductEntity productEntity);

    ProductEntity productToEntity(ProductData productData);

    List<ProductData> productListToDOList(List<ProductEntity> productEntityList);
}
