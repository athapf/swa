package de.thaso.swa.db.store.process;

import de.thaso.swa.db.common.exception.DatabaseError;
import de.thaso.swa.db.common.exception.DatabaseException;
import de.thaso.swa.db.store.utils.DatabaseExceptionCodeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.persistence.EntityManager;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * ModelDataDAOTest
 *
 * @author thaler
 * @since 2017-04-25
 */
public class ModelDataDAOTest {

    public static final DatabaseExceptionCodeMatcher EXCEPTION_MATCHER_ENTITY_NOT_FOUND
            = new DatabaseExceptionCodeMatcher(DatabaseError.ENTITY_NOT_FOUND);

    @InjectMocks
    private ModelDataDAO underTest;

    @Mock
    private EntityManager entityManager;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private Long primaryKey;
    private ModelStateEntity modelStateEntity;

    @Before
    public void setUp() {
        initMocks(this);

        primaryKey = 1L;
        modelStateEntity = new ModelStateEntity();
        when(entityManager.find(ModelStateEntity.class, primaryKey)).thenReturn(modelStateEntity);
    }

    @Test
    public void findModel() {
        // when
        final ModelStateEntity result = underTest.findModelStateById(primaryKey);
        // then
        assertThat(result, is(modelStateEntity));
    }

    @Test
    public void findModel_whenPrimaryKeyNotFound() {
        // given
        when(entityManager.find(ModelStateEntity.class, primaryKey)).thenReturn(null);
        // when
        final ModelStateEntity result = underTest.findModelStateById(primaryKey);
        // then
        assertThat(result, is(nullValue()));
    }

    @Test
    public void loadModel() {
        // when
        final ModelStateEntity result = underTest.loadModelStateById(primaryKey);
        // then
        assertThat(result, is(modelStateEntity));
    }

    @Test
    public void loadModel_whenPrimaryKeyNotFound() {
        // given
        when(entityManager.find(ModelStateEntity.class, primaryKey)).thenReturn(null);
        exception.expect(DatabaseException.class);
        exception.expectMessage(containsString(" " + primaryKey.toString() + " "));
        exception.expect(EXCEPTION_MATCHER_ENTITY_NOT_FOUND);
        // when
        final ModelStateEntity result = underTest.loadModelStateById(primaryKey);
    }
}