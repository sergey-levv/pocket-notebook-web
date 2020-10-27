package by.liauko.siarhei.pn.repository.entity

import javax.persistence.Entity
import javax.persistence.Table

@Entity(name = "User")
@Table(name = "user")
class UserEntity (
        val email: String,
        val password: String
) : BaseEntity<Long>()