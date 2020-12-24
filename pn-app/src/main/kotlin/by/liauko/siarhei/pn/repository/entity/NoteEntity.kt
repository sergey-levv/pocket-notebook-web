package by.liauko.siarhei.pn.repository.entity

import java.sql.Timestamp
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.Table


/**
 * Entity class describing user note fields.
 *
 * @see BaseEntity
 * @author Siarhei Liauko
 * @since 1.0.0
 */
@Entity(name = "Note")
@Table(name = "note")
class NoteEntity(
        /**
         * Note title - a short text describing note content.
         */
        var title: String,

        /**
         * Note text field which can be used in different ways: for classic notes this field stored main data;
         * for notes containing list of tasks or set items - short description of tasks of items.
         */
        var text: String?,

        /**
         * Date and time when note was created, used for sorting note list.
         */
        @Column(name = "created_at")
        var createdAt: Timestamp,

        /**
         * Date and time when note was updated last time, used for sorting note list.
         */
        @Column(name = "updated_at")
        var updatedAt: Timestamp,

        /**
         * Flag determining if note should be displayed in the top of list, default value is false.
         */
        var pinned: Boolean,

        /**
         * User's credential unique identifier determining for which user note relates, `note` table associate with
         * `credential` table by many to one relationship.
         */
        @JoinColumn(name = "id", table = "credential")
        var credentialId: Long,

        /**
         * Note type unique identifier determining for which type note relates, note types stored in `note_type` table,
         * `note` table associate with it by many to one relationship.
         */
        @JoinColumn(name = "id", table = "note_type")
        var typeId: Long
) : BaseEntity() {

        /**
         * Constructor which creates note with task list.
         */
        constructor(
                title: String,
                text: String?,
                createdAt: Timestamp,
                updatedAt: Timestamp,
                pinned: Boolean,
                credentialId: Long,
                typeId: Long,
                tasks: MutableList<TaskEntity>
        ) : this (title, text, createdAt, updatedAt, pinned, credentialId, typeId) {
                this.tasks = tasks
        }

        /**
         * Constructor which creates note with set items.
         */
        constructor(
                title: String,
                text: String?,
                createdAt: Timestamp,
                updatedAt: Timestamp,
                pinned: Boolean,
                credentialId: Long,
                typeId: Long,
                items: MutableSet<SetItemEntity>
        ) : this (title, text, createdAt, updatedAt, pinned, credentialId, typeId) {
                this.items = items
        }

        /**
         * List of tasks related to note, `note` table associate with `task` table by one to many relationship.
         */
        @OneToMany(mappedBy = "note")
        lateinit var tasks: MutableList<TaskEntity>

        /**
         * Set of items with data related to note, `note` table associate with `set_item` table by one to many relationship.
         */
        @OneToMany(mappedBy = "note")
        lateinit var items: MutableSet<SetItemEntity>
}
