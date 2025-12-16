package com.learning.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class ChatMessage {

    private String content;
    private String type;
    private Map<String, Object> metadata;

}
