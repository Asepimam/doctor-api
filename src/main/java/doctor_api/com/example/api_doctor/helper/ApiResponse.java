package doctor_api.com.example.api_doctor.helper;


public class ApiResponse<T> {
    private int status;
    private String message;
    private T data;

    // Constructor untuk respons sukses
    public ApiResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    // Constructor untuk respons tanpa data
    public ApiResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.data = null;
    }

    // Getter & Setter
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
