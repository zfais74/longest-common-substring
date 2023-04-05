package zeke.faison.zekelcs.advice

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import zeke.faison.zekelcs.model.ErrorMessage

@ControllerAdvice
class LCSControllerAdvice {
    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalStateException(exception: IllegalArgumentException): ResponseEntity<ErrorMessage> {
        val errorMessage = ErrorMessage(
            status = HttpStatus.BAD_REQUEST,
            message = exception.message
        )
        return ResponseEntity.badRequest().body(errorMessage)
    }
}