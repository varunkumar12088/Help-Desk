package com.learning.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Getter
@Setter
@Document(collection = "chat_memory")
@Builder
public class ChatMemoryDocument {

    @Id
    private String id;
    private String userId;
    private String conversationId;
    private ChatMessage message;
    private Instant timestamp;
}
