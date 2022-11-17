package com.mustache.bbs.Controller;

import com.mustache.bbs.Domain.Dto.HospitalResponse;
import com.mustache.bbs.Service.HospitalRestService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(HospitalRestController.class)
class HospitalRestControllerTest {
     
    @Autowired
    MockMvc mockMvc;
    
    @MockBean   // @Autowired아님 --> HospitalService는 테스트를 위해 가짜 객체를 쓰겠다는 뜻
    HospitalRestService hospitalRestService; // 가짜 객체를 쓰면 좋은점 DB와 상관없이 테스트 가능
    
    @Test
    @DisplayName("Json형태로 Response가 잘 오는지 확인")  // 비즈니스로직(Service를 검증하지 않음) Controller만 테스트를 통해 결과 확인
    void jsonResponse() throws Exception {
        // HospitalResponse에 저장되는 값을 미리 설정함
        // Repository 부분
        HospitalResponse hospitalResponse = HospitalResponse.builder()
                .id(2321)
                .roadnameaddress("서울특별시 서초구 서초중앙로 230, 202호 (반포동, 동화반포프라자빌딩)")
                .hospitalname("노소아청소년과의원")
                .patientroomcount(0)
                .totalnumberofbeds(0)
                .businesstypename("의원")
                .totalareasize(0.0f)
                .businessstatuscode("영업중")
                .build();
        
        // Service 부분
        given(hospitalRestService.getHospital(2321))
                .willReturn(hospitalResponse);

        int hospitalId = 2321;

        // 앞에서 Autowired한 mockMvc
        // Controller 부분
        String url = String.format("/api/v1/hospitals/%d", hospitalId);
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.hospitalname").exists()) // $는 루트 $아래에 hospitalname이 있어야 함
                .andExpect(jsonPath("$.hospitalname").value("노소아청소년과의원"))
                .andDo(print());

        verify(hospitalRestService).getHospital(hospitalId);
 
    }
}
