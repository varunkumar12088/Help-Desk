package com.learning.constant;

public interface ToolConstant {

    String CREATE_TICKET_NAME = "create_support_ticket";
    String CREATE_TICKET_DESCRIPTION = """
                    Create a NEW support ticket.
                    Call get_user_open_tickets and check having similar ticket or not.
                    NEVER call this tool if any OPEN or IN_PROGRESS ticket exists.
                   """;

    String GET_USER_TICKETS_NAME = "get_user_open_tickets";
    String GET_USER_TICKETS_DESCRIPTION = """
                   MANDATORY STEP BEFORE CREATING A TICKET.
                   Fetch all OPEN or IN_PROGRESS tickets for a user from the database.
                   You MUST call this tool before creating a new ticket.
                   If any similar ticket exists, DO NOT create a new one.
            """;






}
