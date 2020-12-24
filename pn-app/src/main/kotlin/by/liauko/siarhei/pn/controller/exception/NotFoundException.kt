package by.liauko.siarhei.pn.controller.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus


/**
 * Exception class intended for notifying, that resource was not found.
 *
 * @author Siarhei Liauko
 * @since 1.0.0
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException: RuntimeException {

    constructor(): super()
    constructor(message: String): super(message)
    constructor(message: String, cause: Throwable): super(message, cause)
    constructor(cause: Throwable): super(cause)
    constructor(message: String, cause: Throwable, enableSuppression: Boolean, writableStackTrace: Boolean):
            super(message, cause, enableSuppression, writableStackTrace)
}
