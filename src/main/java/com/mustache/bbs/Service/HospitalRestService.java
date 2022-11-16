package com.mustache.bbs.Service;

import com.mustache.bbs.Domain.Dto.HospitalResponse;
import com.mustache.bbs.Domain.Hospital;
import com.mustache.bbs.repository.HospitalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class HospitalRestService {
    private final HospitalRepository hospitalRepository;

    public HospitalRestService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public HospitalResponse getHospital(Integer id){
        Optional<Hospital> opthospital = hospitalRepository.findById(id); // DB에서 id에 해당하는 데이터 가져옴
        Hospital hospital = opthospital.get();                      // 가져온 데이터를 hospital 객체에 저장함
        HospitalResponse hospitalResponse = Hospital.of(hospital);  // Entity인 Hospital의 데이터를 DTO로 보내고 hospitalResponse 객체에 저장

        if(hospital.getBusinessstatuscode() == 13){
            hospitalResponse.setBusinessstatuscode("영업중");
        }else if(hospital.getBusinessstatuscode() == 3){
            hospitalResponse.setBusinessstatuscode("폐업");
        }else{
            hospitalResponse.setBusinessstatuscode(String.valueOf(hospital.getBusinessstatuscode()));
            // getBusinesstatuescode는 String형으로 되어 있으므로 String.valuof를 해서 문자열로 바꿔줌
        }
        return hospitalResponse;

    }
}
