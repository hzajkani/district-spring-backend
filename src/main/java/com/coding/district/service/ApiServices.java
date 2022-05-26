package com.coding.district.service;

import com.coding.district.service.dto.GeoCoordinateDTO;
import com.coding.district.util.EndPointParams;
import com.coding.district.domain.District;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class ApiServices {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final Logger logger = LoggerFactory.getLogger(ScheduledJobs.class);

    private final EndPointParams params;

    public ApiServices(EndPointParams params) {
        this.params = params;
    }

    public LinkedHashMap<String, String> callEndPoint(String districtNameValid) {

        RestTemplate restTemplate = new RestTemplate();
        String baseUrl = params.url +
                "/search?q=" + districtNameValid +
                "&format=" + params.format +
                "&countrycodes=" + params.countrycodes +
                "&limit=" + params.limit;

        URI uri = null;
        try {
            uri = new URI(baseUrl);
        } catch (URISyntaxException e) {
            logger.info("Url : " + baseUrl + " is not a Valid Url ", dateTimeFormatter.format(LocalDateTime.now()));
            e.printStackTrace();
        }

        ResponseEntity<List<Object>> responseEntity =
                restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<Object>>() {
                });
        LinkedHashMap<String, String> responseBodyLinkedHashMap = (LinkedHashMap<String, String>) responseEntity.getBody().get(0);
        return responseBodyLinkedHashMap;
    }

    public GeoCoordinateDTO createGeoCoordinate(LinkedHashMap<String, String> responseBody) {
        GeoCoordinateDTO geoCoordinateDTO = new GeoCoordinateDTO();

        String lonStr = responseBody.get("lon");
        String latStr = responseBody.get("lat");

        Double lon = null;
        Double lat = null;
        try {
            lon = Double.valueOf(lonStr);
            lat = Double.valueOf(latStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        geoCoordinateDTO.setLon(lon);
        geoCoordinateDTO.setLat(lat);
        return geoCoordinateDTO;
    }

    public String makeNameValid(District district) {
        return Arrays.stream(district.getName().split(" ")).filter(word -> word.length() > 3).findFirst().get();
    }
}
