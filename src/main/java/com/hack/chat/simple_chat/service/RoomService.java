package com.hack.chat.simple_chat.service;

import com.hack.chat.simple_chat.model.Room;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;

@Service
@Transactional
public class RoomService {

    private final EntityManagerFactory factory;

    public RoomService(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public void saveRoom(Room newRoom){
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(newRoom);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            throw new RuntimeException();
        }
    }


}
