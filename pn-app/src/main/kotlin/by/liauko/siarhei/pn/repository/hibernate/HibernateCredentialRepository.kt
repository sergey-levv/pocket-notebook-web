package by.liauko.siarhei.pn.repository.hibernate

import by.liauko.siarhei.pn.repository.BaseRepository
import by.liauko.siarhei.pn.repository.CredentialRepository
import by.liauko.siarhei.pn.repository.entity.CredentialEntity
import org.springframework.stereotype.Repository
import java.sql.Timestamp
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

    private val selectByEmail = "from Credential where email = :email"
    private val countEmail = "select count(1) from Credential where email = :email"
    private val updateIsActiveAndDeactivationDateValue = "update Credential " +
            "set isActive = :value, deactivationDate = :date where id = :id"
    private val selectPasswordById = "select password from Credential where id = :id"
    private val updatePassword = "update Credential set password = :password where id = :id"
    private val checkCredentialsStatus = "select count(1) from Credential where id = :id and isActive = true"

    private val emailParameterName = "email"
    private val idParameterName = "id"
    private val valueParameterName = "value"
    private val dateParameterName = "date"
    private val passwordParameterName = "password"

    override fun save(credential: CredentialEntity) =
            entityManager.persist(credential)

    override fun update(credential: CredentialEntity): CredentialEntity =
            entityManager.merge(credential)

    override fun delete(credential: CredentialEntity) =
            entityManager.remove(credential)

    override fun findById(id: Long): CredentialEntity? =
            entityManager.find(CredentialEntity::class.java, id)

    override fun findByEmail(email: String): CredentialEntity =
            entityManager.createQuery(selectByEmail, CredentialEntity::class.java)
                    .setParameter(emailParameterName, email)
                    .singleResult

    override fun isEmailExist(email: String) =
            entityManager.createQuery(countEmail, Long::class.javaObjectType)
                    .setParameter(emailParameterName, email)
                    .singleResult > 0

    override fun deactivateCredential(id: Long, time: Long) =
            entityManager.createQuery(updateIsActiveAndDeactivationDateValue)
                    .setParameter(valueParameterName, false)
                    .setParameter(dateParameterName, Timestamp(time))
                    .setParameter(idParameterName, id)
                    .executeUpdate()

    override fun updatePassword(id: Long, password: String) =
            entityManager.createQuery(updatePassword)
                    .setParameter(passwordParameterName, password)
                    .setParameter(idParameterName, id)
                    .executeUpdate()
}
