package world.sohail.short_url.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import world.sohail.short_url.dto.UserRequest;
import world.sohail.short_url.entity.User;
import world.sohail.short_url.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserRequest body) {
        User response = userService.createUser(body);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
