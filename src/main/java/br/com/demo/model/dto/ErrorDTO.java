package br.com.demo.model.dto;

/**
 * Created by renan on 9/29/16.
 */
public class ErrorDTO {

    private int status = 500;

    private String message;

    private String path;

    private String[] errors;

    public ErrorDTO(int status, String message, String path) {
        this.status = status;
        this.message = message;
        this.path = path;
    }

    public ErrorDTO(int status, String message, String path, String[] errors) {
        this(status, message, path);
        this.errors = errors;
    }

    public int getStatus() {
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

    public String getPath() {
        return path;
    }
}
