package com.coding.district.service.dto;

import lombok.Data;

@Data
public class VoteDTO {

    private String partyType;
    private String name;
    private Double ranked;
    private Double voteType;
    private Double count;
    private Double pct;

    private Long districtId;
    private String districtName;
    private Double districtLat;
    private Double districtLon;
}
