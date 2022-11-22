package com.mustache.bbs.Service;

import com.mustache.bbs.Domain.Dto.User.UserRequest;
import com.mustache.bbs.Domain.Dto.User.UserResponse;
import com.mustache.bbs.Domain.User;
import com.mustache.bbs.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

// Service Mock을 통한 Test하는법
class UserServiceTest {

    private UserRepository userRepository = Mockito.mock(UserRepository.class);     // Repository를 Mock을 통해 가짜 객채로 생성

    private UserService userService;

    @BeforeEach
    void setUp(){
        userService = new UserService(userRepository);  // 수동 DI 해줌
    }

    @Test
    @DisplayName("회원 등록 성공 메세지가 나오는지")
    void addTest(){
        Mockito.when(userRepository.save(any()))
                .thenReturn(new User(1l,"test","1234"));

        UserResponse userResponse = userService.save(new UserRequest("test","1234"));
        assertEquals("회원등록성공",userResponse.getMessage());
    }
}