package com.coding.district.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

// It is Using for some Params ...
@Configuration
@PropertySource("classpath:application.properties")
public class EndPointParams {

    @Value("${geo.api.endpoint.sample}")
    public String endpointSample;

    @Value("${geo.api.endpoint.url}")
    public String url;

    @Value("${geo.api.endpoint.q}")
    public String q;

    @Value("${geo.api.endpoint.format}")
    public String format;

    @Value("${geo.api.endpoint.countrycodes}")
    public String countrycodes;

    @Value("${geo.api.endpoint.limit}")
    public String limit;
}
