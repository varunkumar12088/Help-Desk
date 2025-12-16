package com.learning.service.impl;

import com.learning.constant.ServiceType;
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

import java.util.Set;

@Service
public class DeepseekHelpDeskServiceImpl implements HelpDeskService {

    @Autowired
    @Qualifier("deepseekChatClient")
    private ChatClient chatClient;

    @Value("classpath:/helpdesk-system.st")
    private Resource systemPromptResource;

    @Override
    public Set<ServiceType> supportedTypes() {
        return Set.of(ServiceType.DEEPSEEK);
    }

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

