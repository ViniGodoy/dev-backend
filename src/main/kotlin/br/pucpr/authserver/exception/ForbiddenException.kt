package br.pucpr.authserver.exception

import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(NOT_FOUND)
class ForbiddenException(
    message: String = "Forbidden",
    cause: Throwable? = null
) : IllegalArgumentException(message, cause)