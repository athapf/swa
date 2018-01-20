package de.thaso.swa.db.notes;

import de.thaso.swa.db.common.exception.DatabaseError;
import de.thaso.swa.db.common.exception.DatabaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class NoteDAO {
    private final static Logger LOG = LoggerFactory.getLogger(NoteDAO.class);

    @Inject
    private EntityManager entityManager;

    public NoteEntity storeNote(final NoteEntity noteEntity) {
        LOG.info("storeNote with id {} and number {}", noteEntity.getId(), noteEntity.getNumber());

        entityManager.persist(noteEntity);
        return noteEntity;
    }

    public NoteEntity findNoteById(final Long id) {
        LOG.info("findNoteById {}", id);

        final NoteEntity noteEntity = entityManager.find(NoteEntity.class, id);
        return noteEntity;
    }

    public NoteEntity loadNoteById(final Long id) throws DatabaseException {
        LOG.info("loadNoteById {}", id);

        final NoteEntity noteEntity = entityManager.find(NoteEntity.class, id);
        if(noteEntity == null) {
            throw new DatabaseException(DatabaseError.ENTITY_NOT_FOUND, "Note with id " + id + " not found!");
        }
        return noteEntity;
    }

    public NoteEntity findByNumber(final Integer number) {
        LOG.info("findByNumber( {} )", number);

        final TypedQuery<NoteEntity> query
                = entityManager.createNamedQuery(NoteEntity.FIND_BY_NUMBER, NoteEntity.class);
        query.setParameter("number", number);
        return query.getSingleResult();
    }

    public List<NoteEntity> findByTitle(final String title) {
        LOG.info("findByTitle( {} )", title);

        final TypedQuery<NoteEntity> query
                = entityManager.createNamedQuery(NoteEntity.FIND_BY_TITLE, NoteEntity.class);
        query.setParameter("title", title);
        query.setMaxResults(10);
        return query.getResultList();
    }
}
