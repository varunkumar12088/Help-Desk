package com.learning.controller;

import com.learning.service.HelpDeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/helpdesk")
public class HelpDeskController {

    @Autowired
    private HelpDeskService helpDeskService;

    @PostMapping(value = "")
    public Flux<String> chat(@RequestBody String message,
                             @RequestHeader("conversionId") String conversionId,
                             @RequestHeader("userId") String userId) {
        return helpDeskService.chat(message, conversionId, userId);
    }
}
