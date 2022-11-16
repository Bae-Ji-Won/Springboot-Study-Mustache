package com.mustache.bbs.Controller;

import com.mustache.bbs.Domain.Dto.HospitalResponse;
import com.mustache.bbs.Domain.Hospital;
import com.mustache.bbs.Service.HospitalRestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hospitals")
@Slf4j
public class HospitalRestController {

    private final HospitalRestService hospitalRestService;

    public HospitalRestController(HospitalRestService hospitalRestService) {
        this.hospitalRestService = hospitalRestService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<HospitalResponse> get(@PathVariable Integer id){
        HospitalResponse hospitalResponse = hospitalRestService.getHospital(id);
        log.info(String.valueOf(hospitalResponse));
        return ResponseEntity.ok().body(hospitalResponse); // Return은 DTO로
    }
}
