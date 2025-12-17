package com.learning.tools.impl;

import com.learning.model.User;
import com.learning.service.UserService;
import com.learning.tools.HelpDeskTool;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserTool implements HelpDeskTool {

    @Autowired
    private UserService userService;

    @Tool(description = "This tool help you to get user from database based on userId")
    public User getUser(@ToolParam(description = "UserId whose user is required") String userId){
        System.out.println("Tool is calling get user method in UserTool");
        return userService.getById(userId);
    }
}
