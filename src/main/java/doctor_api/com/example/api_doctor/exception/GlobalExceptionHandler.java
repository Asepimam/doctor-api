package doctor_api.com.example.api_doctor.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import doctor_api.com.example.api_doctor.helper.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Tangani IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null));
    }

    // Tangani RuntimeException
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<String>> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Terjadi kesalahan di server", null));
    }
    // Tangani NoHandlerFoundException atau 404
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleNotFoundException(NoHandlerFoundException ex) {
        ApiResponse<String> response = new ApiResponse<>(
            HttpStatus.NOT_FOUND.value(),
            "Path tidak ditemukan: " + ex.getRequestURL(),
            null
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Menangani kesalahan server (500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGlobalException(Exception ex) {
        ApiResponse<String> response = new ApiResponse<>(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Terjadi kesalahan pada server: " + ex.getMessage(),
            null
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
