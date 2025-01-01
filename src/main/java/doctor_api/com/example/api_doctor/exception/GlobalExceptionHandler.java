package doctor_api.com.example.api_doctor.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import doctor_api.com.example.api_doctor.helper.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Tangani IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        logger.error("IllegalArgumentException occurred: ", ex);
        ApiResponse<String> response = new ApiResponse<>(
            HttpStatus.BAD_REQUEST.value(),
            ex.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Tangani RuntimeException
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<String>> handleRuntimeException(RuntimeException ex) {
        logger.error("RuntimeException occurred: ", ex);
        ApiResponse<String> response = new ApiResponse<>(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Terjadi kesalahan di server"
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Tangani HttpMessageNotReadableException (contoh: request body tidak valid)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<String>> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        logger.error("HttpMessageNotReadableException occurred: ", ex);
        ApiResponse<String> response = new ApiResponse<>(
            HttpStatus.BAD_REQUEST.value(),
            "Invalid request body: " + ex.getMostSpecificCause().getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Tangani MethodArgumentNotValidException (contoh: validasi input tidak berhasil)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        logger.error("MethodArgumentNotValidException occurred: ", ex);
        String errorMessage = ex.getBindingResult().getFieldErrors()
            .stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .reduce((msg1, msg2) -> msg1 + ", " + msg2)
            .orElse("Validation error");

        ApiResponse<String> response = new ApiResponse<>(
            HttpStatus.BAD_REQUEST.value(),
            "Validation failed: " + errorMessage
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Tangani NoHandlerFoundException (404 - Endpoint tidak ditemukan)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        logger.error("NoHandlerFoundException occurred: ", ex);
        ApiResponse<String> response = new ApiResponse<>(
            HttpStatus.NOT_FOUND.value(),
            "Path tidak ditemukan: " + ex.getRequestURL()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Tangani AccessDeniedException (403 - Akses ditolak)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<String>> handleAccessDeniedException(AccessDeniedException ex) {
        logger.error("AccessDeniedException occurred: ", ex);
        ApiResponse<String> response = new ApiResponse<>(
            HttpStatus.FORBIDDEN.value(),
            "Anda tidak memiliki akses ke resource ini"
        );
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    // Tangani pengecualian umum lainnya
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGlobalException(Exception ex) {
        logger.error("Unhandled exception occurred: ", ex);
        ApiResponse<String> response = new ApiResponse<>(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Terjadi kesalahan pada server: " + ex.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
