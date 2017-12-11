package de.thaso.swa.db.store.cart;

import de.thaso.swa.db.common.exception.DatabaseError;
import de.thaso.swa.db.common.exception.DatabaseException;
import de.thaso.swa.db.store.utils.DatabaseExceptionCodeMatcher;
import de.thaso.swa.db.workshop.cart.WorkshopDAO;
import de.thaso.swa.db.workshop.cart.WorkshopEntity;
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
 * WorkshopDAOTest
 *
 * @author thaler
 * @since 2017-12-11
 */
public class WorkshopDAOTest {

    public static final DatabaseExceptionCodeMatcher EXCEPTION_MATCHER_ENTITY_NOT_FOUND
            = new DatabaseExceptionCodeMatcher(DatabaseError.ENTITY_NOT_FOUND);

    @InjectMocks
    private WorkshopDAO underTest;

    @Mock
    private EntityManager entityManager;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private Long primaryKey;
    private WorkshopEntity workshopEntity;

    @Before
    public void setUp() {
        initMocks(this);

        primaryKey = 1L;
        workshopEntity = new WorkshopEntity();
        when(entityManager.find(WorkshopEntity.class, primaryKey)).thenReturn(workshopEntity);
    }

    @Test
    public void storeWorkshop() {
        // when
        final WorkshopEntity result = underTest.storeWorkshop(workshopEntity);
        // then
        verify(entityManager).persist(workshopEntity);
        assertThat(result, is(workshopEntity));
    }

    @Test
    public void findWorkshop() {
        // when
        final WorkshopEntity result = underTest.findWorkshopById(primaryKey);
        // then
        assertThat(result, is(workshopEntity));
    }

    @Test
    public void findWorkshop_whenPrimaryKeyNotFound() {
        // given
        when(entityManager.find(WorkshopEntity.class, primaryKey)).thenReturn(null);
        // when
        final WorkshopEntity result = underTest.findWorkshopById(primaryKey);
        // then
        assertThat(result, is(nullValue()));
    }

    @Test
    public void loadWorkshop() {
        // when
        final WorkshopEntity result = underTest.loadWorkshopById(primaryKey);
        // then
        assertThat(result, is(workshopEntity));
    }

    @Test
    public void loadWorkshop_whenPrimaryKeyNotFound() {
        // given
        when(entityManager.find(WorkshopEntity.class, primaryKey)).thenReturn(null);
        exception.expect(DatabaseException.class);
        exception.expectMessage(containsString(" " + primaryKey.toString() + " "));
        exception.expect(EXCEPTION_MATCHER_ENTITY_NOT_FOUND);
        // when
        final WorkshopEntity result = underTest.loadWorkshopById(primaryKey);
    }

    @Test
    public void findByNumber() {
        // given
        final TypedQuery query = mock(TypedQuery.class);
        when(entityManager.createNamedQuery(WorkshopEntity.FIND_BY_NUMBER,WorkshopEntity.class)).thenReturn(query);
        final WorkshopEntity workshopEntity = new WorkshopEntity();
        when(query.getSingleResult()).thenReturn(workshopEntity);
        // when
        final WorkshopEntity result = underTest.findByNumber(1234);
        // then
        assertThat(result,is(workshopEntity));
        verify(query).setParameter("number",1234);
    }

    @Test
    public void findByTitle() {
        // given
        final TypedQuery query = mock(TypedQuery.class);
        when(entityManager.createNamedQuery(WorkshopEntity.FIND_BY_TITLE,WorkshopEntity.class)).thenReturn(query);
        final List<WorkshopEntity> workshopEntityList = new ArrayList<>();
        when(query.getResultList()).thenReturn(workshopEntityList);
        // when
        final List<WorkshopEntity> result = underTest.findByTitle("Hugo");
        // then
        assertThat(result,is(workshopEntityList));
        verify(query).setParameter("title","Hugo");
        verify(query).setMaxResults(10);
    }
}