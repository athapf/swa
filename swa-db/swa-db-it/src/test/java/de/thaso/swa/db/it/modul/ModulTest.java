package de.thaso.swa.db.it.modul;

import de.thaso.swa.db.it.base.DbTestBase;
import de.thaso.swa.db.it.utils.PersistenceStoreHelper;
import de.thaso.swa.db.store.cart.ShoppingCartDAO;
import de.thaso.swa.db.store.cart.ShoppingCartEntity;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.lang.reflect.Field;
import java.util.List;

/**
 * ModulTest
 *
 * @author thaler
 * @since 2017-05-10
 */
public class ModulTest extends DbTestBase {

    private ShoppingCartDAO underTest;

    private Weld weld;
    private WeldContainer container;

    @Before
    public void setUp() {
        weld = new Weld();
        container = weld.initialize();
        underTest = container.instance().select(ShoppingCartDAO.class).get();
        final EntityManager entityManager = PersistenceStoreHelper.getEntityManager();
        try {
            final Field entityManagerField = ShoppingCartDAO.class.getDeclaredField("entityManager");
            entityManagerField.setAccessible(true);
            entityManagerField.set(underTest, entityManager);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        weld.shutdown();
    }

    @Test
    public void testName() {
        final List<ShoppingCartEntity> resultList = underTest.findByName("FooBar");
    }
}
