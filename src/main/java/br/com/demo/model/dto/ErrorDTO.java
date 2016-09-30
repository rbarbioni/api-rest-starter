package br.com.demo.model.dto;

/**
 * Created by renan on 9/29/16.
 */
public class ErrorDTO {

    private long status = 500;

    private String[] errors;

    private String message;

    public ErrorDTO(long status, String message) {
        this.status = status;
        this.message = message;
    }

    public long getStatus() {
        return status;
    }

    public String[] getErrors() {
        return errors;
    }

    public String getMessage() {
        return message;
    }

    public void setErrors(String[] errors) {
        this.errors = errors;
    }
}
