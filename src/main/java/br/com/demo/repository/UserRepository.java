package br.com.demo.repository;

import br.com.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by renan on 9/29/16.
 */
public interface UserRepository extends JpaRepository<User, Long>{

}
