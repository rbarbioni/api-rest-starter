package br.com.demo.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.Date;


public class DomainEntityListener<T extends DomainEntity> {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @PrePersist
    public void onPrePersist(T obj) throws JsonProcessingException {
        doLog("PRE_PERSIST", obj);
    }

    @PostPersist
    public void onPostPersist(T obj) throws JsonProcessingException {
        doLog("POST_PERSIST", obj);
    }

    @PostLoad
    public void onPostLoad(T obj) throws JsonProcessingException {
        doLog("POST_LOAD", obj);
    }

    @PreUpdate
    public void onPreUpdate(T obj) throws JsonProcessingException {
        obj.setLastModifieldDate(new Date());
        doLog("PRE_UPDATE", obj);
    }

    @PostUpdate
    public void onPostUpdate(T obj) throws JsonProcessingException {
        doLog("POST_UPDATE", obj);
    }

    @PreRemove
    public void onPreRemove(T obj) throws JsonProcessingException {
        doLog("PRE_REMOVE", obj);
    }

    @PostRemove
    public void onPostRemove(T obj) throws JsonProcessingException {
        doLog("POST_REMOVE", obj);
    }

    private void doLog(String operation, T obj) throws JsonProcessingException {
        log.debug(StringUtils.rightPad(operation, 12) + " -> " + obj.toJson(new ObjectMapper()));
    }
}