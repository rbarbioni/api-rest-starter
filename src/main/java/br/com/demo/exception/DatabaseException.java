package br.com.demo.exception;

/**
 * Created by renan on 30/09/16.
 */
public class DatabaseException extends ApplicationException{

    public DatabaseException(int status, String message) {
        super(status, message);
    }
}
