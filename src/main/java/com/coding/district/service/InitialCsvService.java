package com.coding.district.service;

import com.coding.district.domain.District;
import com.coding.district.domain.Vote;
import com.coding.district.repository.DistrictRepository;
import com.coding.district.repository.VoteRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InitialCsvService {

    private final DistrictRepository districtRepository;

    private final VoteRepository voteRepository;

    @Value("${district.csv.path}")
    private String districtPath;


    @Value("${vote.csv.path}")
    private String votePath;

    public InitialCsvService(DistrictRepository districtRepository, VoteRepository voteRepository) {
        this.districtRepository = districtRepository;
        this.voteRepository = voteRepository;
    }


    @PostConstruct
    public void init() {
        Boolean firstLine = false;
        String line = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(districtPath));
            while ((line = bufferedReader.readLine()) != null) {

                if (!firstLine) {
                    String[] data = line.split(";");
                    // Maybe use for a purpose in future
                }

                if (firstLine) {

                    String[] data = StringUtils.split(line, ";:,");
                    District district = new District();
                    district.setId(Long.valueOf(data[0]));
                    district.setName(data.length > 1 && data[1] != null ? data[1] : "");
                    district.setArea(data.length > 2 && NumberUtils.isParsable(data[2]) ? Double.valueOf(data[2]) : 0);
                    district.setPopulation(data.length > 3 && NumberUtils.isParsable(data[3]) ? Double.valueOf(data[3]) : 0);
                    district.setForeignerPct(data.length > 4 && NumberUtils.isParsable(data[4]) ? Double.valueOf(data[4]) : 0);
                    district.setAgeLt18Pct(data.length > 7 && NumberUtils.isParsable(data[7]) ? Double.valueOf(data[7]) : 0);
                    district.setAgeGt75Pct(data.length > 12 && NumberUtils.isParsable(data[12]) ? Double.valueOf(data[12]) : 0);
                    district.setAreaSettlePct(data.length > 13 && NumberUtils.isParsable(data[13]) ? Double.valueOf(data[13]) : 0);
                    district.setAreaNaturePct(data.length > 14 && NumberUtils.isParsable(data[14]) ? Double.valueOf(data[14]) : 0);
                    district.setLivingSpace(data.length > 16 && NumberUtils.isParsable(data[16]) ? Double.valueOf(data[16]) : 0);
                    district.setCars(data.length > 17 && NumberUtils.isParsable(data[17]) ? Double.valueOf(data[17]) : 0);
                    district.setIncome(data.length > 18 && NumberUtils.isParsable(data[18]) ? Double.valueOf(data[18]) : 0);
                    district.setBip(data.length > 19 && NumberUtils.isParsable(data[19]) ? Double.valueOf(data[19]) : 0);
                    district.setUnemploymentRate(data.length > 20 && NumberUtils.isParsable(data[20]) ? Double.valueOf(data[20]) : 0);

                    districtRepository.save(district);

                }
                firstLine = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        initVoteList();
    }

    public void initVoteList() {
        Boolean firstLineVote = false;
        String line = "";
        List<Vote> voteList = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(votePath));
            while ((line = bufferedReader.readLine()) != null) {


                if (!firstLineVote) {
                    String[] dataHeader = StringUtils.split(line, ";");
                }
                if (firstLineVote) {
                    String[] data = StringUtils.split(line, ";:,");
                    Vote vote = new Vote();

                    vote.setDistrict(districtRepository.findById(Long.valueOf(data[0])).get());
                    vote.setPartyType(data.length > 1 && data[1] != null ? data[1] : "");
                    vote.setName(data.length > 2 && data[2] != null ? data[2] : "");
                    vote.setRanked(data.length > 3 && NumberUtils.isParsable(data[3]) ? Double.valueOf(data[3]) : 0);
                    vote.setVoteType(data.length > 4 && NumberUtils.isParsable(data[4]) ? Double.valueOf(data[4]) : 0);
                    vote.setCount(data.length > 5 && NumberUtils.isParsable(data[5]) ? Double.valueOf(data[5]) : 0);
                    vote.setPct(data.length > 6 && NumberUtils.isParsable(data[6]) ? Double.valueOf(data[6]) : 0);

                    voteList.add(vote);
                }
                firstLineVote = true;
            }
            voteList.stream()
                    .sorted(Comparator.comparingDouble(Vote::getCount).reversed())
                    .peek(voteRepository::save)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
