package by.liauko.siarhei.pn.service.impl

import by.liauko.siarhei.pn.dto.Account
import by.liauko.siarhei.pn.repository.CredentialRepository
import by.liauko.siarhei.pn.repository.entity.CredentialEntity
import by.liauko.siarhei.pn.service.AccountService
import by.liauko.siarhei.pn.service.exception.AccountAlreadyExistsException
import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder
import org.springframework.stereotype.Service


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

    override fun createAccount(account: Account): Long? {
        if (credentialRepository.isEmailExist(account.username)) {
            log.warn("Account with email ${account.username} already exist in database")
            throw AccountAlreadyExistsException("Account with email ${account.username} already exist in database")
        }
        account.password = passwordEncoder.encode(account.password)
        val credentialEntity = toEntity(account)
        credentialRepository.save(credentialEntity)

        return credentialEntity.id
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val accountDetails = credentialRepository.findByEmail(username)

        return User(accountDetails.email, accountDetails.password, emptyList())
    }

    override fun getAccountDetails(username: String) =
            toDto(credentialRepository.findByEmail(username))

    private fun toDto(entity: CredentialEntity): Account =
            Account(entity.id, entity.email, entity.password)

    private fun toEntity(dto: Account): CredentialEntity {
        val entity = CredentialEntity(dto.username, dto.password)
        entity.id = dto.id

        return entity
    }
}
