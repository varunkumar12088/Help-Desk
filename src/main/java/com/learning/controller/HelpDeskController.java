package com.learning.controller;

import com.learning.constant.ServiceType;
import com.learning.dto.ChatRequest;
import com.learning.resolver.AIServiceResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/helpdesk")
public class HelpDeskController {

    @Autowired
    private AIServiceResolver aiServiceResolver;

    @PostMapping(value = "")
    public Flux<String> chat(@RequestBody ChatRequest chatRequest) {
        ServiceType serviceType = chatRequest.getServiceType() != null ?
                chatRequest.getServiceType() : ServiceType.OPEN_AI;
        return aiServiceResolver.resolve(serviceType.getValue())
                .chat(chatRequest.getMessage(), chatRequest.getConversionId(), chatRequest.getUserId());
    }

    @PostMapping(value = "/message")
    public Flux<String> chat(@RequestBody String message,
                             @RequestHeader("UserId") String userId,
                             @RequestHeader("ConversionId") String conversionId) {
        ServiceType serviceType = ServiceType.OPEN_AI;
        return aiServiceResolver.resolve(serviceType.getValue())
                .chat(message, conversionId, userId);
    }
}
