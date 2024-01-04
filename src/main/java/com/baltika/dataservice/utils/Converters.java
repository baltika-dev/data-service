package com.baltika.dataservice.utils;

import com.baltika.dataservice.model.db.Ally;
import com.baltika.dataservice.model.db.User;
import com.baltika.dataservice.model.request.CreateUserRequest;
import com.baltika.dataservice.model.request.PatchUserRequest;
import com.baltika.dataservice.model.response.UserResponse;

import java.time.OffsetDateTime;

public class Converters {
    public static UserResponse mapUserToUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getAlias().stream().map(Ally::getAlly).toList(),
                user.getFirstname(),
                user.getLastname(),
                user.getMiddlename(),
                user.getCreatedAt(),
                user.getBirthday()
        );
    }

    public static User mapUserRequestToUser(CreateUserRequest createUserRequest) {
        return User.builder()
                .firstname(createUserRequest.firstname())
                .lastname(createUserRequest.lastname())
                .middlename(createUserRequest.middlename())
                .birthday(OffsetDateTime.parse(createUserRequest.birthday()))
                .build();
    }
}
