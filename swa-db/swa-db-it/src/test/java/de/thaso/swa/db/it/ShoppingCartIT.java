package de.thaso.swa.db.it;

import de.thaso.swa.db.it.base.DbTestBase;
import de.thaso.swa.db.store.cart.ShoppingCartDAO;
import de.thaso.swa.db.store.cart.ShoppingCartEntity;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.model.MultipleFailureException;
import org.mockito.InjectMocks;

import javax.persistence.Query;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * ShoppingCartIT
 *
 * @author thaler
 * @since 13.09.16
 */
public class ShoppingCartIT extends DbTestBase {

    @InjectMocks
    private ShoppingCartDAO underTest;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testStoreShoppingCart() throws SQLException {
        // given
        final ShoppingCartEntity processEntity = new ShoppingCartEntity();
        processEntity.setSince(new Date());
        processEntity.setName("test name");
        processEntity.setTenant(18);
        // when
        entityManager.persist(processEntity);
        // then
        assertThat(processEntity.getId(), is(greaterThan(1000000L)));

        entityManager.flush();
        final ResultSet resultSet = getConnection().prepareStatement("SELECT COUNT(*) FROM T_SHOPPING_CART").executeQuery();
        resultSet.next();
        assertThat(resultSet.getInt(1),is(15));
    }

    @Test
    @Ignore
    public void testPrimaryKeyViolation() throws SQLException {
        // given
        final Query nextvalQuery = entityManager.createNativeQuery("SELECT SEQ_ID_SHOPPING_CART.NEXTVAL FROM T_SHOPPING_CART WHERE ID = 5");
        Integer singleResult = (Integer) nextvalQuery.getSingleResult();
        singleResult = 1000200;
        Query nativeQuery = entityManager.createNativeQuery("INSERT INTO T_SHOPPING_CART (ID, SINCE, NAME, TENANT) VALUES(" + singleResult + ", '2014-02-15', 'foo', 29)");
        nativeQuery.executeUpdate();
        getConnection().commit();

        final ShoppingCartEntity processEntity = new ShoppingCartEntity();
        processEntity.setSince(new Date());
        processEntity.setName("developer");
        processEntity.setTenant(432);

        exception.expect(MultipleFailureException.class);
        //exception.expectCause(new SecondCauseMatcher(com.ibm.db2.jcc.am.SqlIntegrityConstraintViolationException.class, "DB2 SQL Error", "SQLCODE=-803"));
        // when
        entityManager.persist(processEntity);
        entityManager.flush();
    }

    @Test
    public void testFindByName() throws SQLException {
        // when
        final List<ShoppingCartEntity> result = underTest.findByName("my name A");
        // then
        assertThat(result.size(), is(10));
        Long previousTimestamp = null;
        for (ShoppingCartEntity processEntity : result) {
            if(previousTimestamp != null) {
                assertThat(processEntity.getSince().getTime(),is(lessThan(previousTimestamp)));
            }
            previousTimestamp = processEntity.getSince().getTime();
        }
    }
}
