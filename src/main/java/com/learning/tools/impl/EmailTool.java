package com.learning.tools.impl;

import com.learning.tools.HelpDeskTool;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@Component
public class EmailTool implements HelpDeskTool {

    @Tool(description = "This tool helps to send email to support team regarding new ticket")
    public void sendEmail(@ToolParam(description = "User id associated with ticket for contact information") String userId,
                          @ToolParam(description = "Short description of ticket summary") String message) {
        // Sending email to support team
        System.out.println("Sending email to support team regarding new ticket");
        System.out.println("User id associated with ticket for contact information: " + userId);
        System.out.println("Short description of ticket summary: " + message);
    }
}
