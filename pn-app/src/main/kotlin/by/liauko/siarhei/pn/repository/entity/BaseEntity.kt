package by.liauko.siarhei.pn.repository.entity

import org.springframework.data.util.ProxyUtils
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass


/**
 * Abstract entity class containing common fields for all application entities.
 *
 * @author Siarhei Liauko
 * @since 1.0.0
 */
@MappedSuperclass
abstract class BaseEntity<T> {

    /**
     * Entity unique identifier.
     *
     * @author Siarhei Liauko
     * @since 1.0.0
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: T? = null

    override fun equals(other: Any?): Boolean {
        other ?: return false

        if (this === other) return true

        if (javaClass != ProxyUtils.getUserClass(other)) return false

        other as BaseEntity<*>

        return this.id != null && this.id == other.id
    }

    override fun hashCode() = 31 * id.hashCode()

    override fun toString(): String {
        return "${this.javaClass.simpleName}(id=$id)"
    }
}
