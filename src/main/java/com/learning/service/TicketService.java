package com.learning.service;

import com.learning.model.Ticket;

import java.util.List;

public interface TicketService {

    void createTicket(Ticket ticket);

    Ticket updateTicket(Ticket ticket);

    Ticket getById(Long id);

    List<Ticket> getByUserId(Long userId);
}
