package pl.wisniea.empik.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wisniea.empik.communication.dto.response.UserResponse;
import pl.wisniea.empik.domain.service.UserService;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/{login}")
    public ResponseEntity<UserResponse> getUser(@PathVariable @NonNull String login) {
        return userService.getUser(login);
    }
}
