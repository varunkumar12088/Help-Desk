package com.learning.service;

import reactor.core.publisher.Flux;

public interface HelpDeskService {

    Flux<String> chat(String message, String conversionId, String userId);
}
