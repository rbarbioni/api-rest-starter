package br.com.demo.service;

import br.com.demo.model.User;
import br.com.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class UserService extends BaseService<User>{

    @Autowired
    public UserService(UserRepository userRepository, EntityManager entityManager) {
        super(userRepository, entityManager, User.class);
    }
}
