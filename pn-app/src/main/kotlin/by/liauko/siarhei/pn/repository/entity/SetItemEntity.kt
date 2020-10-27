package by.liauko.siarhei.pn.repository.entity

import java.sql.Timestamp
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity(name = "SetItem")
@Table(name = "set_item")
class SetItemEntity (
        val name: String,
        @Column(name = "created_at")
        val createdAt: Timestamp,
        @Column(name = "updated_at")
        val updatedAt: Timestamp,
        val pinned: Boolean
) : BaseEntity<Long>()