package com.baltika.dataservice.model.request;

public record CreateUserRequest(
        String ally,
        String firstname,
        String lastname,
        String middlename,
        String birthday
) {
}
