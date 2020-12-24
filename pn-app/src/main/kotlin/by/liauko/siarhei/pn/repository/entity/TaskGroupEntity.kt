package by.liauko.siarhei.pn.repository.entity

import javax.persistence.Entity
import javax.persistence.Table


/**
 * Entity class describing task group fields.
 *
 * @see BaseEntity
 * @author Siarhei Liauko
 * @since 1.0.0
 */
@Entity(name = "TaskGroup")
@Table(name = "task_group")
class TaskGroupEntity (
        /**
         * Group name.
         */
        val name: String
) : BaseEntity()
