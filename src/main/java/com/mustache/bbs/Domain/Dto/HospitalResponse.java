package com.mustache.bbs.Domain.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

// Json을 위한 DTO
public class HospitalResponse {
    private Integer id;
    private String roadnameaddress;
    private String hospitalname;
    private Integer patientroomcount;
    private Integer totalnumberofbeds;
    private String businesstypename;
    private Float totalareasize;
}
