package com.learning.resolver;

import com.learning.constant.ServiceType;
import com.learning.service.HelpDeskService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class AIServiceResolver {

    private final Map<String, HelpDeskService> serviceMap = new HashMap<>();

    public AIServiceResolver(List<HelpDeskService>  services) {
        for(HelpDeskService service : services) {
            for(ServiceType serviceType : service.supportedTypes()) {
                serviceMap.put(serviceType.getValue().toLowerCase(), service);
            }
        }
    }

    public HelpDeskService resolve(String type) {
        return Optional.ofNullable(serviceMap.get(type.toLowerCase()))
                .orElseThrow(() -> new IllegalArgumentException("No service for type: " + type));
    }
}
