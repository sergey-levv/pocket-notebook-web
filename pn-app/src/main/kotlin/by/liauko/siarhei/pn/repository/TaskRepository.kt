package by.liauko.siarhei.pn.repository

import by.liauko.siarhei.pn.repository.entity.TaskEntity


interface TaskRepository {

    fun save(task: TaskEntity)

    fun update(task: TaskEntity): TaskEntity

    fun delete(task: TaskEntity)

    fun findByNoteId(noteId: Long): List<TaskEntity>
}