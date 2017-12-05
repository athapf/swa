package de.thaso.swa.be.business;

import de.thaso.swa.be.business.mapper.OrderMapper;
import de.thaso.swa.be.service.OrderData;
import de.thaso.swa.db.store.order.OrderDAO;
import de.thaso.swa.db.store.order.OrderEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * OrderServiceImplTest
 *
 * @author thaler
 * @since 22.09.16
 */
public class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl underTest;

    @Mock
    private OrderDAO orderDAO;

    @Mock
    private OrderMapper orderMapper;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testName() {
        // given
        final OrderData orderData = new OrderData();
        final OrderEntity orderEntity = new OrderEntity();
        when(orderMapper.orderToEntity(orderData)).thenReturn(orderEntity);
        // when
        underTest.storeOrder(orderData);
        // then
        verify(orderDAO).storeOrder(orderEntity);
    }

    @Test
    public void testFindOpenOrders() {
        // given
        final List<OrderEntity> orderEntityList = new ArrayList<>();
        when(orderDAO.findOpenOrders()).thenReturn(orderEntityList);
        final List<OrderData> orderDataList = new ArrayList<>();
        when(orderMapper.orderListToDOList(orderEntityList)).thenReturn(orderDataList);
        // when
        final List<OrderData> result = underTest.findOpenOrders();
        // then
        assertThat(result, is(orderDataList));
    }
}
