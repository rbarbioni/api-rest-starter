package br.com.demo.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by renan on 9/29/16.
 */

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "{NotNull.user.name}")
    @NotBlank(message = "{NotBlank.user.name}")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull(message = "{required_field}")
    @NotBlank(message = "{not_empty_field}")
    @Email
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull(message = "{required_field}")
    @NotBlank(message = "{not_empty_field}")
    @Column(name = "password", nullable = false)
    private String password;

    public User(){
        super();
    }

    public User(String name, String email, String password) {
        this();
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
