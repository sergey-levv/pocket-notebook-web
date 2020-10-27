package by.liauko.siarhei.pn.repository.entity

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.PrimaryKeyJoinColumn

@Entity(name = "TaskList")
@PrimaryKeyJoinColumn(name = "note_id")
class TaskListEntity (
        @OneToMany(cascade = [CascadeType.ALL])
        @JoinColumn(name = "note_id", table = "task")
        val items: List<TaskEntity>
)