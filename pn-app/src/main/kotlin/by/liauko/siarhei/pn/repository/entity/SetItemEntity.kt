package by.liauko.siarhei.pn.repository.entity

import java.sql.Timestamp
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table


/**
 * Entity class describing set item fields.
 *
 * @see BaseEntity
 * @author Siarhei Liauko
 * @since 1.0.0
 */
@Entity(name = "SetItem")
@Table(name = "set_item")
class SetItemEntity (
        /**
         * Item name.
         */
        var name: String,

        /**
         * Date and time when item was created, used for sorting item list.
         */
        @Column(name = "created_at")
        val createdAt: Timestamp,

        /**
         * Date and time when item was updated last time, used for sorting item list.
         */
        @Column(name = "updated_at")
        var updatedAt: Timestamp,

        /**
         * Flag determining if item should be displayed in the top of list, default value is false.
         */
        var pinned: Boolean,

        /**
         * The note to which the items belong, `set_item` table associate with `note` table by many to one relationship,
         * `note_id` is a connecting field.
         */
        @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        @JoinColumn(name = "note_id")
        var note: NoteEntity?
) : BaseEntity()
