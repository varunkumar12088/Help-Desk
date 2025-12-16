package com.learning.service;

import com.learning.constant.ServiceType;
import reactor.core.publisher.Flux;


import java.util.Set;

public interface HelpDeskService {

    Set<ServiceType> supportedTypes();

    Flux<String> chat(String message, String conversionId, String userId);
}
