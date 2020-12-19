package by.liauko.siarhei.pn.service.impl

import by.liauko.siarhei.pn.dto.User
import by.liauko.siarhei.pn.repository.CredentialRepository
import by.liauko.siarhei.pn.repository.entity.CredentialEntity
import by.liauko.siarhei.pn.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service


@Service
class UserServiceImpl : UserService {

    @Autowired
    private lateinit var credentialRepository: CredentialRepository

    override fun loadUserByUsername(username: String?): UserDetails {
        TODO("Not yet implemented")
    }

    override fun getCredentials(username: String) =
            toDto(credentialRepository.findByEmail(username))

    private fun toDto(entity: CredentialEntity): User =
            User(entity.id, entity.email, entity.password)

    private fun toEntity(dto: User): CredentialEntity {
        val entity = CredentialEntity(dto.username, dto.password)
        entity.id = dto.id

        return entity
    }
}