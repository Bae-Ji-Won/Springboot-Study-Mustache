package com.mustache.bbs.Controller;

import com.mustache.bbs.Domain.Dto.User.UserRequest;
import com.mustache.bbs.Domain.Dto.User.UserResponse;
import com.mustache.bbs.Domain.User;
import com.mustache.bbs.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> find(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(userService.findbyid(id));
    }

    @PostMapping()
    public ResponseEntity<UserResponse> save(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok().body(userService.save(userRequest));
    }
}
