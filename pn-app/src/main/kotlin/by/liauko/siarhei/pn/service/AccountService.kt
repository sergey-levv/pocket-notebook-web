package by.liauko.siarhei.pn.service

import by.liauko.siarhei.pn.dto.Account
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
     * @param account - [Account] instance containing account username and password.
     *
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    fun createAccount(account: Account): Long?

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
}
