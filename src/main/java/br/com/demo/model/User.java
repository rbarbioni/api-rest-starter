package br.com.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class User extends DomainEntity {

    private static final long serialVersionUID = 280849381809891638L;

    @NotNull(message = "{NotNull.user.name}")
    @NotBlank(message = "{NotBlank.user.name}")
    @Column(name = "name", nullable = false)
    @JsonProperty("name")
    private String name;

    @NotNull(message = "{NotNull.user.email}")
    @NotBlank(message = "{NotBlank.user.email}")
    @Email
    @Column(name = "email", nullable = false)
    @JsonProperty("email")
    private String email;

    @NotNull(message = "{NotNull.user.password}")
    @NotBlank(message = "{NotBlank.user.password}")
    @Column(name = "password", nullable = false)
    @JsonProperty("password")
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
