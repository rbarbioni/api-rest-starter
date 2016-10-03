package br.com.demo.exception;

/**
 * Created by renan on 02/10/16.
 */
public class ApplicationException extends RuntimeException {

    private int status;

    public ApplicationException(int status, String message) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}