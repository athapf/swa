package de.thaso.swa.db.it;

import de.thaso.swa.db.it.base.DbTestBase;
import de.thaso.swa.db.workshop.WorkshopDAO;
import de.thaso.swa.db.workshop.WorkshopEntity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.core.Is.is;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * ModelDataIT
 *
 * @author thaler
 * @since 2017-12-12
 */
public class WorkshopIT extends DbTestBase {

    @InjectMocks
    private WorkshopDAO underTest;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testStoreWorkshop() throws SQLException {
        // given
        final WorkshopEntity workshopEntity = new WorkshopEntity();
        workshopEntity.setTitle("title");
        workshopEntity.setContent("this is a content");
        workshopEntity.setNumber(1234);
        // when
        entityManager.persist(workshopEntity);
        // then
        assertThat(workshopEntity.getId(), is(greaterThanOrEqualTo(1000000L)));

        entityManager.flush();
        final ResultSet resultSet = getConnection().prepareStatement("SELECT COUNT(*) FROM T_WORKSHOP WHERE ID >= 1000000").executeQuery();
        resultSet.next();
        assertThat(resultSet.getInt(1),is(1));
    }
}
