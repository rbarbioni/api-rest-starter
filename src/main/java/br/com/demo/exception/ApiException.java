package br.com.demo.exception;

/**
 * Created by renan on 9/29/16.
 */
public class ApiException extends RuntimeException {

    private long status;

    public ApiException(long status, String message) {
        super(message);
        this.status = status;
    }

    public long getStatus() {
        return status;
    }
}
