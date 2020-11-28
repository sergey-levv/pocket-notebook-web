package by.liauko.siarhei.pn.repository.entity

import java.sql.Timestamp
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity(name = "Note")
@Table(name = "note")
class NoteEntity(
        var title: String,
        var text: String?,
        @Column(name = "created_at")
        var createdAt: Timestamp,
        @Column(name = "updated_at")
        var updatedAt: Timestamp,
        var pinned: Boolean,
        @JoinColumn(name = "id", table = "credential")
        var credentialId: Long,
        @JoinColumn(name = "id", table = "note_type")
        var typeId: Long
) : BaseEntity<Long>() {

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



        @OneToMany(mappedBy = "note")
        lateinit var tasks: MutableList<TaskEntity>

        @OneToMany(mappedBy = "note")
        lateinit var items: MutableSet<SetItemEntity>
}
