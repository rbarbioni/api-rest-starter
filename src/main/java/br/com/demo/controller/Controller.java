package br.com.demo.controller;

import br.com.demo.model.DomainEntity;
import br.com.demo.model.dto.ResultDTO;
import br.com.demo.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by renan on 09/10/16.
 */
public class Controller<T extends DomainEntity> {

    private final BaseService<T> baseService;

    public Controller(BaseService<T> baseService) {
        this.baseService = baseService;
    }

    public ResultDTO get(Long id){
        return new ResultDTO(this.getObject(id));
    }

    public T getObject(Long id){
        return this.baseService.findOneThrowException(id);
    }

    public ResultDTO get(){
        return new ResultDTO(this.getObjects());
    }

    public List<T> getObjects(){
        return this.baseService.findAll();
    }

    public Page<T> getPageableObjects(Pageable pageable){
        return this.baseService.findAll(pageable);
    }

    public ResultDTO get(Pageable pageable){
        return new ResultDTO(this.baseService.findAll(pageable));
    }

    public ResultDTO post (T object){
        return new ResultDTO(this.postObject(object));
    }

    public T postObject(T object){
        return this.baseService.save(object);
    }

    public ResultDTO put (Long id, T object){
        return new ResultDTO(this.putObject(id, object));
    }

    public T putObject(Long id, T object){
        return this.baseService.update(object);
    }

    public ResultDTO delete (T object){
        this.deleteObject(object);
        return new ResultDTO(200, null);
    }

    public ResultDTO delete (Long id){
        this.deleteObject(id);
        return new ResultDTO(200, null);
    }

    public void deleteObject (Long id){
        this.baseService.delete(id);
    }

    public void deleteObject (T object){
        this.baseService.delete(object);
    }

    public List<T> searchObjects(HttpServletRequest request){
        return this.baseService.search(request);
    }

    public Page<T> searchObjects(HttpServletRequest request, Pageable pageable){
        return this.baseService.search(request, pageable);
    }

    public ResultDTO search(HttpServletRequest request, Pageable pageable){
        return new ResultDTO(this.searchObjects(request, pageable));
    }

    public ResultDTO search(HttpServletRequest request){
        return new ResultDTO(this.searchObjects(request));
    }
}