package com.learning.tools.impl;

import com.learning.constant.Status;
import com.learning.constant.ToolConstant;
import com.learning.constant.ToolParamConstant;
import com.learning.model.Ticket;
import com.learning.service.TicketService;
import com.learning.tools.HelpDeskTool;
import com.learning.utils.Convertor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TicketTool implements HelpDeskTool {

    @Autowired
    private TicketService ticketService;

    @Tool(
            name = ToolConstant.CREATE_TICKET_NAME,
            description = ToolConstant.CREATE_TICKET_DESCRIPTION
    )
    public void createTicket(@ToolParam(description = ToolParamConstant.CREATE_TICKET_DESCRIPTION) Ticket ticket) {
        try {
            System.out.println("Creating a new ticket...");
            System.out.println(Convertor.convertObjectToString(ticket));
            ticketService.createTicket(ticket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Tool(
            name = ToolConstant.GET_USER_TICKETS_NAME,
            description = ToolConstant.GET_USER_TICKETS_DESCRIPTION
    )
    public List<Ticket> getTicket(
            @ToolParam(description = ToolParamConstant.GET_USER_TICKETS_DESCRIPTION) String userId) {
        System.out.println("Getting tickets from database..." + userId);
        List<Ticket> tickets = ticketService.getByUserId(userId).stream()
                .filter(ticket -> !ticket.getStatus().equals(Status.CLOSED))
                .toList();
        System.out.println(Convertor.convertObjectToString(tickets));
        return tickets;
    }

}
