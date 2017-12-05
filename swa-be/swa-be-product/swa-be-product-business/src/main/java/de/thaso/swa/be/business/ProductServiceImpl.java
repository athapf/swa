package de.thaso.swa.be.business;

import de.thaso.swa.be.business.mapper.ProductMapper;
import de.thaso.swa.be.product.service.ProductData;
import de.thaso.swa.be.product.service.ProductService;
import de.thaso.swa.db.store.product.ProductDAO;
import de.thaso.swa.db.store.product.ProductEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * ProductImpl
 *
 * @author thaler
 * @since 21.09.16
 */
@Stateless
@Remote(ProductService.class)
public class ProductServiceImpl implements ProductService {

    public static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Inject
    private ProductDAO productDAO;

    @Inject
    private ProductMapper productMapper;

    @Override
    public void storeProduct(final ProductData productData) {
        final ProductEntity productEntity = productMapper.productToEntity(productData);
        productDAO.storeProduct(productEntity);
    }

    @Override
    public List<ProductData> findByCategory(final Long category) {
        LOG.debug("findByCategory( {} )", category);
        final List<ProductEntity> productEntityList = productDAO.findProductsByCategory(category);
        return productMapper.productListToDOList(productEntityList);
    }
}
