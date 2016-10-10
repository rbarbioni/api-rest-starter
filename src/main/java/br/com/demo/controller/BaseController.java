package br.com.demo.controller;

import br.com.demo.model.DomainEntity;
import br.com.demo.service.BaseService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by renan on 30/09/16.
 */
public abstract class BaseController<T extends DomainEntity> {

    private final Controller controller;

    public BaseController(BaseService<T> baseService) {
        this.controller = new Controller(baseService);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public T get(@PathVariable Long id){
        return (T) this.controller.getObject(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<T> get(){
        return this.controller.getObjects();
    }

    @RequestMapping(method = RequestMethod.POST)
    public T post (@Valid @RequestBody T object){
        return (T) this.controller.postObject(object);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public T put (@PathVariable Long id, @Valid @RequestBody T object){
        return (T) this.controller.putObject(id, object);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete (@PathVariable Long id){
        this.controller.deleteObject(id);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<T> search(HttpServletRequest request){
        return this.controller.searchObjects(request);
    }
}