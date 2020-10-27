package by.liauko.siarhei.pn.repository.entity

import java.sql.Timestamp
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Inheritance
import javax.persistence.InheritanceType
import javax.persistence.Table

@Entity(name = "Note")
@Table(name = "note")
@Inheritance(strategy = InheritanceType.JOINED)
class NoteEntity (
        val title: String,
        @Column(name = "created_at")
        val createdAt: Timestamp,
        @Column(name = "updated_at")
        val updatedAt: Timestamp,
        val pinned: Boolean,
        @Column(name = "user_id")
        val userId: Long
) : BaseEntity<Long>()