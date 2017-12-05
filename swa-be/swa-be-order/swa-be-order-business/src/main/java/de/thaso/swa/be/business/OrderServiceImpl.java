package de.thaso.swa.be.business;

import de.thaso.swa.be.business.mapper.OrderMapper;
import de.thaso.swa.be.service.OrderData;
import de.thaso.swa.be.service.OrderService;
import de.thaso.swa.db.store.order.OrderDAO;
import de.thaso.swa.db.store.order.OrderEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * OrderImpl
 *
 * @author thaler
 * @since 21.09.16
 */
@Stateless
@Remote(OrderService.class)
public class OrderServiceImpl implements OrderService {

    public static final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Inject
    private OrderDAO orderDAO;

    @Inject
    private OrderMapper orderMapper;

    @Override
    public void storeOrder(final OrderData orderData) {
        final OrderEntity orderEntity = orderMapper.orderToEntity(orderData);
        orderDAO.storeOrder(orderEntity);
    }

    @Override
    public List<OrderData> findOpenOrders() {
        LOG.debug("findOpenOrders ...");
        final List<OrderEntity> orderEntityList = orderDAO.findOpenOrders();
        return orderMapper.orderListToDOList(orderEntityList);
    }
}
