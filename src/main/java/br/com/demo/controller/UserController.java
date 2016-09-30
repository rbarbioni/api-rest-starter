package br.com.demo.controller;

import br.com.demo.Constants;
import br.com.demo.exception.ApiException;
import br.com.demo.model.User;
import br.com.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by renan on 9/29/16.
 */

@RestController
@RequestMapping(Constants.API + "/user")
public class UserController {


    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User get(@PathVariable Long id){

        User userExist = this.userRepository.findOne(id);
        if(userExist == null){
            throw new ApiException(HttpServletResponse.SC_NOT_FOUND, "Resource Not Found");
        }

        return this.userRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> get(){
        return this.userRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public User post (@Valid @RequestBody User user){
        return this.userRepository.save(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public User put (@PathVariable Long id, @Valid @RequestBody User user){

        User userExist = this.userRepository.findOne(id);
        if(userExist == null){
            throw new ApiException(HttpServletResponse.SC_NOT_FOUND, "Resource Not Found");
        }

        return this.userRepository.save(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void put (@PathVariable Long id){

        User userExist = this.userRepository.findOne(id);
        if(userExist == null){
            throw new ApiException(HttpServletResponse.SC_NOT_FOUND, "Resource Not Found");
        }

        this.userRepository.delete(userExist);
    }
}