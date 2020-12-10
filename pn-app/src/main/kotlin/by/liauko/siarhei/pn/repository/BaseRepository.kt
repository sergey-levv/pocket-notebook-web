package by.liauko.siarhei.pn.repository

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext


/**
 * Abstract repository class with common fields for all repositories.
 *
 * @author Siarhei Liauko
 * @since 1.0.0
 */
abstract class BaseRepository {

    /**
     * Interface used to interact with the persistence context.
     */
    @PersistenceContext
    protected lateinit var entityManager: EntityManager
}
