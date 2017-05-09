package de.thaso.swa.db.store.process;

import de.thaso.swa.db.common.DatabaseError;
import de.thaso.swa.db.common.DatabaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * ProcessModelDAO
 *
 * @author thaler
 * @since 2017-04-26
 */
public class ProcessModelDAO {

    private final static Logger LOG = LoggerFactory.getLogger(ProcessModelDAO.class);

    @Inject
    private EntityManager entityManager;

    public ProcessStateEntity storeProcess(final ProcessStateEntity processEntity) {
        LOG.info("storeProcess with id {} in name {} / {}",
                new Object[]{processEntity.getId(), processEntity.getDomainobjectName(), processEntity.getDomainobjectId()});

        entityManager.persist(processEntity);
        return processEntity;
    }

    public ProcessStateEntity findProcessById(final Long id) {
        LOG.info("findProcessById {}", id);

        final ProcessStateEntity processEntity = entityManager.find(ProcessStateEntity.class, id);
        return processEntity;
    }

    public ProcessStateEntity loadProcessById(final Long id) throws DatabaseException {
        LOG.info("loadProcessById {}", id);
        
        final ProcessStateEntity processEntity = entityManager.find(ProcessStateEntity.class, id);
        if(processEntity == null) {
            throw new DatabaseException(DatabaseError.ENTITY_NOT_FOUND, "Process with id " + id + " not found!");
        }
        return processEntity;
    }

    public List<ProcessStateEntity> findByDomainObject(final String domainObjectName, final Long domainObjectId) {
        LOG.info("findByDomainObject( {}, {} )", domainObjectName, domainObjectId);

        final TypedQuery<ProcessStateEntity> query
                = entityManager.createNamedQuery(ProcessStateEntity.FIND_BY_DOMAINOBJECT, ProcessStateEntity.class);
        query.setParameter("objname", domainObjectName);
        query.setParameter("objid", domainObjectId);
        return query.getResultList();
    }
}
