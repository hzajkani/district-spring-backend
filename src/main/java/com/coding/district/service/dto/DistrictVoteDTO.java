package com.coding.district.service.dto;

import lombok.Data;

import java.util.List;

@Data
public class DistrictVoteDTO {

    private Long districtId;
    private String districtName;
    private Double districtArea;
    private Double districtPopulation;
    private Double districtForeignerPct;
    private Double districtAgeLt18Pct;
    private Double districtAgeGt75Pct;
    private Double districtAreaSettlePct;
    private Double districtAreaNaturePct;
    private Double districtLivingSpace;
    private Double districtCars;
    private Double districtIncome;
    private Double districtBip;
    private Double districtUnemploymentRate;

    private List<String> voteNames;
    private List<Double> voteTypes;
    private List<Double> votePcts;

}
