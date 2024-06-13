package org.example.supportservice.controller.auth;

import lombok.AllArgsConstructor;
import org.example.supportservice.user.dto.UserDto;
import org.example.supportservice.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
@AllArgsConstructor
public class registrationController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity register(@RequestBody final UserDto user) {
        return userService.register(user);
    }
}
