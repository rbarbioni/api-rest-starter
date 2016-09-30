package br.com.demo.exception;

/**
 * Created by renan on 9/29/16.
 */
public class ApiException extends RuntimeException {

    private int status;

    public ApiException(int status, String message) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
