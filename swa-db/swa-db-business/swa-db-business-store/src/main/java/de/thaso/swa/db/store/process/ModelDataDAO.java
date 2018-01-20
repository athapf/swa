package de.thaso.swa.db.store.process;

import de.thaso.swa.db.common.exception.DatabaseError;
import de.thaso.swa.db.common.exception.DatabaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * ModelDataDAO
 *
 * @author thaler
 * @since 2017-04-25
 */
public class ModelDataDAO {

    private final static Logger LOG = LoggerFactory.getLogger(ModelDataDAO.class);

    @Inject
    private EntityManager entityManager;

    public ModelStateEntity findModelStateById(final Long id) {
        final ModelStateEntity modelStateEntity = entityManager.find(ModelStateEntity.class, id);
        return modelStateEntity;
    }

    public ModelStateEntity loadModelStateById(final Long id) throws DatabaseException {
        final ModelStateEntity modelStateEntity = entityManager.find(ModelStateEntity.class, id);
        if(modelStateEntity == null) {
            throw new DatabaseException(DatabaseError.ENTITY_NOT_FOUND, "Process with id " + id + " not found!");
        }
        return modelStateEntity;
    }

    public ModelStateEntity findModelStateByState(final String state, final String graph) throws DatabaseException {
        final TypedQuery<ModelStateEntity> query
                = entityManager.createNamedQuery(ModelStateEntity.FIND_BY_STATE, ModelStateEntity.class);
        query.setParameter("state", state);
        query.setParameter("graph", graph);
        return query.getSingleResult();
    }

    public List<ModelActionEntity> findAllPossibleActionsFromState(final String state, final String graph) throws DatabaseException {
        final TypedQuery<ModelActionEntity> query
                = entityManager.createNamedQuery(ModelActionEntity.FIND_ALL_POSSIBLE_ACTIONS, ModelActionEntity.class);
        query.setParameter("state", state);
        query.setParameter("graph", graph);
        return query.getResultList();
    }

    public List<ModelActionEntity> findPossiblePublicActionsFromState(final String state, final String graph) throws DatabaseException {
        final TypedQuery<ModelActionEntity> query
                = entityManager.createNamedQuery(ModelActionEntity.FIND_POSSIBLE_PUBLIC_ACTIONS, ModelActionEntity.class);
        query.setParameter("state", state);
        query.setParameter("graph", graph);
        return query.getResultList();
    }
}
