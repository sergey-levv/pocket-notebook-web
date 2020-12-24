package by.liauko.siarhei.pn.repository.entity

import javax.persistence.Entity
import javax.persistence.Table


/**
 * Entity class describing note type fields.
 *
 * @see BaseEntity
 * @author Siarhei Liauko
 * @since 1.0.0
 */
@Entity(name = "NoteType")
@Table(name = "note_type")
class NoteTypeEntity (
        /**
         * Note type name.
         */
        val type: String
) : BaseEntity()
