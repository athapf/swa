package de.thaso.swa.db.store.cart;

import de.thaso.swa.db.common.DatabaseError;
import de.thaso.swa.db.common.DatabaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * ShoppingCartDAO
 *
 * @author thaler
 * @since 13.09.16
 */
public class ShoppingCartDAO {

    private final static Logger LOG = LoggerFactory.getLogger(ShoppingCartDAO.class);

    @Inject
    private EntityManager entityManager;

    public ShoppingCartEntity storeShoppingCart(final ShoppingCartEntity shoppingCartEntity) {
        LOG.info("storeShoppingCart with id {} in name {}", shoppingCartEntity.getId(), shoppingCartEntity.getName());

        entityManager.persist(shoppingCartEntity);
        return shoppingCartEntity;
    }

    public ShoppingCartEntity findShoppingCartById(final Long id) {
        LOG.info("findShoppingCartById {}", id);

        final ShoppingCartEntity shoppingCartEntity = entityManager.find(ShoppingCartEntity.class, id);
        return shoppingCartEntity;
    }

    public ShoppingCartEntity loadShoppingCartById(final Long id) throws DatabaseException {
        LOG.info("loadShoppingCartById {}", id);
        
        final ShoppingCartEntity shoppingCartEntity = entityManager.find(ShoppingCartEntity.class, id);
        if(shoppingCartEntity == null) {
            throw new DatabaseException(DatabaseError.ENTITY_NOT_FOUND, "ShoppingCart with id " + id + " not found!");
        }
        return shoppingCartEntity;
    }

    public List<ShoppingCartEntity> findByName(final String name) {
        LOG.info("findByName( {} )", name);

        final TypedQuery<ShoppingCartEntity> query
                = entityManager.createNamedQuery(ShoppingCartEntity.FIND_BY_NAMES, ShoppingCartEntity.class);
        query.setParameter("name", name);
        query.setMaxResults(10);
        return query.getResultList();
    }
}
