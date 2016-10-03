package br.com.demo.controller;

import br.com.demo.exception.ApplicationException;
import br.com.demo.model.dto.ErrorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by renan on 9/29/16.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final String ERROR = "ERROR";

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseBody
    public ErrorDTO onValidation(MethodArgumentNotValidException ex, HttpServletRequest request, HttpServletResponse response) {

        log.error(ERROR, ex);

        ErrorDTO errorDTO = new ErrorDTO(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage(), request.getRequestURI());
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        List<String> errorList = new ArrayList<>();
        BindingResult result = ex.getBindingResult();
        for (FieldError fieldError : result.getFieldErrors()) {

            for (int i = 0; i < fieldError.getCodes().length; i++) {
                String code = fieldError.getCodes()[i];
                try{
                    String message = messageSource.getMessage(code, new Object[]{}, Locale.US);
                    errorList.add(fieldError.getField() + ": " + message);
                }catch (Exception e){
                    System.out.printf(code);
                    errorList.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
                }

                break;
            }
        }

        errorDTO.setErrors(errorList.stream().toArray(String[]::new));
        return errorDTO;
    }

    @ExceptionHandler(value = {ApplicationException.class})
    @ResponseBody
    public ErrorDTO onApiException(ApplicationException ex, HttpServletRequest request, HttpServletResponse response) {
        log.error(ERROR, ex);

        response.setStatus(ex.getStatus());
        return new ErrorDTO(ex.getStatus(), ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(value = {SQLException.class, DataAccessException.class})
    @ResponseBody
    public ErrorDTO onDatabaseException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        log.error(ERROR, ex);

        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        if(ex instanceof DataIntegrityViolationException){
            DataIntegrityViolationException exception = (DataIntegrityViolationException) ex;
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return new ErrorDTO(HttpServletResponse.SC_BAD_REQUEST, exception.getMostSpecificCause().getLocalizedMessage(), request.getRequestURI());
        }else{
            return onGenericException(ex, request, response);
        }
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public ErrorDTO onGenericException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        log.error(ERROR, ex);
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return new ErrorDTO(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage(), request.getRequestURI());
    }
}