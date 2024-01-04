package com.baltika.dataservice.service;

import com.baltika.dataservice.exceptions.PatchUserException;
import com.baltika.dataservice.model.db.Ally;
import com.baltika.dataservice.model.db.User;
import com.baltika.dataservice.model.request.CreateUserRequest;
import com.baltika.dataservice.model.request.PatchUserRequest;
import com.baltika.dataservice.model.response.UserResponse;
import com.baltika.dataservice.repository.AliasRepository;
import com.baltika.dataservice.repository.UserRepository;
import com.baltika.dataservice.utils.Converters;
import com.dm4nk.aop.logger.annotations.Loggable;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@Loggable
@AllArgsConstructor
public class UserService {
    private static final Sort DEFAULT_SORTING = Sort.by(Sort.Order.desc("createdAt"));

    private final UserRepository userRepository;
    private final AliasRepository aliasRepository;

    public ResponseEntity<List<UserResponse>> getAllUsers(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, DEFAULT_SORTING);

        return ResponseEntity.ok(userRepository.findAll(pageable)
                .stream()
                .map(Converters::mapUserToUserResponse)
                .toList());
    }

    public ResponseEntity<UserResponse> getUserById(UUID id) {
        UserResponse userResponse = userRepository.findById(id)
                .map(Converters::mapUserToUserResponse)
                .orElse(null);

        return userResponse == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(userResponse);
    }

    public ResponseEntity<UserResponse> getUserByAlly(String ally) {
        UserResponse userResponse = userRepository.findByAlly(ally)
                .map(Converters::mapUserToUserResponse)
                .orElse(null);

        return userResponse == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(userResponse);
    }

    @Transactional
    public ResponseEntity<UserResponse> createUser(CreateUserRequest createUserRequest) {
        User user = Converters.mapUserRequestToUser(createUserRequest);
        user.setCreatedAt(OffsetDateTime.now());
        user.setModifiedAt(OffsetDateTime.now());
        user.setId(UUID.randomUUID());
        user = userRepository.saveAndFlush(user);

        Ally ally = Ally.builder()
                .ally(createUserRequest.ally())
                .user(user)
                .build();
        ally = aliasRepository.saveAndFlush(ally);
        user.setAlias(Collections.singletonList(ally));
        UserResponse userResponse = Converters.mapUserToUserResponse(user);

        return ResponseEntity.ok(userResponse);
    }

    @Transactional
    public ResponseEntity<UserResponse> updateUser(PatchUserRequest patchUserRequest) {
        if (patchUserRequest.id() == null) {
            throw new PatchUserException("Id is not provided");
        }

        User user = userRepository.findById(patchUserRequest.id())
                .orElseThrow(() -> new PatchUserException("No suer with such Id"));

        user.setBirthday(OffsetDateTime.parse(patchUserRequest.birthday()));
        user.setModifiedAt(OffsetDateTime.now());
        user.setFirstname(patchUserRequest.firstname());
        user.setLastname(patchUserRequest.lastname());
        user.setMiddlename(patchUserRequest.middlename());

        user = userRepository.saveAndFlush(user);
        UserResponse userResponse = Converters.mapUserToUserResponse(user);

        return ResponseEntity.ok(userResponse);
    }

    @Transactional
    public ResponseEntity<UserResponse> deleteUser(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new PatchUserException("No suer with such Id"));

        userRepository.delete(user);
        UserResponse userResponse = Converters.mapUserToUserResponse(user);

        return ResponseEntity.ok(userResponse);
    }
}
