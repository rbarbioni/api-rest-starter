package br.com.demo.controller;

import br.com.demo.model.DomainEntity;
import br.com.demo.model.dto.ResultDTO;
import br.com.demo.service.BaseService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by renan on 09/10/16.
 */
public class BaseResultController<T extends DomainEntity>{

    private final Controller<T> controller;

    public BaseResultController(BaseService<T> baseService) {
        this.controller = new Controller(baseService);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResultDTO get(@PathVariable Long id){
        return this.controller.get(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResultDTO get(Pageable pageable){
        if(pageable != null){
            return controller.get(pageable);
        }else{
            return controller.get();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResultDTO post (@Valid @RequestBody T object){
        return this.controller.post(object);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResultDTO put (@PathVariable Long id, @Valid @RequestBody T object){
        return this.controller.put(id, object);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResultDTO delete (@PathVariable Long id){
        this.controller.delete(id);
        return new ResultDTO(200, null);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResultDTO search(HttpServletRequest request, Pageable pageable){
        if(pageable != null){
            return this.controller.search(request, pageable);
        }else{
            return this.controller.search(request);
        }
    }
}