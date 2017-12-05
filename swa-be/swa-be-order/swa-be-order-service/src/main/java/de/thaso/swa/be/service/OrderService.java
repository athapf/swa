package de.thaso.swa.be.service;

import java.util.List;

/**
 * OrderService
 *
 * @author thaler
 * @since 21.09.16
 */
public interface OrderService {

    void storeOrder(OrderData orderData);

    List<OrderData> findOpenOrders();
}
