package by.liauko.siarhei.pn.repository

import by.liauko.siarhei.pn.repository.entity.CredentialEntity


interface CredentialRepository {

    fun save(credential: CredentialEntity)

    fun update(credential: CredentialEntity): CredentialEntity

    fun delete(credential: CredentialEntity)

    fun findById(id: Long): CredentialEntity

    fun findPasswordByEmail(email: String): String
}