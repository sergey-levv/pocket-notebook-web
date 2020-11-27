package by.liauko.siarhei.pn.repository.entity

import java.sql.Timestamp
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity(name = "SetItem")
@Table(name = "set_item")
class SetItemEntity (
        var name: String,
        @Column(name = "created_at")
        val createdAt: Timestamp,
        @Column(name = "updated_at")
        var updatedAt: Timestamp,
        var pinned: Boolean,
        @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        @JoinColumn(name = "note_id")
        var note: NoteEntity?
) : BaseEntity<Long>()
