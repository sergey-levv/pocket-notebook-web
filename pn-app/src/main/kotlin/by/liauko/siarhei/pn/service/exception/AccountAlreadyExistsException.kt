package by.liauko.siarhei.pn.service.exception

import java.lang.RuntimeException


/**
 * Exception class intended for notifying controller layer, that user with such username already exists in application.
 *
 * @author Siarhei Liauko
 * @since 1.0.0
 */
class AccountAlreadyExistsException : RuntimeException {

    constructor(): super()
    constructor(message: String): super(message)
    constructor(message: String, cause: Throwable): super(message, cause)
    constructor(cause: Throwable): super(cause)
    constructor(message: String, cause: Throwable, enableSuppression: Boolean, writableStackTrace: Boolean):
            super(message, cause, enableSuppression, writableStackTrace)
}
