package by.liauko.siarhei.pn.repository.entity

import javax.persistence.Entity
import javax.persistence.Table

@Entity(name = "Credential")
@Table(name = "credential")
class CredentialEntity (
        val email: String,
        val password: String
) : BaseEntity<Long>()
