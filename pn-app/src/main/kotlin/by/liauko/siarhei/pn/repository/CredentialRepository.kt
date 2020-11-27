package by.liauko.siarhei.pn.repository

import by.liauko.siarhei.pn.repository.entity.CredentialEntity
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional
class CredentialRepository : BaseRepository() {

    fun findById(id: Long) =
            entityManager.find(CredentialEntity::class.java, id)

    fun findPasswordByEmail(email: String) =
            entityManager.createQuery("select password from Credential where email = '$email'")
                .singleResult

    fun save(credential: CredentialEntity) =
            entityManager.persist(credential)

    fun update(credential: CredentialEntity) =
            entityManager.merge(credential)

    fun delete(credential: CredentialEntity) =
            entityManager.remove(credential)
}
