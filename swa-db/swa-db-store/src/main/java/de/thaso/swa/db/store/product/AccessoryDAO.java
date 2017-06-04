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
 * AccessoryDAO
 *
 * @author thaler
 * @since 2017-06-04
 */
public class AccessoryDAO {

    private final static Logger LOG = LoggerFactory.getLogger(AccessoryDAO.class);

    @Inject
    private EntityManager entityManager;
    
    public AccessoryEntity storeAccessory(final AccessoryEntity accessoryEntity) {
        LOG.info("storeAccessory with id {}", accessoryEntity.getId());

        entityManager.persist(accessoryEntity);
        return accessoryEntity;
    }

    public AccessoryEntity findAccessoryById(final Long id) {
        LOG.info("findAccessoryById {}", id);

        final AccessoryEntity accessoryEntity = entityManager.find(AccessoryEntity.class, id);
        return accessoryEntity;
    }

    public AccessoryEntity loadAccessoryById(final Long id) throws DatabaseException {
        LOG.info("loadAccessoryById {}", id);

        final AccessoryEntity accessoryEntity = entityManager.find(AccessoryEntity.class, id);
        if(accessoryEntity == null) {
            throw new DatabaseException(DatabaseError.ENTITY_NOT_FOUND, "Accessory with id " + id + " not found!");
        }
        return accessoryEntity;
    }

    public List<AccessoryEntity> findAccessorysByCategorie(final Long productId) {
        LOG.info("findAccessorysByCategorie");

        final TypedQuery<AccessoryEntity> query
                = entityManager.createNamedQuery(AccessoryEntity.FIND_BY_PRODUCT_ID, AccessoryEntity.class);
        query.setParameter("productId", productId);
        return query.getResultList();
    }
}
