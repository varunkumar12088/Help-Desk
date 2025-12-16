package com.learning.dto;

import com.learning.constant.ServiceType;
import lombok.Data;

@Data
public class ChatRequest {

    private String message;
    private String conversionId;
    private String userId;
    private ServiceType serviceType;

}
