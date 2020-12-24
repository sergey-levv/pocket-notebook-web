package by.liauko.siarhei.pn.service

import by.liauko.siarhei.pn.dto.Account
import by.liauko.siarhei.pn.dto.Credential
import by.liauko.siarhei.pn.dto.Password
import org.springframework.security.core.userdetails.UserDetailsService


/**
 * Interface which handles user-specific data.
 *
 * @see UserDetailsService
 * @author Siarhei Liauko
 * @since 1.0.0
 */
interface AccountService : UserDetailsService {

    /**
     * Save user's credentials.
     *
     * @param credential [Credential] instance containing account username and password.
     *
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    fun createAccount(credential: Credential): Long?

    /**
     * Return account details by username related to it.
     *
     * @param username user's email which identifier user's accoutn.
     *
     * @return [Account] instance containing data about user profile.
     *
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    fun getAccountDetails(username: String): Account

    /**
     * Updating user's password.
     *
     * @param password [Password] instance containing account ID, and new password.
     *
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    fun updatePassword(password: Password)

    /**
     * Deactivates user's account when user want remove their account from application.
     *
     * @param id unique identifier related to user's account to be deactivated.
     *
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    fun deactivateAccount(id: Long)
}
