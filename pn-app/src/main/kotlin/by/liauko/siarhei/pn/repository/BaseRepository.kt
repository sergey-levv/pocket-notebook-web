package by.liauko.siarhei.pn.repository

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext


abstract class BaseRepository {

    @PersistenceContext
    lateinit var entityManager: EntityManager
}
