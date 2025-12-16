package com.learning.repository;

import com.learning.model.ChatMemoryDocument;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HelpDeskChatMemoryRepository extends MongoRepository<ChatMemoryDocument, String> {

    @Query(
            value = "{ 'userId': ?0, 'conversationId': ?1 }",
            sort = "{ 'timestamp': -1 }"
    )
    List<ChatMemoryDocument> findLatestRecord(
            String userId,
            String conversationId,
            Pageable pageable
    );
}
