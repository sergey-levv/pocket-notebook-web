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

@Entity(name = "Task")
@Table(name = "task")
@SecondaryTable(name = "task_group", pkJoinColumns = [PrimaryKeyJoinColumn(referencedColumnName = "id")])
class TaskEntity (
        var title: String,
        var description: String?,
        @Column(name = "created_at")
        val createdAt: Timestamp,
        @Column(name = "updated_at")
        var updatedAt: Timestamp,
        var pinned: Boolean,
        @Column(name = "execute_at")
        var executeAt: Timestamp?,
        @Column(name = "notify_at")
        var notifyAt: Timestamp?,
        @ManyToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "group_id", nullable = true)
        var group: TaskGroupEntity?,
        @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        @JoinColumn(name = "note_id")
        var note: NoteEntity?
) : BaseEntity<Long>()
