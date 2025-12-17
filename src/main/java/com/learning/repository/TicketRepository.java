package com.learning.repository;

import com.learning.model.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends MongoRepository<Ticket, Long> {

    List<Ticket> findByUserId(String userId);
}
