package by.liauko.siarhei.pn.service.impl

import by.liauko.siarhei.pn.dto.Account
import by.liauko.siarhei.pn.dto.Credential
import by.liauko.siarhei.pn.dto.Password
import by.liauko.siarhei.pn.repository.CredentialRepository
import by.liauko.siarhei.pn.repository.entity.CredentialEntity
import by.liauko.siarhei.pn.service.AccountService
import by.liauko.siarhei.pn.service.exception.AccountAlreadyExistsException
import by.liauko.siarhei.pn.service.exception.AccountNotFoundException
import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*
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

    override fun createAccount(credential: Credential): UUID? {
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

    override fun getAccountDetails(username: String): Account {
        val entity = credentialRepository.findByEmail(username)
        return Account(entity.id!!, entity.email, entity.password, entity.isActive)
    }

    override fun updatePassword(password: Password) {
        if (credentialRepository.updatePassword(password.id, passwordEncoder.encode(password.password)) == 0) {
            log.warn("Account with id ${password.id} does not exists.")
            throw AccountNotFoundException("Account with id ${password.id} does not exists.")
        }
    }

    override fun deactivateAccount(id: UUID) {
        if (credentialRepository.deactivateCredential(id, System.currentTimeMillis()) == 0) {
            log.warn("Account with id $id does not exists.")
            throw AccountNotFoundException("Account with id $id does not exists.")
        }
    }
}
