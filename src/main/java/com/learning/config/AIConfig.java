package com.learning.config;

import com.learning.memory.HelpDeskChatMemory;
import com.learning.properties.AiProperties;
import com.learning.repository.HelpDeskChatMemoryRepository;
import com.learning.utils.Convertor;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class AIConfig {

    private final AiProperties aiProperties;

    @Value("${safe.guard}")
    private List<String> safeGuard;

    @Bean("openAIChatClient")
    public ChatClient chatClientOpenAI(HelpDeskChatMemory chatMemory) {
        System.out.println(Convertor.convertObjectToString(this.aiProperties));
        return ChatClient.builder(
                        OpenAiChatModel.builder()
                                .openAiApi(OpenAiApi.builder()
                                        .baseUrl(aiProperties.openAI().baseUrl())
                                        .apiKey(aiProperties.openAI().apiKey())
                                        .build())
                                .build()
                )
                .defaultAdvisors(new SimpleLoggerAdvisor(),
                        new SafeGuardAdvisor(safeGuard),
                        MessageChatMemoryAdvisor.builder(chatMemory)
                                .build())
                .defaultOptions(ChatOptions.builder()
                        .model(aiProperties.openAI().modelName())
                        .temperature(aiProperties.openAI().temperature())
                        .topP(aiProperties.openAI().topP())
                        .maxTokens(aiProperties.openAI().maxTokens())
                        .build())
                .defaultTools()
                .build();
    }

    @Bean("deepseekChatClient")
    public ChatClient chatClientDeepseek(HelpDeskChatMemory chatMemory) {
        System.out.println(Convertor.convertObjectToString(this.aiProperties));
        return ChatClient.builder(
                OpenAiChatModel.builder()
                        .openAiApi(OpenAiApi.builder()
                                .baseUrl(aiProperties.deepseek().baseUrl())
                                .apiKey(aiProperties.deepseek().apiKey())
                                .build())
                        .build()
                )
                .defaultAdvisors(new SimpleLoggerAdvisor(),
                        new SafeGuardAdvisor(safeGuard),
                        MessageChatMemoryAdvisor.builder(chatMemory)
                                .build())
                .defaultOptions(ChatOptions.builder()
                        .model(aiProperties.deepseek().modelName())
                        .temperature(aiProperties.deepseek().temperature())
                        .topP(aiProperties.deepseek().topP())
                        .maxTokens(aiProperties.deepseek().maxTokens())
                        .build())
                .defaultTools()
                .build();
    }

    @Bean
    public HelpDeskChatMemory helpDeskChatMemory(HelpDeskChatMemoryRepository memoryRepository){
        return HelpDeskChatMemory.builder()
                .chatMemoryRepository(memoryRepository)
                .maxMessages(aiProperties.maxMessages())
                .build();
    }

}

