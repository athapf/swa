package de.thaso.swa.db.store.product;

import de.thaso.swa.db.common.DatabaseError;
import de.thaso.swa.db.common.DatabaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * ProductDAO
 *
 * @author thaler
 * @since 2017-06-04
 */
public class ProductDAO {

    private final static Logger LOG = LoggerFactory.getLogger(ProductDAO.class);

    @Inject
    private EntityManager entityManager;

    public ProductEntity storeProduct(final ProductEntity productEntity) {
        LOG.info("storeProduct with id {}", productEntity.getId());

        entityManager.persist(productEntity);
        return productEntity;
    }

    public ProductEntity findProductById(final Long id) {
        LOG.info("findProductById {}", id);

        final ProductEntity productEntity = entityManager.find(ProductEntity.class, id);
        return productEntity;
    }

    public ProductEntity loadProductById(final Long id) throws DatabaseException {
        LOG.info("loadProductById {}", id);

        final ProductEntity productEntity = entityManager.find(ProductEntity.class, id);
        if(productEntity == null) {
            throw new DatabaseException(DatabaseError.ENTITY_NOT_FOUND, "Product with id " + id + " not found!");
        }
        return productEntity;
    }

    public List<ProductEntity> findProductsByCategory(final Long category) {
        LOG.info("findProductsByCategorie");

        final TypedQuery<ProductEntity> query
                = entityManager.createNamedQuery(ProductEntity.FIND_BY_CATEGORIE, ProductEntity.class);
        query.setParameter("categorie", category);
        return query.getResultList();
    }
}
