package com.baltika.dataservice.model.response;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record UserResponse(
        UUID id,
        List<String> alias,
        String firstname,
        String lastname,
        String middlename,
        OffsetDateTime createdAt,
        OffsetDateTime birthday
) {
}
