package by.liauko.siarhei.pn.service.impl

import by.liauko.siarhei.pn.dto.Account
import by.liauko.siarhei.pn.dto.Credential
import by.liauko.siarhei.pn.dto.NewPassword
import by.liauko.siarhei.pn.repository.CredentialRepository
import by.liauko.siarhei.pn.repository.entity.CredentialEntity
import by.liauko.siarhei.pn.service.AccountService
import by.liauko.siarhei.pn.service.exception.AccountAlreadyExistsException
import by.liauko.siarhei.pn.service.exception.CredentialsVerificationException
import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder
import org.springframework.stereotype.Service
import javax.persistence.NoResultException


/**
 * Basic [AccountService] implementation.
 *
 * @author Siarhei Liauko
 * @since 1.0.0
 */
@Service
class AccountServiceImpl : AccountService {

    private val log = LogManager.getLogger(AccountService::class)

    @Autowired
    private lateinit var passwordEncoder: Pbkdf2PasswordEncoder
    @Autowired
    private lateinit var credentialRepository: CredentialRepository

    override fun createAccount(credential: Credential): Long? {
        if (credentialRepository.isEmailExist(credential.username)) {
            log.warn("Account with email ${credential.username} already exist in database")
            throw AccountAlreadyExistsException("Account with email ${credential.username} already exist in database")
        }
        credential.password = passwordEncoder.encode(credential.password)
        val credentialEntity = CredentialEntity(credential.username, credential.password, false, null)
        credentialRepository.save(credentialEntity)

        return credentialEntity.id
    }

    override fun loadUserByUsername(username: String): UserDetails {
        try {
            val accountDetails = credentialRepository.findByEmail(username)

            return User(accountDetails.email, accountDetails.password, emptyList())
        } catch (e: NoResultException) {
            log.warn("Account with username $username does not exist in database")
            throw UsernameNotFoundException("Account with username $username does not exist in database", e)
        }
    }

    override fun getAccountDetails(username: String) =
            toDto(credentialRepository.findByEmail(username))

    override fun updatePassword(newPassword: NewPassword) {
        if (!checkPassword(newPassword.id, newPassword.oldPassword)) {
            log.warn("Unsuccessful current password verification.")
            throw CredentialsVerificationException("Unsuccessful current password verification.")
        }

        credentialRepository.updatePassword(newPassword.id, newPassword.newPassword)
    }

    override fun deactivateAccount(account: Account) {
        if (!checkPassword(account.id, account.password)) {
            log.warn("Unsuccessful password verification.")
            throw CredentialsVerificationException("Unsuccessful password verification.")
        }

        credentialRepository.deactivateCredential(account.id, System.currentTimeMillis())
    }

    private fun toDto(entity: CredentialEntity): Account =
            Account(entity.id!!, entity.email, entity.password, entity.isActive)

    private fun checkPassword(id: Long, password: String): Boolean {
        val expectedPassword = credentialRepository.findPasswordById(id)
        val actualPassword = passwordEncoder.encode(password)

        return expectedPassword == actualPassword
    }
}
