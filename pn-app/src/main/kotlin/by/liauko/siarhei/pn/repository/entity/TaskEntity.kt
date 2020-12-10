package by.liauko.siarhei.pn.repository.entity

import java.sql.Timestamp
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.PrimaryKeyJoinColumn
import javax.persistence.SecondaryTable
import javax.persistence.Table


/**
 * Entity class describing task fields.
 *
 * @see BaseEntity
 * @author Siarhei Liauko
 * @since 1.0.0
 */
@Entity(name = "Task")
@Table(name = "task")
@SecondaryTable(name = "task_group", pkJoinColumns = [PrimaryKeyJoinColumn(referencedColumnName = "id")])
class TaskEntity (
        /**
         * Task title - a short text describing task.
         */
        var title: String,

        /**
         * Text describing task details.
         */
        var description: String?,

        /**
         * Date and time when task was created, used for sorting task list.
         */
        @Column(name = "created_at")
        val createdAt: Timestamp,

        /**
         * Date and time when task was updated last time, used for sorting task list.
         */
        @Column(name = "updated_at")
        var updatedAt: Timestamp,

        /**
         * Flag determining if task should be displayed in the top of list, default value is false.
         */
        var pinned: Boolean,

        /**
         * Date and time when task should be executed.
         */
        @Column(name = "execute_at")
        var executeAt: Timestamp?,

        /**
         * Date and time when notification about task execution should be displayed.
         */
        @Column(name = "notify_at")
        var notifyAt: Timestamp?,

        /**
         * Task group unique identifier determining for which group task relates, `task` table associate with
         * `task_group` table by many to one relationship, `group_id` is a connecting field.
         */
        @ManyToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "group_id", nullable = true)
        var group: TaskGroupEntity?,

        /**
         * The note to which the task belong, `task` table associate with `note` table by many to one relationship,
         * `note_id` is a connecting field.
         */
        @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        @JoinColumn(name = "note_id")
        var note: NoteEntity?
) : BaseEntity<Long>()
