package de.thaso.swa.db.notes;

import de.thaso.swa.db.common.exception.DatabaseError;
import de.thaso.swa.db.common.exception.DatabaseException;
import de.thaso.swa.db.notes.utils.DatabaseExceptionCodeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class NoteDAOTest {

    public static final DatabaseExceptionCodeMatcher EXCEPTION_MATCHER_ENTITY_NOT_FOUND
            = new DatabaseExceptionCodeMatcher(DatabaseError.ENTITY_NOT_FOUND);

    @InjectMocks
    private NoteDAO underTest;

    @Mock
    private EntityManager entityManager;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private Long primaryKey;
    private NoteEntity noteEntity;

    @Before
    public void setUp() {
        initMocks(this);

        primaryKey = 1L;
        noteEntity = new NoteEntity();
        when(entityManager.find(NoteEntity.class, primaryKey)).thenReturn(noteEntity);
    }

    @Test
    public void storeNote() {
        // when
        final NoteEntity result = underTest.storeNote(noteEntity);
        // then
        verify(entityManager).persist(noteEntity);
        assertThat(result, is(noteEntity));
    }

    @Test
    public void findNote() {
        // when
        final NoteEntity result = underTest.findNoteById(primaryKey);
        // then
        assertThat(result, is(noteEntity));
    }

    @Test
    public void findNote_whenPrimaryKeyNotFound() {
        // given
        when(entityManager.find(NoteEntity.class, primaryKey)).thenReturn(null);
        // when
        final NoteEntity result = underTest.findNoteById(primaryKey);
        // then
        assertThat(result, is(nullValue()));
    }

    @Test
    public void loadNote() {
        // when
        final NoteEntity result = underTest.loadNoteById(primaryKey);
        // then
        assertThat(result, is(noteEntity));
    }

    @Test
    public void loadNote_whenPrimaryKeyNotFound() {
        // given
        when(entityManager.find(NoteEntity.class, primaryKey)).thenReturn(null);
        exception.expect(DatabaseException.class);
        exception.expectMessage(containsString(" " + primaryKey.toString() + " "));
        exception.expect(EXCEPTION_MATCHER_ENTITY_NOT_FOUND);
        // when
        final NoteEntity result = underTest.loadNoteById(primaryKey);
    }

    @Test
    public void findByNumber() {
        // given
        final TypedQuery query = mock(TypedQuery.class);
        when(entityManager.createNamedQuery(NoteEntity.FIND_BY_NUMBER,NoteEntity.class)).thenReturn(query);
        final NoteEntity noteEntity = new NoteEntity();
        when(query.getSingleResult()).thenReturn(noteEntity);
        // when
        final NoteEntity result = underTest.findByNumber(1234);
        // then
        assertThat(result,is(noteEntity));
        verify(query).setParameter("number",1234);
    }

    @Test
    public void findByTitle() {
        // given
        final TypedQuery query = mock(TypedQuery.class);
        when(entityManager.createNamedQuery(NoteEntity.FIND_BY_TITLE,NoteEntity.class)).thenReturn(query);
        final List<NoteEntity> noteEntityList = new ArrayList<>();
        when(query.getResultList()).thenReturn(noteEntityList);
        // when
        final List<NoteEntity> result = underTest.findByTitle("Hugo");
        // then
        assertThat(result,is(noteEntityList));
        verify(query).setParameter("title","Hugo");
        verify(query).setMaxResults(10);
    }
}