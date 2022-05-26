package com.coding.district.service;

import com.coding.district.service.dto.GeoDistrictDTO;
import com.coding.district.domain.District;
import com.coding.district.domain.Vote;
import com.coding.district.repository.DistrictRepository;
import com.coding.district.service.dto.DistrictVoteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DistrictService {

    private final ApiServices apiServices;

    private final DistrictRepository districtRepository;

    public DistrictService(ApiServices apiServices, DistrictRepository districtRepository) {
        this.apiServices = apiServices;
        this.districtRepository = districtRepository;
    }

    public List<GeoDistrictDTO> getGeoDistrict() {
        return districtRepository.findAll().stream().map(v -> createGeoDistrictDTO(v)).collect(Collectors.toList());
    }

    public List<DistrictVoteDTO> getAllDistrictVote(Integer pageNo, Integer pageSize, String sortBy) {
        List<District> districts;
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<District> pagedResult = districtRepository.findAll(paging);
        if (pagedResult.hasContent()) {
            districts = pagedResult.getContent();
        } else {
            return new ArrayList<DistrictVoteDTO>();
        }
        return districts.stream().map(a -> createDistrictVoteDTO(a)).collect(Collectors.toList());
    }

    public DistrictVoteDTO getDistrictVote(Long id) {
        Optional<District> district = districtRepository.findById(id);
        DistrictVoteDTO districtVoteDTO = createDistrictVoteDTO(district.get());
        return districtVoteDTO;
    }


    private DistrictVoteDTO createDistrictVoteDTO(District district) {

        DistrictVoteDTO districtVoteDTO = new DistrictVoteDTO();

        List<String> voteNames = district.getVotes().stream().map(Vote::getName).collect(Collectors.toList());
        List<Double> voteTypes = district.getVotes().stream().map(Vote::getVoteType).collect(Collectors.toList());
        List<Double> votePcts = district.getVotes().stream().map(Vote::getPct).collect(Collectors.toList());

        districtVoteDTO.setDistrictId(district.getId());
        districtVoteDTO.setDistrictName(district.getName());
        districtVoteDTO.setDistrictArea(district.getArea());
        districtVoteDTO.setDistrictPopulation(district.getPopulation());
        districtVoteDTO.setDistrictForeignerPct(district.getForeignerPct());
        districtVoteDTO.setDistrictAgeLt18Pct(district.getAgeLt18Pct());
        districtVoteDTO.setDistrictAgeGt75Pct(district.getAgeGt75Pct());
        districtVoteDTO.setDistrictAreaSettlePct(district.getAreaSettlePct());
        districtVoteDTO.setDistrictAreaNaturePct(district.getAreaNaturePct());
        districtVoteDTO.setDistrictLivingSpace(district.getLivingSpace());
        districtVoteDTO.setDistrictCars(district.getCars());
        districtVoteDTO.setDistrictIncome(district.getIncome());
        districtVoteDTO.setDistrictBip(district.getBip());
        districtVoteDTO.setDistrictUnemploymentRate(district.getUnemploymentRate());

        districtVoteDTO.setVoteNames(voteNames);
        districtVoteDTO.setVoteTypes(voteTypes);
        districtVoteDTO.setVotePcts(votePcts);

        return districtVoteDTO;
    }

    public GeoDistrictDTO createGeoDistrictDTO(District district) {
        GeoDistrictDTO geoDistrictDTO = new GeoDistrictDTO();

        geoDistrictDTO.setDistrictId(district.getId());
        geoDistrictDTO.setDistrictName(district.getName());
        geoDistrictDTO.setDistrictLon(apiServices.createGeoCoordinate(apiServices.callEndPoint(apiServices.makeNameValid(district))).getLon());
        geoDistrictDTO.setDistrictLat(apiServices.createGeoCoordinate(apiServices.callEndPoint(apiServices.makeNameValid(district))).getLat());

        return geoDistrictDTO;
    }
}
