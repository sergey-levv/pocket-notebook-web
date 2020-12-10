package by.liauko.siarhei.pn.repository.hibernate

import by.liauko.siarhei.pn.repository.BaseRepository
import by.liauko.siarhei.pn.repository.SetRepository
import by.liauko.siarhei.pn.repository.entity.SetItemEntity
import org.springframework.stereotype.Repository
import javax.transaction.Transactional


/**
 * [SetRepository] implementation using Hibernate API.
 *
 * @see BaseRepository
 * @see SetRepository
 * @author Siarhei Liauko
 * @since 1.0.0
 */
@Repository
@Transactional
class HibernateSetRepository : BaseRepository(), SetRepository {

    private val selectItemsByNoteId = "from SetItem where note_id = :note_id"

    private val noteIdParameterName = "note_id"

    override fun save(item: SetItemEntity) =
            entityManager.persist(item)

    override fun update(item: SetItemEntity): SetItemEntity =
            entityManager.merge(item)

    override fun delete(item: SetItemEntity) =
            entityManager.remove(item)

    override fun findByNoteId(noteId: Long): List<SetItemEntity> =
            entityManager.createQuery(selectItemsByNoteId, SetItemEntity::class.java)
                    .setParameter(noteIdParameterName, noteId)
                    .resultList
}
