package by.liauko.siarhei.pn.repository

import by.liauko.siarhei.pn.repository.entity.SetItemEntity


/**
 * Interface used to manage set item entities in database.
 *
 * @see SetItemEntity
 * @author Siarhei Liauko
 * @since 1.0.0
 */
interface SetRepository {

    /**
     * Save new set item to database.
     *
     * @param item - [SetItemEntity] instance containing data about set item.
     *
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    fun save(item: SetItemEntity)

    /**
     * Update set item data with a certain ID in database.
     *
     * @param item - [SetItemEntity] instance containing updated set item data.
     *
     * @return [SetItemEntity] instance.
     *
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    fun update(item: SetItemEntity): SetItemEntity

    /**
     * Remove set item with a certain ID from database.
     *
     * @param item - [SetItemEntity] instance containing a set item ID to be removed.
     *
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    fun delete(item: SetItemEntity)

    /**
     * Find set items related to certain note.
     *
     * @param noteId - note unique identifier for filtering set items.
     *
     * @return list of [SetItemEntity] containing data about all set items related to a certain note entity.
     *
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    fun findByNoteId(noteId: Long): List<SetItemEntity>
}
