package com.learning.memory;

import com.learning.repository.ChatMemoryRepository;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;


public class HelpDeskChatMemory {

    @Autowired
    @Qualifier("customChatMemoryRepository")
    private ChatMemoryRepository chatMemoryRepository;


}
