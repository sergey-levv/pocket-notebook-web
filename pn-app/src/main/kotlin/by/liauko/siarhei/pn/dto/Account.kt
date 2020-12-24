package by.liauko.siarhei.pn.dto

import java.util.*


/**
 * Data transfer object containing data about user's account
 *
 * @author Siarhei Liauko
 * @since 1.0.0
 */
data class Account(
        /**
         * User credentials unique identifier.
         */
        val id: UUID,
        /**
         * User's email which used as username.
         */
        val username: String,
        /**
         * User's password.
         */
        var password: String,
        /**
         * Flag determining if user account activated
         */
        val isActive: Boolean
)
