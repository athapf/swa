package de.thaso.swa.db.workshop.cart;

import de.thaso.swa.db.common.exception.DatabaseError;
import de.thaso.swa.db.common.exception.DatabaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * WorkshopDAO
 *
 * @author thaler
 * @since 2017-12-11
 */
public class WorkshopDAO {

    private final static Logger LOG = LoggerFactory.getLogger(WorkshopDAO.class);

    @Inject
    private EntityManager entityManager;

    public WorkshopEntity storeWorkshop(final WorkshopEntity workshopEntity) {
        LOG.info("storeWorkshop with id {} and number {}", workshopEntity.getId(), workshopEntity.getNumber());

        entityManager.persist(workshopEntity);
        return workshopEntity;
    }

    public WorkshopEntity findWorkshopById(final Long id) {
        LOG.info("findWorkshopById {}", id);

        final WorkshopEntity workshopEntity = entityManager.find(WorkshopEntity.class, id);
        return workshopEntity;
    }

    public WorkshopEntity loadWorkshopById(final Long id) throws DatabaseException {
        LOG.info("loadWorkshopById {}", id);
        
        final WorkshopEntity workshopEntity = entityManager.find(WorkshopEntity.class, id);
        if(workshopEntity == null) {
            throw new DatabaseException(DatabaseError.ENTITY_NOT_FOUND, "Workshop with id " + id + " not found!");
        }
        return workshopEntity;
    }

    public WorkshopEntity findByNumber(final Integer number) {
        LOG.info("findByNumber( {} )", number);

        final TypedQuery<WorkshopEntity> query
                = entityManager.createNamedQuery(WorkshopEntity.FIND_BY_NUMBER, WorkshopEntity.class);
        query.setParameter("number", number);
        return query.getSingleResult();
    }

    public List<WorkshopEntity> findByTitle(final String title) {
        LOG.info("findByTitle( {} )", title);

        final TypedQuery<WorkshopEntity> query
                = entityManager.createNamedQuery(WorkshopEntity.FIND_BY_TITLE, WorkshopEntity.class);
        query.setParameter("title", title);
        query.setMaxResults(10);
        return query.getResultList();
    }
}
