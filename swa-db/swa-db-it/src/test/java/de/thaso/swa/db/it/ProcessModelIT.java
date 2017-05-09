package de.thaso.swa.db.it;

import de.thaso.swa.db.it.base.DbTestBase;
import de.thaso.swa.db.store.process.ModelStateEntity;
import de.thaso.swa.db.store.process.ProcessModelDAO;
import de.thaso.swa.db.store.process.ProcessStateEntity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.core.Is.is;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * ProcessModelIT
 *
 * @author thaler
 * @since 2017-04-26
 */
public class ProcessModelIT extends DbTestBase {

    @InjectMocks
    private ProcessModelDAO underTest;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testStoreProcessState() throws SQLException {
        updateDatabase("ProcessModelIT/testStoreProcessState.xml");
        // given
        final ModelStateEntity modelStateEntity = entityManager.find(ModelStateEntity.class, 2L);

        final ProcessStateEntity processEntity = new ProcessStateEntity();
        processEntity.setDomainobjectId(4221L);
        processEntity.setDomainobjectName("Foo");
        processEntity.setState(modelStateEntity);
        // when
        entityManager.persist(processEntity);
        // then
        assertThat(processEntity.getId(), is(greaterThanOrEqualTo(1000000L)));

        entityManager.flush();
        final ResultSet resultSet = getConnection().prepareStatement("SELECT COUNT(*) FROM T_PROCESS_STATE WHERE DOMAINOBJECT_ID = 4221").executeQuery();
        resultSet.next();
        assertThat(resultSet.getInt(1),is(1));
    }

    @Test
    public void testFindByName() throws SQLException {
        updateDatabase("ProcessModelIT/testFindByName.xml");
        // when
        final List<ProcessStateEntity> result = underTest.findByDomainObject("Test", 3L);
        // then
        assertThat(result.size(), is(1));
    }
}
