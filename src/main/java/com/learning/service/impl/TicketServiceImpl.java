package com.learning.service.impl;

import com.learning.constant.Status;
import com.learning.model.Ticket;
import com.learning.repository.TicketRepository;
import com.learning.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public void createTicket(Ticket ticket) {
        ticket.setTicketId(generateTicketId());
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
    public List<Ticket> getByUserId(String userId) {
        return ticketRepository.findByUserId(userId);
    }

    public String generateTicketId() {
        String year = String.valueOf(Year.now().getValue());
        int randomNum = ThreadLocalRandom.current().nextInt(1000, 9999); // 4-digit random number
        return "T-" + year + "-" + randomNum;
    }
}
