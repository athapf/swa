package de.thaso.swa.db.it;

import de.thaso.swa.db.it.base.DbTestBase;
import de.thaso.swa.db.store.order.OrderDAO;
import de.thaso.swa.db.store.order.OrderEntity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * OrderIT
 *
 * @author thaler
 * @since 13.09.16
 */
public class OrderIT extends DbTestBase {

    @InjectMocks
    private OrderDAO underTest;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testStoreOrder() throws SQLException {
        // given
        final OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCreated(new Date());
        // when
        entityManager.persist(orderEntity);
        // then
        assertThat(orderEntity.getId(), is(greaterThanOrEqualTo(1000000L)));

        entityManager.flush();
        final ResultSet resultSet = getConnection().prepareStatement("SELECT COUNT(*) FROM T_ORDER WHERE ID >= 1000000").executeQuery();
        resultSet.next();
        assertThat(resultSet.getInt(1),is(1));
    }

    @Test
    public void testFindOpenOrders() throws SQLException {
        updateDatabase("OrderIT/testFindOpenOrders.xml");
        // when
        final List<OrderEntity> result = underTest.findOpenOrders();
        // then
        assertThat(result.size(), is(2));
        Long previousTimestamp = null;
        for (OrderEntity processEntity : result) {
            if(previousTimestamp != null) {
                assertThat(processEntity.getCreated().getTime(),is(lessThan(previousTimestamp)));
            }
            previousTimestamp = processEntity.getCreated().getTime();
        }
    }
}
