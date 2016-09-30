package br.com.demo.controller;

import br.com.demo.exception.ApiException;
import br.com.demo.model.dto.ErrorDTO;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by renan on 9/29/16.
 */
@RestControllerAdvice
public class ControllerHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(value = {MethodArgumentNotValidException.class, ApiException.class})
    @ResponseBody
    public ErrorDTO processValidationError(Exception ex, HttpServletRequest request, HttpServletResponse response) {

        ErrorDTO errorDTO = new ErrorDTO(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());

        if(ex instanceof MethodArgumentNotValidException){

            List<String> errorList = new ArrayList<>();
            errorDTO = new ErrorDTO(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            BindingResult result = ((MethodArgumentNotValidException)ex).getBindingResult();
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
        }else if(ex instanceof ApiException){

            ApiException apiException = (ApiException) ex;
            errorDTO = new ErrorDTO(apiException.getStatus(), apiException.getMessage());
        }



        return errorDTO;
    }

}
