package de.thaso.swa.be.service;

import java.util.List;

/**
 * OrderService
 *
 * @author thaler
 * @since 21.09.16
 */
public interface OrderService {

    void storeNickName(OrderData orderData);

    List<OrderData> findByName(String name);

    List<OrderData> findByNickName(String name, String nick);
}
