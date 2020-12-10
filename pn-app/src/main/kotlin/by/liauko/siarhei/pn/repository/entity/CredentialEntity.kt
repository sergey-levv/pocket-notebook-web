package by.liauko.siarhei.pn.repository.entity

import javax.persistence.Entity
import javax.persistence.Table


/**
 * Entity class describing credential fields.
 *
 * @see BaseEntity
 * @author Siarhei Liauko
 * @since 1.0.0
 */
@Entity(name = "Credential")
@Table(name = "credential")
class CredentialEntity (
        /**
         * User's email address used to user identification.
         */
        val email: String,

        /**
         * User's password hashing result.
         */
        val password: String
) : BaseEntity<Long>()
