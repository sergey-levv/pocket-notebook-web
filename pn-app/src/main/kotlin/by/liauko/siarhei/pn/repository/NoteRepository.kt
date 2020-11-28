package by.liauko.siarhei.pn.repository

import by.liauko.siarhei.pn.repository.entity.NoteEntity


interface NoteRepository {

    fun save(note: NoteEntity)

    fun update(note: NoteEntity): NoteEntity

    fun delete(note: NoteEntity)

    fun findUserNotesByType(credentialId: Long, typeId: Long): List<NoteEntity>
}