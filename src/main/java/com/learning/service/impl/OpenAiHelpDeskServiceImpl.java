package com.learning.service.impl;

import com.learning.record.ChatKey;
import com.learning.service.HelpDeskService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class OpenAiHelpDeskServiceImpl implements HelpDeskService {

    @Autowired
    @Qualifier("openAIChatClient")
    private ChatClient  chatClient;

    @Value("classpath:/helpdesk-system.st")
    private Resource systemPromptResource;

    @Override
    public Flux<String> chat(String message, String conversionId, String userId) {
        String conversationKey = ChatKey.of(userId, conversionId);
        return this.chatClient
                .prompt()
           //     .system(systemPromptResource)
                .user(message)
                .advisors( a -> a.param(ChatMemory.CONVERSATION_ID, conversationKey))
                .stream().content();
    }
}
