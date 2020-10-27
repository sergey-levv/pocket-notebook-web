package by.liauko.siarhei.pn.repository.entity

import java.sql.Timestamp
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.Table

@Entity(name = "Task")
@Table(name = "task")
class TaskEntity (
        val title: String,
        val description: String?,
        @Column(name = "created_at")
        val createdAt: Timestamp,
        @Column(name = "updated_at")
        val updatedAt: Timestamp,
        val pinned: Boolean,
        @Column(name = "execute_at")
        val executeAt: Timestamp?,
        @Column(name = "notify_at")
        val notifyAt: Timestamp?,
        @Column(name = "name", table = "task_group")
        @JoinColumn(name = "group_id")
        val groupName: String?
) : BaseEntity<Long>()