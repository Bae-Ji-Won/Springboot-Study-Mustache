package com.mustache.bbs.Controller;

import com.mustache.bbs.Domain.Hospital;
import com.mustache.bbs.Service.HospitalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/hospital")
@Slf4j
public class HospitalController {

    private final HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    // Hospital 대용량 데이터 리스트 만들기(페이징 하여 출력)
    @GetMapping("/list")
    public String list(Pageable pageable,Model model){
        Page<Hospital> hospitals = hospitalService.getHospitalList(pageable);        // 페이징 추가
        model.addAttribute("hospitals",hospitals);
        model.addAttribute("previous",pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next",pageable.next().getPageNumber());
        return "hospital/list";
    }
}
