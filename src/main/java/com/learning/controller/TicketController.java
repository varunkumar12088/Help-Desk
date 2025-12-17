package com.learning.controller;

import com.learning.constant.Status;
import com.learning.model.Ticket;
import com.learning.service.TicketService;
import com.learning.utils.Convertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping(value = "/{userId}")
    public ResponseEntity<?> getTickets(@PathVariable(name = "userId") String userId) {
        List<Ticket> tickets = ticketService.getByUserId(userId).stream()
                .filter(ticket -> !ticket.getStatus().equals(Status.CLOSED))
                .toList();
        System.out.println(Convertor.convertObjectToString(tickets));
        return ResponseEntity.ok(tickets);
    }
}
