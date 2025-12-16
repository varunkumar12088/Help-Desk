package com.learning.memory;

import com.learning.model.ChatMemoryDocument;
import com.learning.model.ChatMessage;
import com.learning.record.ChatKey;
import com.learning.repository.HelpDeskChatMemoryRepository;
import lombok.Builder;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Builder
public class HelpDeskChatMemory implements ChatMemory {

    private final int maxMessages;

    private final HelpDeskChatMemoryRepository chatMemoryRepository;


    @Override
    public void add(String conversationKey, List<Message> messages) {
        ChatKey chatKey = ChatKey.from(conversationKey);
        List<ChatMemoryDocument> chatMemoryDocuments = messages.stream()
                .map(message -> ChatMemoryDocument.builder()
                    .id(String.valueOf(UUID.randomUUID()))
                    .timestamp(Instant.now())
                    .conversationId(chatKey.conversationId())
                    .userId(chatKey.userId())
                    .message(ChatMessage.builder()
                            .type(message.getMessageType().getValue())
                            .metadata(message.getMetadata())
                            .content(message.getText())
                            .build())
                    .build()).toList();
        chatMemoryRepository.saveAll(chatMemoryDocuments);
    }

    @Override
    public List<Message> get(String conversationKey) {
        ChatKey chatKey = ChatKey.from(conversationKey);
        Pageable pageable = PageRequest.of(0, maxMessages, Sort.by(Sort.Direction.DESC, "timestamp"));
        List<ChatMemoryDocument> memoryDocuments = chatMemoryRepository.findLatestRecord(
                chatKey.userId(),
                chatKey.conversationId(),
                pageable);
        System.out.println("total messages: " + memoryDocuments.size());
        return memoryDocuments.stream().map(memoryDocument -> memoryDocument.getMessage())
                .filter(ObjectUtils::isNotEmpty)
                .map(chatMessage -> toMessage(chatMessage))
                .filter(ObjectUtils::isNotEmpty)
                .toList();
    }

    @Override
    public void clear(String conversationId) {

    }

    private Message toMessage(ChatMessage entity) {
        return switch (entity.getType().toUpperCase()) {
            case "USER" -> new UserMessage(entity.getContent());
            case "ASSISTANT" -> new AssistantMessage(entity.getContent());
            case "SYSTEM" -> new SystemMessage(entity.getContent());
            default -> throw new IllegalStateException();
        };
    }
}
