package com.mustache.bbs.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mustache.bbs.Domain.Dto.ArticleAddRequest;
import com.mustache.bbs.Domain.Dto.ArticleAddResponse;
import com.mustache.bbs.Domain.Dto.ArticleDto;
import com.mustache.bbs.Service.ArticleRestService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleRestContorller.class)            // Test할 Controller 클래스 지정
class ArticleRestContorllerTest {

    @Autowired
    MockMvc mockMvc;            // MockMvc를 의존받음

    @Autowired
    ObjectMapper objectMapper;         // json 형식으로 변환하기 위해 사용하는 라이브러리

    @MockBean                // 가짜 객체
    ArticleRestService articleRestService;

    @Test
    @DisplayName("Controller에서 값 잘 받아 오는지 테스트")
    void JsonResponse() throws Exception {
        Long id = 1l;

        // Repository 데이터 삽입
        ArticleAddResponse articleDto = new ArticleAddResponse(1l,"첫번째 글","내용입니다.");
        
        // Service 부분
        given(articleRestService.getfind(id))
                .willReturn(articleDto);

        // 앞에서 Autowired한 mockMvc
        // Controller 부분
        String url = String.format("/api/v1/articles/%d", id);
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                // id,title,content에 값이 들어있는지 확인
                .andExpect(jsonPath("$.id").exists()) // $는 루트 아래에 값이 있다를 의미
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.content").exists())

                // Dto에 저장된 값과 아래의 기대값을 비교한다.
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.title").value("첫번째 글"))
                .andExpect(jsonPath("$.content").value("내용입니다."))
                .andDo(print());

        verify(articleRestService).getfind(id);
    }

    @Test
    @DisplayName("글 등록이 잘 되는지 확인")
    // json 형식으로 값 입력받아 저장되는지 확인
    void add() throws Exception {
        ArticleAddRequest dto = new ArticleAddRequest("제목입니다.","내용입니다.");
        ArticleAddResponse resp = new ArticleAddResponse(1l,dto.getTitle(),dto.getContent());

        // 가짜 객체로 현재 여기서 any를 통해 articleRestService는 어떤값을 받아도 무조건 resp를 반환한다.
        // (service 테스트내용) -> given을 통해 service에 들어가는 객체와 출력되는 객체를 지정하고
        //  verify를 통해 service 메서드의 실행결과 확인함
        given(articleRestService.add(any()))
                .willReturn(resp);

        // controller 테스트 내용
        mockMvc.perform(post("/api/v1/articles")
                        .contentType(MediaType.APPLICATION_JSON)        // Json 타입으로 사용
                        .content(objectMapper.writeValueAsBytes(dto))   // 삽입한 데이터 dto를 json 형식으로 변환
                )
                
                // 입력된 데이터와 예측되는 값을 비교함
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())   // 존재여부 확인
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.title").value("제목입니다."))
                .andExpect(jsonPath("$.content").exists())
                .andDo(print());

      verify(articleRestService).add(dto);            // service의 add 메서드가 실행됬는지 확인함
                                                        // service의 테스트 결과 확인
    }
}