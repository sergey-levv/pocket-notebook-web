package by.liauko.siarhei.pn.repository.entity

import javax.persistence.Entity
import javax.persistence.PrimaryKeyJoinColumn
import javax.persistence.Table

@Entity(name = "NoteText")
@Table(name = "note_text")
@PrimaryKeyJoinColumn(name = "note_id")
class NoteTextEntity (
        val text: String
) : BaseEntity<Long>()