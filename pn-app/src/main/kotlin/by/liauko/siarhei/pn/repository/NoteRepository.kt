package by.liauko.siarhei.pn.repository

import by.liauko.siarhei.pn.repository.entity.NoteEntity


/**
 * Interface used to manage note entities in database.
 *
 * @see NoteEntity
 * @author Siarhei Liauko
 * @since 1.0.0
 */
interface NoteRepository {

    /**
     * Save new note instance to database.
     *
     * @param note - [NoteEntity] instance containing data about new user note.
     *
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    fun save(note: NoteEntity)

    /**
     * Update note information.
     *
     * @param note - [NoteEntity] instance containing updated data about user note.
     *
     * @return [NoteEntity] instance.
     *
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    fun update(note: NoteEntity): NoteEntity

    /**
     * Remove note with a certain ID from database.
     *
     * @param note - [NoteEntity] instance containing item ID to be removed.
     *
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    fun delete(note: NoteEntity)

    /**
     * Find notes owned by particular user with particular type in database.
     *
     * @param credentialId - user unique identifier whose notes will be returned.
     * @param typeId - note type unique identifier for filtering user notes.
     *
     * @return list of [NoteEntity] containing data about particular user notes.
     * The list contains notes which have type, specified by `typeId` parameter.
     *
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    fun findUserNotesByType(credentialId: Long, typeId: Long): List<NoteEntity>
}
