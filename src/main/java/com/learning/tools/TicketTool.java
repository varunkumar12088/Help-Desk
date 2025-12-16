package com.learning.tools;

import com.learning.model.Ticket;
import com.learning.service.TicketService;
import com.learning.utils.Convertor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketTool {

    @Autowired
    private TicketService ticketService;

    @Tool(description = "This tool helps to create new ticket in database.")
    public void createTicket(@ToolParam(description = "Ticket fields required to create new ticket") Ticket ticket) {
        try {
            System.out.println("Creating a new ticket...");
            System.out.println(Convertor.convertObjectToString(ticket));
            ticketService.createTicket(ticket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
