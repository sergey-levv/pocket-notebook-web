package by.liauko.siarhei.pn.dto


/**
 * Data transfer object containing data about user's credential.
 *
 * @author Siarhei Liauko
 * @since 1.0.0
 */
data class Credential(
        /**
         * User's email which used as username.
         */
        val username: String,
        /**
         * User's password.
         */
        var password: String
)
