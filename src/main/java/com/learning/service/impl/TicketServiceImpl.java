package com.learning.service.impl;

import com.learning.constant.Status;
import com.learning.model.Ticket;
import com.learning.repository.TicketRepository;
import com.learning.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public void createTicket(Ticket ticket) {
        ticket.setId(System.currentTimeMillis());
        ticket.setStatus(Status.OPEN);
        ticketRepository.save(ticket);
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {

        return null;
    }

    @Override
    public Ticket getById(Long id) {
        return null;
    }

    @Override
    public List<Ticket> getByUserId(Long userId) {
        return List.of();
    }
}
