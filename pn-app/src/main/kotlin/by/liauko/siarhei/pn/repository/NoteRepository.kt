package by.liauko.siarhei.pn.repository

import by.liauko.siarhei.pn.repository.entity.NoteEntity
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional
class NoteRepository : BaseRepository() {

    fun save(note: NoteEntity) =
            entityManager.persist(note)

    fun update(note: NoteEntity) =
            entityManager.merge(note)

    fun delete(note: NoteEntity) =
            entityManager.remove(note)

    fun findUserNotesByType(credentialId: Long, typeId: Long) =
            entityManager.createQuery("from Note where credential_id = :cred_id and type_id = :type_id")
                    .setParameter("cred_id", credentialId)
                    .setParameter("type_id", typeId)
                    .resultList.toList()
}
