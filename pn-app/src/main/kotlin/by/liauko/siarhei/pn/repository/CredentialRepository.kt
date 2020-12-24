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
     * Saves user credentials to database. [CredentialEntity.password] value is PBKDF2 function result
     * which length is 80 characters.
     *
     * @param credential [CredentialEntity] instance which stored user email and password.
     *
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    fun save(credential: CredentialEntity)

    /**
     * Updates user credentials.
     *
     * @param credential [CredentialEntity] which contains updated user credentials information.
     *
     * @return [CredentialEntity] instance.
     *
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    fun update(credential: CredentialEntity): CredentialEntity

    /**
     * Removes user credentials from database.
     *
     * @param credential user credentials which will be deleted.
     *
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    fun delete(credential: CredentialEntity)

    /**
     * Finds user credentials by it ID in database.
     *
     * @param id credentials unique identifier.
     *
     * @return [CredentialEntity] related to ID.
     *
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    fun findById(id: Long): CredentialEntity?

    /**
     * Finds user credentials by it email in database.
     *
     * @param email user's email identifying the credentials which data is returned.
     *
     * @return [CredentialEntity] related to email.
     *
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    fun findByEmail(email: String): CredentialEntity

    /**
     * Checks if certain email already exists in database.
     *
     * @param email the email address which comparing with data in database.
     *
     * @return true if such email exists in database, otherwise false.
     *
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    fun isEmailExist(email: String): Boolean

    /**
     * Update user's credentials status to deactivated.
     *
     * @param id unique identifier determining user's credentials which will be deactivated.
     * @param time date and time when credential was deactivated in milliseconds.
     *
     * @return number updated rows.
     *
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    fun deactivateCredential(id: Long, time: Long): Int

    /**
     * Updates user's password in database.
     *
     * @param id unique identifier determining user's credentials which password will be updated.
     * @param password new user's password value.
     *
     * @return number updated rows.
     *
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    fun updatePassword(id: Long, password: String): Int
}
