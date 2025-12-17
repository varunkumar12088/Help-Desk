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

    @Tool(description = "This tool help you to fetch/get user from database based on userId")
    public User getUser(@ToolParam(description = "UserId field is required to fetch/get user") String userId){
        return userService.getById(userId);
    }
}
