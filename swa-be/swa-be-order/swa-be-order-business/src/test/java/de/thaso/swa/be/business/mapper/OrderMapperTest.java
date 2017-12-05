package de.thaso.swa.be.business.mapper;

import de.thaso.swa.be.service.OrderData;
import de.thaso.swa.db.store.order.OrderEntity;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * OrderMapperTest
 *
 * @author thaler
 * @since 22.09.16
 */
public class OrderMapperTest {

    @InjectMocks
    private OrderMapper underTest = Mappers.getMapper(OrderMapper.class);

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void convertToEntity_WhenDOIsNull() {
        // when
        final OrderEntity result = underTest.orderToEntity(null);
        // then
        assertThat(result,is(nullValue()));
    }

    @Test
    public void convertListToDO() {
        // given
        final ArrayList<OrderEntity> orderEntityList = new ArrayList<>();
        final OrderEntity firstMessageEntity = new OrderEntity();
        firstMessageEntity.setId(743L);
        firstMessageEntity.setCreated(new Date());
        orderEntityList.add(firstMessageEntity);
        final OrderEntity secondMessageEntity = new OrderEntity();
        secondMessageEntity.setId(234L);
        secondMessageEntity.setCreated(new Date());
        orderEntityList.add(secondMessageEntity);
        // when
        final List<OrderData> orderDataList = underTest.orderListToDOList(orderEntityList);
        // then
        assertThat(orderDataList.size(),is(orderEntityList.size()));

        OrderData firstOrderData = orderDataList.get(0);
        assertThat(firstOrderData.getId(),is(firstMessageEntity.getId()));
//        assertThat(firstOrderData.getTimestamp(),is(firstMessageEntity.getCreated()));
        OrderData secondOrderData = orderDataList.get(1);
        assertThat(secondOrderData.getId(),is(secondMessageEntity.getId()));
//        assertThat(secondOrderData.getTimestamp(),is(secondMessageEntity.getCreated()));
    }
}
