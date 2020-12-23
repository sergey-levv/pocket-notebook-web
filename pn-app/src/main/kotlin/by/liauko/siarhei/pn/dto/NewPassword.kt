package by.liauko.siarhei.pn.dto


/**
 * Data transfer object containing data about user's account unique identifier, old password and new password.
 *
 * @author Siarhei Liauko
 * @since 1.0.0
 */
data class NewPassword (
        /**
         * User's account unique identifier.
         */
        val id: Long,
        /**
         * User's old password.
         */
        val oldPassword: String,
        /**
         * User's new password.
         */
        val newPassword: String
)
