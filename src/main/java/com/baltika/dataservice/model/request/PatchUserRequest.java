package com.baltika.dataservice.model.request;

import java.util.UUID;

public record PatchUserRequest(
        UUID id,
        String firstname,
        String lastname,
        String middlename,
        String birthday
) {
}
