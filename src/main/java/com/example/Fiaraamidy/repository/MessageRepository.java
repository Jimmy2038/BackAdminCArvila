package com.example.Fiaraamidy.repository;

import com.example.Fiaraamidy.entites.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends MongoRepository<Message, String> {
    Optional<Message> findById(String id);
    List<Message> findByUserFromIdUserAndUserToIdUserOrUserFromIdUserAndAndUserToIdUserOrderByDate(int from,int to,int to1,int from1);

}



//findByUserFromIdUtilisateurAndUserToIdUtilisateurOrUserFromIdUtilisateurAndAndUserToIdUtilisateurOrderByDate
