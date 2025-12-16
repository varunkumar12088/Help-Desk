package com.learning.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("ai")
public record AiProperties(
    Integer maxMessages,
    OpenAI openAI,
    Deepseek deepseek

) {
    public record OpenAI(
        String baseUrl,
        String modelName,
        String apiKey,
        Double temperature,
        Double topP,
        Integer maxTokens
    ){ }
    public record Deepseek(
            String baseUrl,
            String modelName,
            String apiKey,
            Double temperature,
            Double topP,
            Integer maxTokens
    ){ }
}
