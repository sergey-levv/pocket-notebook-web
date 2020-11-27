package by.liauko.siarhei.pn.repository

import by.liauko.siarhei.pn.repository.entity.SetItemEntity
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional
class SetRepository : BaseRepository() {

    fun save(item: SetItemEntity) =
            entityManager.persist(item)

    fun update(item: SetItemEntity) =
            entityManager.merge(item)

    fun delete(item: SetItemEntity) =
            entityManager.remove(item)

    fun findByNoteId(noteId: Long) =
            entityManager.createQuery("from SetItem where note_id = :note_id")
                    .setParameter("note_id", noteId)
                    .resultList.toList()
}
