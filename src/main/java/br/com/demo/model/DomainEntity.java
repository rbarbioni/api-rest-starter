package br.com.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by renan on 30/09/16.
 */

@EntityListeners(DomainEntityListener.class)
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
@MappedSuperclass
public abstract class DomainEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private Long id;

    @JsonProperty("created_date")
    @Column(name = "created_date")
    private Date createdDate = new Date();

    @JsonProperty("last_modifield_date")
    @Column(name = "last_modifield_date")
    private Date lastModifieldDate;

    public Long getId() {
        return id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getLastModifieldDate() {
        return lastModifieldDate;
    }

    public void setLastModifieldDate(Date lastModifieldDate) {
        this.lastModifieldDate = lastModifieldDate;
    }

    public String toJson(ObjectMapper objectMapper) throws JsonProcessingException {
        return objectMapper.writeValueAsString(this);
    }
}