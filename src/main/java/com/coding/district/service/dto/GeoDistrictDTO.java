package com.coding.district.service.dto;

import lombok.Data;

@Data
public class GeoDistrictDTO {

    private Long districtId;
    private String districtName;
    private Double districtLon;
    private Double districtLat;

}
