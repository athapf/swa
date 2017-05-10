package de.thaso.swa.db.store.order;

import de.thaso.swa.db.common.DatabaseError;
import de.thaso.swa.db.common.DatabaseException;
import de.thaso.swa.db.store.utils.DatabaseExceptionCodeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * OrderDAOTest
 *
 * @author thaler
 * @since 13.09.16
 */
public class OrderDAOTest {

    public static final DatabaseExceptionCodeMatcher EXCEPTION_MATCHER_ENTITY_NOT_FOUND
            = new DatabaseExceptionCodeMatcher(DatabaseError.ENTITY_NOT_FOUND);

    @InjectMocks
    private OrderDAO underTest;

    @Mock
    private EntityManager entityManager;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private Long primaryKey;
    private OrderEntity orderEntity;

    @Before
    public void setUp() {
        initMocks(this);

        primaryKey = 1L;
        orderEntity = new OrderEntity();
        when(entityManager.find(OrderEntity.class, primaryKey)).thenReturn(orderEntity);
    }

    @Test
    public void storeOrder() {
        // when
        final OrderEntity result = underTest.storeOrder(orderEntity);
        // then
        verify(entityManager).persist(orderEntity);
        assertThat(result, is(orderEntity));
    }

    @Test
    public void findOrder() {
        // when
        final OrderEntity result = underTest.findOrderById(primaryKey);
        // then
        assertThat(result, is(orderEntity));
    }

    @Test
    public void findOrder_whenPrimaryKeyNotFound() {
        // given
        when(entityManager.find(OrderEntity.class, primaryKey)).thenReturn(null);
        // when
        final OrderEntity result = underTest.findOrderById(primaryKey);
        // then
        assertThat(result, is(nullValue()));
    }

    @Test
    public void loadOrder() {
        // when
        final OrderEntity result = underTest.loadOrderById(primaryKey);
        // then
        assertThat(result, is(orderEntity));
    }

    @Test
    public void loadOrder_whenPrimaryKeyNotFound() {
        // given
        when(entityManager.find(OrderEntity.class, primaryKey)).thenReturn(null);
        exception.expect(DatabaseException.class);
        exception.expectMessage(containsString(" " + primaryKey.toString() + " "));
        exception.expect(EXCEPTION_MATCHER_ENTITY_NOT_FOUND);
        // when
        final OrderEntity result = underTest.loadOrderById(primaryKey);
    }

    @Test
    public void findOpenOrders() {
        // given
        final TypedQuery query = mock(TypedQuery.class);
        when(entityManager.createNamedQuery(OrderEntity.FIND_OPEN_ORDERS,OrderEntity.class)).thenReturn(query);
        final List<OrderEntity> orderEntityList = new ArrayList<>();
        when(query.getResultList()).thenReturn(orderEntityList);
        // when
        final List<OrderEntity> result = underTest.findOpenOrders();
        // then
        assertThat(result,is(orderEntityList));
    }
}