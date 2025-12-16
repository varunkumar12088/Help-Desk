package com.learning.repository;

import com.learning.model.ChatMemoryDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("customChatMemoryRepository")
public interface ChatMemoryRepository extends MongoRepository<ChatMemoryDocument, String> {

    Optional<ChatMemoryDocument> findByConversationId(String conversationId);
}
