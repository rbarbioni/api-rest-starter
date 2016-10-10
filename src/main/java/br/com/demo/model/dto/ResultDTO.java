package br.com.demo.model.dto;

/**
 * Created by renan on 09/10/16.
 */
public class ResultDTO{

    private int status = 200;

    private Object data;

    public ResultDTO(Object data) {
        this.data = data;
    }

    public ResultDTO(int status, Object data) {
        this(data);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }
}