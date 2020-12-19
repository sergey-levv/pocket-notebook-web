package by.liauko.siarhei.pn.repository.hibernate

import by.liauko.siarhei.pn.repository.BaseRepository
import by.liauko.siarhei.pn.repository.CredentialRepository
import by.liauko.siarhei.pn.repository.entity.CredentialEntity
import org.springframework.stereotype.Repository
import javax.transaction.Transactional


/**
 * [CredentialRepository] implementation using Hibernate API.
 *
 * @see BaseRepository
 * @see CredentialRepository
 * @author Siarhei Liauko
 * @since 1.0.0
 */
@Repository
@Transactional
class HibernateCredentialRepository : BaseRepository(), CredentialRepository {

    private val selectPasswordByEmail = "select password from Credential where email = :email"
    private val selectByEmail = "from Credential where email = :email"

    private val emailParameterName = "email"

    override fun save(credential: CredentialEntity) =
            entityManager.persist(credential)

    override fun update(credential: CredentialEntity): CredentialEntity =
            entityManager.merge(credential)

    override fun delete(credential: CredentialEntity) =
            entityManager.remove(credential)

    override fun findById(id: Long): CredentialEntity =
            entityManager.find(CredentialEntity::class.java, id)

    override fun findByEmail(email: String): CredentialEntity =
            entityManager.createQuery(selectByEmail, CredentialEntity::class.java)
                    .setParameter(emailParameterName, email)
                    .singleResult

    override fun findPasswordByEmail(email: String): String =
            entityManager.createQuery(selectPasswordByEmail, String::class.java)
                    .setParameter(emailParameterName, email)
                    .singleResult
}
