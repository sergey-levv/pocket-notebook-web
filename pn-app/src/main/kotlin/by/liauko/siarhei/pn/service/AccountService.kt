package by.liauko.siarhei.pn.service

import by.liauko.siarhei.pn.dto.Account
import by.liauko.siarhei.pn.dto.Credential
import by.liauko.siarhei.pn.dto.NewPassword
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
     * @param credential - [Credential] instance containing account username and password.
     *
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    fun createAccount(credential: Credential): Long?

    /**
     * Return account details by username related to it.
     *
     * @param username - user's email which identifier user's accoutn.
     *
     * @return [Account] instance containing data about user profile.
     *
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    fun getAccountDetails(username: String): Account

    /**
     * Updating user's password after verification.
     *
     * @param newPassword - [NewPassword] instance containing account ID,
     * old password for verification and new password.
     *
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    fun updatePassword(newPassword: NewPassword)

    /**
     * Deactivates user's account when user want remove their account from application.
     *
     * @param account - user's account which should be deactivated and contains user's id and password to verification.
     *
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    fun deactivateAccount(account: Account)
}
