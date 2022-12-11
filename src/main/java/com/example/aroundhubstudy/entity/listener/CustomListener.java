package com.example.aroundhubstudy.entity.listener;

import com.example.aroundhubstudy.entity.Listener;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Slf4j
public class CustomListener {

    @PostLoad
    public void postLoad(Listener listener){
        log.info("[postLoad] called!!");
    }

    @PrePersist
    public void perPersist(Listener listener){
        log.info("[prePersist] called!!");
    }

    @PostPersist
    public void postPersis(Listener listener){
        log.info("[postPersist] called!!");
    }

    @PreUpdate
    public void preUpdate(Listener entity) {
        log.info("[preUpdate] called!!");
    }

    @PostUpdate
    public void postUpdate(Listener entity) {
        log.info("[postUpdate] called!!");
    }

    @PreRemove
    public void preRemove(Listener entity) {
        log.info("[preRemove] called!!");
    }

    @PostRemove
    public void postRemove(Listener entity) {
        log.info("[postRemove] called!!");
    }
}
