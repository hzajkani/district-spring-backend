package com.coding.district.service;

import com.coding.district.domain.Vote;
import com.coding.district.repository.VoteRepository;
import com.coding.district.service.dto.GeoDistrictDTO;
import com.coding.district.service.dto.VoteDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VoteService {

    private final ApiServices apiServices;
    private final VoteRepository voteRepository;

    public VoteService(ApiServices apiServices, VoteRepository voteRepository) {
        this.apiServices = apiServices;
        this.voteRepository = voteRepository;
    }

    public Set<String> uniqueVoteNames() {
        return voteRepository.findAll().stream().map(Vote::getName).collect(Collectors.toSet());
    }

    public List<VoteDTO> findVoteByName(String name) {
        List<Vote> votes = voteRepository.findAllByNameContains(name);
        return votes.stream().map(v -> createVoteDTO(v)).collect(Collectors.toList());
    }

    private VoteDTO createVoteDTO(Vote vote) {
        VoteDTO voteDTO = new VoteDTO();
        GeoDistrictDTO geoDistrictDTO = createGeoDistrictDTO(vote);

        voteDTO.setPartyType(vote.getPartyType());
        voteDTO.setName(vote.getName());
        voteDTO.setRanked(vote.getRanked());
        voteDTO.setVoteType(vote.getVoteType());
        voteDTO.setCount(vote.getCount());
        voteDTO.setPct(vote.getPct());

        voteDTO.setDistrictId(geoDistrictDTO.getDistrictId());
        voteDTO.setDistrictName(geoDistrictDTO.getDistrictName());
        voteDTO.setDistrictLat(geoDistrictDTO.getDistrictLat());
        voteDTO.setDistrictLon(geoDistrictDTO.getDistrictLon());

        return voteDTO;
    }

    private GeoDistrictDTO createGeoDistrictDTO(Vote vote) {
        GeoDistrictDTO geoDistrictDTO = new GeoDistrictDTO();

        geoDistrictDTO.setDistrictId(vote.getDistrict().getId());
        geoDistrictDTO.setDistrictName(vote.getDistrict().getName());
        geoDistrictDTO.setDistrictLon(apiServices.createGeoCoordinate(apiServices.callEndPoint(apiServices.makeNameValid(vote.getDistrict()))).getLon());
        geoDistrictDTO.setDistrictLat(apiServices.createGeoCoordinate(apiServices.callEndPoint(apiServices.makeNameValid(vote.getDistrict()))).getLat());

        return geoDistrictDTO;
    }
}
