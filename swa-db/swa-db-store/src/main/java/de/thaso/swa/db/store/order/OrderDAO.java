package de.thaso.swa.db.store.order;

import de.thaso.swa.db.common.exception.DatabaseError;
import de.thaso.swa.db.common.exception.DatabaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * OrderDAO
 *
 * @author thaler
 * @since 13.09.16
 */
public class OrderDAO {

    private final static Logger LOG = LoggerFactory.getLogger(OrderDAO.class);

    @Inject
    private EntityManager entityManager;

    public OrderEntity storeOrder(final OrderEntity orderEntity) {
        LOG.info("storeOrder with id {}", orderEntity.getId());

        entityManager.persist(orderEntity);
        return orderEntity;
    }

    public OrderEntity findOrderById(final Long id) {
        LOG.info("findOrderById {}", id);

        final OrderEntity orderEntity = entityManager.find(OrderEntity.class, id);
        return orderEntity;
    }

    public OrderEntity loadOrderById(final Long id) throws DatabaseException {
        LOG.info("loadOrderById {}", id);
        
        final OrderEntity orderEntity = entityManager.find(OrderEntity.class, id);
        if(orderEntity == null) {
            throw new DatabaseException(DatabaseError.ENTITY_NOT_FOUND, "Order with id " + id + " not found!");
        }
        return orderEntity;
    }

    public List<OrderEntity> findOpenOrders() {
        LOG.info("findOpenOrders");

        final TypedQuery<OrderEntity> query
                = entityManager.createNamedQuery(OrderEntity.FIND_OPEN_ORDERS, OrderEntity.class);
        return query.getResultList();
    }
}
