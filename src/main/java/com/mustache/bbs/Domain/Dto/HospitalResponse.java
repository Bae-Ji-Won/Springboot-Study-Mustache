package com.mustache.bbs.Domain.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
// Json을 위한 DTO
public class HospitalResponse {
    private Integer id;
    private String roadnameaddress;
    private String hospitalname;
    private Integer patientroomcount;
    private Integer totalnumberofbeds;
    private String businesstypename;
    private Float totalareasize;
    private String businessstatuscode;


    // businessStatuesName을 제외한 생성자 생성
    // 다른것들은 DB에서 받아서 그대로 json으로 쏘는데 businessStatuesName은 디비에서 숫자를 받아와 Service에서 비교 후
    // Set을 통해 값을 삽입후 json으로 보내기 때문에
    public HospitalResponse(Integer id, String roadnameaddress, String hospitalname, Integer patientroomcount, Integer totalnumberofbeds, String businesstypename, Float totalareasize) {
        this.id = id;
        this.roadnameaddress = roadnameaddress;
        this.hospitalname = hospitalname;
        this.patientroomcount = patientroomcount;
        this.totalnumberofbeds = totalnumberofbeds;
        this.businesstypename = businesstypename;
        this.totalareasize = totalareasize;
    }

    // DB에서는 int로 값을 받아와서 Service에서 비교 후 문자열로 바꿔줌
    public void setBusinessstatuscode(String businessstatuscode) {
        this.businessstatuscode = businessstatuscode;
    }



}
