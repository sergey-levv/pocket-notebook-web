package by.liauko.siarhei.pn.repository.entity

import javax.persistence.Entity
import javax.persistence.Table

@Entity(name = "TaskGroup")
@Table(name = "task_group")
class TaskGroupEntity (
        val name: String
)