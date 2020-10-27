package by.liauko.siarhei.pn.repository.entity

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.PrimaryKeyJoinColumn

@Entity(name = "Set")
@PrimaryKeyJoinColumn(name = "note_id")
class SetEntity (
        @OneToMany(cascade = [CascadeType.ALL])
        @JoinColumn(name = "note_id", table = "set_item")
        val items: List<SetItemEntity>
) : BaseEntity<Long>()