package com.mustache.bbs.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mustache.bbs.Domain.Dto.User.UserRequest;
import com.mustache.bbs.Domain.Dto.User.UserResponse;
import com.mustache.bbs.Service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired      // json 형식을 위함
    ObjectMapper objectMapper;

    @MockBean       // 가짜 Service 객체
    UserService  userService;

    @Test
    @DisplayName("유저 신규 등록")
    void saveuser() throws Exception {
        UserRequest userRequest = new UserRequest("test","1234");
        UserResponse userResponse = new UserResponse(1l,"test","id가 등록되었습니다.");

        given(userService.save(userRequest))
                .willReturn(userResponse);

        mockMvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)        // Json 타입으로 사용
                        .content(objectMapper.writeValueAsBytes(userRequest))   // 삽입한 데이터 dto를 json 형식으로 변환
                )

                // 입력된 데이터와 예측되는 값을 비교함
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").exists())
                .andExpect(jsonPath("$.username").value("test"))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").value("id가 등록되었습니다."))
                .andDo(print());

        verify(userService).save(userRequest);            // service의 add 메서드가 실행됬는지 확인함
        // service의 테스트 결과 확인
    }

//    @Test
//    @DisplayName("유저 등록 실패 이미 존재")
//    void failuser(){
//
//    }
}