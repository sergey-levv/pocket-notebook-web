package by.liauko.siarhei.pn.repository.entity

import java.sql.Timestamp
import javax.persistence.Column
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
        val password: String,
        /**
         * Flag determining if user's credential has active status.
         */
        @Column(name = "is_active")
        val isActive: Boolean,
        /**
         * Date and time when user's credential was deactivated.
         */
        @Column(name = "deactivation_date", nullable = true)
        val deactivationDate: Timestamp?
) : BaseEntity<Long>()
