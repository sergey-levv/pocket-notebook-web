package by.liauko.siarhei.pn.service.exception


/**
 * Exception class intended for notifying controller layer, that username or password is wrong.
 *
 * @author Siarhei Liauko
 * @since 1.0.0
 */
class CredentialsVerificationException : RuntimeException {

    constructor(): super()
    constructor(message: String): super(message)
    constructor(message: String, cause: Throwable): super(message, cause)
    constructor(cause: Throwable): super(cause)
    constructor(message: String, cause: Throwable, enableSuppression: Boolean, writableStackTrace: Boolean):
            super(message, cause, enableSuppression, writableStackTrace)
}
