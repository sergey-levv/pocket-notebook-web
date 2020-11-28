package by.liauko.siarhei.pn.repository.entity

import javax.persistence.Entity
import javax.persistence.Table

@Entity(name = "NoteType")
@Table(name = "note_type")
class NoteTypeEntity (
        val type: String
) : BaseEntity<Long>()
