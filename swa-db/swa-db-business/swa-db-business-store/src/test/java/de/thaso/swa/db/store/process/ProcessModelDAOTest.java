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
 * ProcessModelDAOTest
 *
 * @author thaler
 * @since 2017-04-25
 */
public class ProcessModelDAOTest {

    public static final DatabaseExceptionCodeMatcher EXCEPTION_MATCHER_ENTITY_NOT_FOUND
            = new DatabaseExceptionCodeMatcher(DatabaseError.ENTITY_NOT_FOUND);

    @InjectMocks
    private ProcessModelDAO underTest;

    @Mock
    private EntityManager entityManager;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private Long primaryKey;
    private ProcessStateEntity processEntity;

    @Before
    public void setUp() {
        initMocks(this);

        primaryKey = 1L;
        processEntity = new ProcessStateEntity();
        when(entityManager.find(ProcessStateEntity.class, primaryKey)).thenReturn(processEntity);
    }

    @Test
    public void storeProcess() {
        // when
        final ProcessStateEntity result = underTest.storeProcess(processEntity);
        // then
        verify(entityManager).persist(processEntity);
        assertThat(result, is(processEntity));
    }

    @Test
    public void findProcess() {
        // when
        final ProcessStateEntity result = underTest.findProcessById(primaryKey);
        // then
        assertThat(result, is(processEntity));
    }

    @Test
    public void findProcess_whenPrimaryKeyNotFound() {
        // given
        when(entityManager.find(ProcessStateEntity.class, primaryKey)).thenReturn(null);
        // when
        final ProcessStateEntity result = underTest.findProcessById(primaryKey);
        // then
        assertThat(result, is(nullValue()));
    }

    @Test
    public void loadProcess() {
        // when
        final ProcessStateEntity result = underTest.loadProcessById(primaryKey);
        // then
        assertThat(result, is(processEntity));
    }

    @Test
    public void loadProcess_whenPrimaryKeyNotFound() {
        // given
        when(entityManager.find(ProcessStateEntity.class, primaryKey)).thenReturn(null);
        exception.expect(DatabaseException.class);
        exception.expectMessage(containsString(" " + primaryKey.toString() + " "));
        exception.expect(EXCEPTION_MATCHER_ENTITY_NOT_FOUND);
        // when
        final ProcessStateEntity result = underTest.loadProcessById(primaryKey);
    }

    @Test
    public void findByName() {
        // given
        final TypedQuery query = mock(TypedQuery.class);
        when(entityManager.createNamedQuery(ProcessStateEntity.FIND_BY_DOMAINOBJECT, ProcessStateEntity.class)).thenReturn(query);
        final List<ProcessStateEntity> processEntityList = new ArrayList<>();
        when(query.getResultList()).thenReturn(processEntityList);
        // when
        final List<ProcessStateEntity> result = underTest.findByDomainObject("Hugo", 123L);
        // then
        assertThat(result,is(processEntityList));
        verify(query).setParameter("objname","Hugo");
        verify(query).setParameter("objid",123L);
    }
}