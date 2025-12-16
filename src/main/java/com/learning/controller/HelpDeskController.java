package com.learning.controller;

import com.learning.constant.ServiceType;
import com.learning.dto.ChatRequest;
import com.learning.resolver.AIServiceResolver;
import com.learning.service.HelpDeskService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
