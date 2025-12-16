package com.learning.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/helpdesk")
public class HelpDeskController {


    @PostMapping(value = "")
    public Flux<String> chat(@RequestBody String message,
                             @RequestHeader("conversionId") String conversionId,
                             @RequestHeader("userId") String userId) {
        return Flux.just("Hello World!");
    }
}
