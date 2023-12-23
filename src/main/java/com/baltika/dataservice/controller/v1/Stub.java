package com.baltika.dataservice.controller.v1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class Stub {
    @GetMapping("api/v1/stub")
    public String stub() {
        return "Hello";
    }
}
