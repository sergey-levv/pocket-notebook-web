package by.liauko.siarhei.pn.repository

import by.liauko.siarhei.pn.repository.entity.SetItemEntity


interface SetRepository {

    fun save(item: SetItemEntity)

    fun update(item: SetItemEntity): SetItemEntity

    fun delete(item: SetItemEntity)

    fun findByNoteId(noteId: Long): List<SetItemEntity>
}