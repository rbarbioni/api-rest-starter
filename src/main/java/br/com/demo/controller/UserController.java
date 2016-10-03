package br.com.demo.controller;

import br.com.demo.Constants;
import br.com.demo.model.User;
import br.com.demo.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.API + "/user")
public class UserController extends BaseController<User> {

    @Autowired
    public UserController(BaseService<User> baseService) {
        super(baseService);
    }
}