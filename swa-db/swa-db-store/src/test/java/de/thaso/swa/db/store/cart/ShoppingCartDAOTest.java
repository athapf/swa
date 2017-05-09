package de.thaso.swa.db.store.cart;

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
 * ShoppingCartDAOTest
 *
 * @author thaler
 * @since 13.09.16
 */
public class ShoppingCartDAOTest {

    public static final DatabaseExceptionCodeMatcher EXCEPTION_MATCHER_ENTITY_NOT_FOUND
            = new DatabaseExceptionCodeMatcher(DatabaseError.ENTITY_NOT_FOUND);

    @InjectMocks
    private ShoppingCartDAO underTest;

    @Mock
    private EntityManager entityManager;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private Long primaryKey;
    private ShoppingCartEntity shoppingCartEntity;

    @Before
    public void setUp() {
        initMocks(this);

        primaryKey = 1L;
        shoppingCartEntity = new ShoppingCartEntity();
        when(entityManager.find(ShoppingCartEntity.class, primaryKey)).thenReturn(shoppingCartEntity);
    }

    @Test
    public void storeShoppingCart() {
        // when
        final ShoppingCartEntity result = underTest.storeShoppingCart(shoppingCartEntity);
        // then
        verify(entityManager).persist(shoppingCartEntity);
        assertThat(result, is(shoppingCartEntity));
    }

    @Test
    public void findShoppingCart() {
        // when
        final ShoppingCartEntity result = underTest.findShoppingCartById(primaryKey);
        // then
        assertThat(result, is(shoppingCartEntity));
    }

    @Test
    public void findShoppingCart_whenPrimaryKeyNotFound() {
        // given
        when(entityManager.find(ShoppingCartEntity.class, primaryKey)).thenReturn(null);
        // when
        final ShoppingCartEntity result = underTest.findShoppingCartById(primaryKey);
        // then
        assertThat(result, is(nullValue()));
    }

    @Test
    public void loadShoppingCart() {
        // when
        final ShoppingCartEntity result = underTest.loadShoppingCartById(primaryKey);
        // then
        assertThat(result, is(shoppingCartEntity));
    }

    @Test
    public void loadShoppingCart_whenPrimaryKeyNotFound() {
        // given
        when(entityManager.find(ShoppingCartEntity.class, primaryKey)).thenReturn(null);
        exception.expect(DatabaseException.class);
        exception.expectMessage(containsString(" " + primaryKey.toString() + " "));
        exception.expect(EXCEPTION_MATCHER_ENTITY_NOT_FOUND);
        // when
        final ShoppingCartEntity result = underTest.loadShoppingCartById(primaryKey);
    }

    @Test
    public void findByName() {
        // given
        final TypedQuery query = mock(TypedQuery.class);
        when(entityManager.createNamedQuery(ShoppingCartEntity.FIND_BY_NAMES,ShoppingCartEntity.class)).thenReturn(query);
        final List<ShoppingCartEntity> messageEntityList = new ArrayList<>();
        when(query.getResultList()).thenReturn(messageEntityList);
        // when
        final List<ShoppingCartEntity> result = underTest.findByName("Hugo");
        // then
        assertThat(result,is(messageEntityList));
        verify(query).setParameter("name","Hugo");
        verify(query).setMaxResults(10);
    }
}