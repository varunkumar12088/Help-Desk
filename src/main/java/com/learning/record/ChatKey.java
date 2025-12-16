package com.learning.record;

import org.apache.commons.lang3.StringUtils;

public record ChatKey(String userId, String conversationId) {

    public static ChatKey from(String raw) {

        if (StringUtils.isBlank(raw) || !raw.contains("::")) {
            throw new IllegalArgumentException(
                    "Invalid conversation key. Expected format: userId::conversationId"
            );
        }

        String[] parts = raw.split("::", 2);

        if (StringUtils.isAnyBlank(parts[0], parts[1])) {
            throw new IllegalArgumentException(
                    "userId or conversationId cannot be blank"
            );
        }

        return new ChatKey(parts[0], parts[1]);
    }

    public static String of(String userId, String conversationId) {
        return userId + "::" + conversationId;
    }
}
