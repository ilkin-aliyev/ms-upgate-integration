package work.mayflower.ms.upgate.integration.exception

import org.springframework.http.HttpStatus.BAD_GATEWAY
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import work.mayflower.ms.upgate.integration.config.log
import work.mayflower.ms.upgate.integration.exception.ErrorConstants.HTTP_METHOD_NOT_SUPPORTED
import work.mayflower.ms.upgate.integration.exception.ErrorConstants.INVALID_FIELDS
import work.mayflower.ms.upgate.integration.exception.ErrorConstants.UNEXPECTED_ERROR

@RestControllerAdvice
class ErrorHandler {

    @ExceptionHandler(Exception::class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    fun handle(ex: Exception): ErrorResponse {
        log.error("Exception: ", ex)
        return ErrorResponse(UNEXPECTED_ERROR.message, UNEXPECTED_ERROR.code)
    }

    @ExceptionHandler(BadGatewayException::class)
    @ResponseStatus(BAD_GATEWAY)
    fun handle(ex: BadGatewayException): ErrorResponse {
        log.error("BadGatewayException: ", ex)
        return ErrorResponse(ex.message, ex.code)
    }

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(NOT_FOUND)
    fun handle(ex: NotFoundException): ErrorResponse {
        log.error("NotFoundException: ", ex)
        return ErrorResponse(ex.message, ex.code)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(BAD_REQUEST)
    fun handle(ex: MethodArgumentNotValidException): ErrorResponse {
        log.error("MethodArgumentNotValidException: ", ex)
        val validationErrorMessages = ex.allErrors.map { it.defaultMessage }.toList()
        return ErrorResponse(INVALID_FIELDS.message, INVALID_FIELDS.code, validationErrorMessages)
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    @ResponseStatus(METHOD_NOT_ALLOWED)
    fun handle(ex: HttpRequestMethodNotSupportedException): ErrorResponse {
        log.error("HttpRequestMethodNotSupportedException: ", ex)
        return ErrorResponse(HTTP_METHOD_NOT_SUPPORTED.message, HTTP_METHOD_NOT_SUPPORTED.code)
    }
}