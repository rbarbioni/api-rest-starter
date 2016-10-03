package br.com.demo.exception;

/**
 * Created by renan on 9/29/16.
 */
public class ApiException extends ApplicationException {

    public ApiException(int status, String message) {
        super(status, message);
    }
}