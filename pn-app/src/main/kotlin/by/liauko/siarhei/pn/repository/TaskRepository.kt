package by.liauko.siarhei.pn.repository

import by.liauko.siarhei.pn.repository.entity.TaskEntity


/**
 * Interface used to manage task entities in database.
 *
 * @see TaskEntity
 * @author Siarhei Liauko
 * @since 1.0.0
 */
interface TaskRepository {

    /**
     * Save task data to database.
     *
     * @param task - [TaskEntity] instance containing data about task.
     *
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    fun save(task: TaskEntity)

    /**
     * Update information about task with a certain ID in database.
     *
     * @param task - [TaskEntity] instance containing updated task data.
     *
     * @return [TaskEntity] instance.
     *
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    fun update(task: TaskEntity): TaskEntity

    /**
     * Remove task with a certain ID from database.
     *
     * @param task - [TaskEntity] instance containing a task ID to be removed.
     *
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    fun delete(task: TaskEntity)

    /**
     * Find all tasks related to certain note.
     *
     * @param noteId - note unique identifier for filtering tasks.
     *
     * @return list of [TaskEntity] containing data about all tasks related to a certain note entity.
     *
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    fun findByNoteId(noteId: Long): List<TaskEntity>
}
