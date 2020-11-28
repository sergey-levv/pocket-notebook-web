package by.liauko.siarhei.pn.repository.postgres

import by.liauko.siarhei.pn.repository.BaseRepository
import by.liauko.siarhei.pn.repository.NoteRepository
import by.liauko.siarhei.pn.repository.entity.NoteEntity
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional
class PostgresNoteRepository : BaseRepository(), NoteRepository {

    private val selectUserNotesByType = "from Note where credential_id = :cred_id and type_id = :type_id"

    private val credentialIdParameterName = "cred_id"
    private val typeIdParameterName = "type_id"

    override fun save(note: NoteEntity) =
            entityManager.persist(note)

    override fun update(note: NoteEntity): NoteEntity =
            entityManager.merge(note)

    override fun delete(note: NoteEntity) =
            entityManager.remove(note)

    override fun findUserNotesByType(credentialId: Long, typeId: Long): List<NoteEntity> =
            entityManager.createQuery(selectUserNotesByType, NoteEntity::class.java)
                    .setParameter(credentialIdParameterName, credentialId)
                    .setParameter(typeIdParameterName, typeId)
                    .resultList
}
