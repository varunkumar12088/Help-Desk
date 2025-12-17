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
public class OpenAiHelpDeskServiceImpl implements HelpDeskService {

    @Autowired
    @Qualifier("openAIChatClient")
    private ChatClient  chatClient;

    @Value("classpath:/helpdesk-system.st")
    private Resource systemPromptResource;

    @Value("classpath:/helpdesk-user.st")
    private Resource userPromptResource;


    @Override
    public Set<ServiceType> supportedTypes() {
        return Set.of(ServiceType.GPT,
                ServiceType.OPEN_AI,
                ServiceType.CHAT_GPT
                );
    }

    @Override
    public Flux<String> chat(String message, String conversionId, String userId) {
        try {
            String conversationKey = ChatKey.of(userId, conversionId);
            return this.chatClient
                    .prompt()
                    .system(systemPromptResource)
                    .user(user -> user.text(this.userPromptResource)
                            .param("userId", userId)
                            .param("query", message)
                    )
                    .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationKey))
                    .stream().content();
        } catch (Exception e) {
            e.printStackTrace();
            return Flux.error(e);
        }
    }
}
