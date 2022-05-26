package com.coding.district.rest;

import com.coding.district.service.DistrictService;
import com.coding.district.service.dto.DistrictVoteDTO;
import com.coding.district.service.dto.GeoDistrictDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DistrictController {

    private final DistrictService districtService;

    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @GetMapping("/districts")
    public List<GeoDistrictDTO> getGeoDistrict() {
        return districtService.getGeoDistrict();
    }

    @GetMapping("/districts/sorted")
    public ResponseEntity<List<DistrictVoteDTO>> getAllDistrictVote(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        List<DistrictVoteDTO> list = districtService.getAllDistrictVote(pageNo, pageSize, sortBy);
        return new ResponseEntity<List<DistrictVoteDTO>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/districts/{id}")
    public DistrictVoteDTO getDistrictVote(@PathVariable("id") Long id) {
        return districtService.getDistrictVote(id);
    }
}
