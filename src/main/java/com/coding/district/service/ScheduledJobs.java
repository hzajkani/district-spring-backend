package com.coding.district.service;

import com.coding.district.domain.District;
import com.coding.district.repository.DistrictRepository;
import com.coding.district.service.dto.GeoDistrictDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduledJobs {

    private DistrictRepository districtRepository;
    private ApiServices apiServices;

    private static List<District> districtAll;
    private static int counter;
    private static int sizeDistrict;
    private static List<GeoDistrictDTO> geoDistrictDTOS;

    static {
        geoDistrictDTOS = new ArrayList<GeoDistrictDTO>();
    }

    private static final Logger logger = LoggerFactory.getLogger(ScheduledJobs.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public ScheduledJobs(ApiServices apiServices, DistrictRepository districtRepository) {
        this.apiServices = apiServices;
        this.districtRepository = districtRepository;
    }


    @PostConstruct()
    public void getAllDistricts() {
        districtAll = districtRepository.findAll();
        sizeDistrict = districtAll.size();
    }


    @Scheduled(fixedRate = 1000)
    public void scheduleJobGeoCoordinate() {

        if (counter <= sizeDistrict) {
            District district = districtAll.get(counter);
            String nameValid = apiServices.makeNameValid(district);
            GeoDistrictDTO geoDistrictDTO = new GeoDistrictDTO();
            geoDistrictDTO.setDistrictId(district.getId());
            geoDistrictDTO.setDistrictName(district.getName());
            geoDistrictDTO.setDistrictLon(apiServices.createGeoCoordinate(apiServices.callEndPoint(nameValid)).getLon());
            geoDistrictDTO.setDistrictLat(apiServices.createGeoCoordinate(apiServices.callEndPoint(nameValid)).getLat());
            geoDistrictDTOS.add(geoDistrictDTO);
            counter++;
            logger.info("GeoCoordinate Job for " + district.getName() + "  :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));

        } else {
            logger.info("There is no other District for GeoCoordinate  :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        }
    }
}
