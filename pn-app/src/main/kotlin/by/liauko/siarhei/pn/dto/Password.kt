package by.liauko.siarhei.pn.dto

import java.util.*


/**
 * Data transfer object containing data about user's account unique identifier and new password.
 *
 * @author Siarhei Liauko
 * @since 1.0.0
 */
data class Password (
        /**
         * User's account unique identifier.
         */
        val id: UUID,
        /**
         * User's new password.
         */
        val password: String
)
