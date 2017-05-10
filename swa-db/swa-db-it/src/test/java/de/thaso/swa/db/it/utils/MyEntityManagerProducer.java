package de.thaso.swa.db.it.utils;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;

/**
 * EntityManagerProducer
 *
 * @author thaler
 * @since 13.09.16
 */
@Alternative
public class MyEntityManagerProducer {

    private EntityManager entityManager = PersistenceStoreHelper.getEntityManager();

    @Produces
    public EntityManager createEntityManager() {
        return entityManager;
    }

    protected void closeEntityManager(@Disposes EntityManager entityManager) {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }
}
