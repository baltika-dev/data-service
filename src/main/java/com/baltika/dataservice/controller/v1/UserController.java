package com.baltika.dataservice.controller.v1;

import com.baltika.dataservice.model.request.CreateUserRequest;
import com.baltika.dataservice.model.response.UserResponse;
import com.baltika.dataservice.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAllUsers(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "50") Integer pageSize
    ) {
        return userService.getAllUsers(pageNumber, pageSize);
    }

    @GetMapping("/id")
    public ResponseEntity<UserResponse> getUserById(@RequestParam UUID id) {
        return userService.getUserById(id);
    }

    @GetMapping("/ally")
    public ResponseEntity<UserResponse> getUserByAlly(@RequestParam String ally) {
        return userService.getUserByAlly(ally);
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest createUserRequest) {
        return userService.createUser(createUserRequest);
    }
}
