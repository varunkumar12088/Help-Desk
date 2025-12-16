package com.learning.repository;

import com.learning.model.ChatMemoryDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HelpDeskChatMemoryRepository extends MongoRepository<ChatMemoryDocument, String> {

    List<ChatMemoryDocument> findByUserIdAndConversationId(
            String userId,
            String conversationId
    );
}
