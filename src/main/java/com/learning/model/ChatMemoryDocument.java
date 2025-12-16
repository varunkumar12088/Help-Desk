package com.learning.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Getter
@Setter
@Document(collation = "chat_memory")
@Builder
public class ChatMemoryDocument {

    @Id
    private String id;
    private String conversationId;
    private Message message;
    private Instant timestamp;
}
