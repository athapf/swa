package de.thaso.swa.be.business.mapper;

import de.thaso.swa.be.service.OrderData;
import de.thaso.swa.db.store.order.OrderEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * OrderMapper
 *
 * @author thaler
 * @since 22.09.16
 */
@Mapper
public interface OrderMapper {

    OrderData orderToDO(OrderEntity orderEntity);

    OrderEntity orderToEntity(OrderData orderData);

    List<OrderData> orderListToDOList(List<OrderEntity> nickNameEntityList);
}
