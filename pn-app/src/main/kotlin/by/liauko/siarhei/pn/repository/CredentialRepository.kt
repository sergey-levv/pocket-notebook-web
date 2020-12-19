package by.liauko.siarhei.pn.repository

import by.liauko.siarhei.pn.repository.entity.CredentialEntity


/**
 * Interface used to manage user credential in database.
 *
 * @see CredentialEntity
 * @author Siarhei Liauko
 * @since 1.0.0
 */
interface CredentialRepository {

    /**
     * Save user credentials to database. [CredentialEntity.password] value is PBKDF2 function result
     * which length is 44 characters.
     *
     * @param credential - [CredentialEntity] instance which stored user email and password.
     *
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    fun save(credential: CredentialEntity)

    /**
     * Update user credentials.
     *
     * @param credential - [CredentialEntity] which contains updated user credentials information.
     *
     * @return [CredentialEntity] instance.
     *
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    fun update(credential: CredentialEntity): CredentialEntity

    /**
     * Remove user credentials from database.
     *
     * @param credential - user credentials which will be deleted.
     *
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    fun delete(credential: CredentialEntity)

    /**
     * Find user credentials by it ID in database.
     *
     * @param id - credentials unique identifier.
     *
     * @return [CredentialEntity] related to ID.
     *
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    fun findById(id: Long): CredentialEntity

    /**
     * Find user credentials by it email in database.
     *
     * @param email - user's email identifying the credentials which data is returned.
     *
     * @return [CredentialEntity] related to email.
     *
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    fun findByEmail(email: String): CredentialEntity

    /**
     * Find password by email related to it.
     *
     * @param email - user's email related to password.
     *
     * @return password value
     *
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    fun findPasswordByEmail(email: String): String
}
