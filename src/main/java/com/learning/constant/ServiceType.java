package com.learning.constant;

public enum ServiceType {

    OPEN_AI("OpenAI"),
    DEEPSEEK("Deepseek"),
    GPT("GPT"),
    CHAT_GPT("ChatGpt");

    private String value;

    ServiceType(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
