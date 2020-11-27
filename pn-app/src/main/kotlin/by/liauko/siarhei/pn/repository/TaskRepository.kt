package by.liauko.siarhei.pn.repository

import by.liauko.siarhei.pn.repository.entity.TaskEntity
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional
class TaskRepository : BaseRepository() {

    fun save(task: TaskEntity) =
            entityManager.persist(task)

    fun update(task: TaskEntity) =
            entityManager.merge(task)

    fun delete(task: TaskEntity) =
            entityManager.remove(task)

    fun findByNoteId(noteId: Long) =
            entityManager.createQuery("from TaskNote where note_id = :note_id")
                    .setParameter("note_id", noteId)
                    .resultList.toList()
}
