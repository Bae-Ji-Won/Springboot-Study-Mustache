package com.mustache.bbs.Domain;

import com.mustache.bbs.Domain.Dto.HospitalResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "nation_wide_hospitals")
@Builder
public class Hospital {
    @Id
    private Integer id;

    @Column(name = "hospital_name") // DB 변수와 entity 변수명이 다를경우 Column을 통해 직접 연결할 수 있다.
    private String hospitalname;

    @Column(name = "full_address")
    private String fulladdress;     // cammel 형식으로 작성시 자동으로 DB에 _ 형식으로 들어감 fullAddress => full_address

    @Column(name = "business_type_name")
    private String businesstypename;

    @Column(name = "road_name_address")
    private String roadNameAddress;     // DB에서 road_name_address를 의미

    @Column(name = "patient_room_count")
    private Integer patientroomcount;   // patient_room_count

    @Column(name = "total_number_of_beds")
    private Integer totalnumberofbeds;

    @Column(name = "total_area_size")
    private Float totalareasize;

    @Column(name = "business_status_code")
    private Integer businessstatuscode;

    // HospitalEntity를 HospitalResponse DTO로 만들어주는 부분
    public static HospitalResponse of(Hospital hospital) {
        return new HospitalResponse(hospital.getId(),
                hospital.getRoadNameAddress(),hospital.getHospitalname(),
                hospital.getPatientroomcount(),hospital.getTotalnumberofbeds(),
                hospital.getBusinesstypename(),hospital.getTotalareasize());
    }
}
