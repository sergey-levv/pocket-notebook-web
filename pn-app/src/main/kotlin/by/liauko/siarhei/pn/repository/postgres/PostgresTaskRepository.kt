package by.liauko.siarhei.pn.repository.postgres

import by.liauko.siarhei.pn.repository.BaseRepository
import by.liauko.siarhei.pn.repository.TaskRepository
import by.liauko.siarhei.pn.repository.entity.TaskEntity
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional
class PostgresTaskRepository : BaseRepository(), TaskRepository {

    private val selectTasksByNoteId = "from TaskNote where note_id = :note_id"

    private val noteIdParameterName = "note_id"

    override fun save(task: TaskEntity) =
            entityManager.persist(task)

    override fun update(task: TaskEntity): TaskEntity =
            entityManager.merge(task)

    override fun delete(task: TaskEntity) =
            entityManager.remove(task)

    override fun findByNoteId(noteId: Long): List<TaskEntity> =
            entityManager.createQuery(selectTasksByNoteId, TaskEntity::class.java)
                    .setParameter(noteIdParameterName, noteId)
                    .resultList
}
