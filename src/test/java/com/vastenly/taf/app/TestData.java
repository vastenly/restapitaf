package com.vastenly.taf.app;

import com.vastenly.taf.util.ObjectMapperConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestData {

    public static ObjectMapper getObjectMapper() {
        ObjectMapperConfiguration mapperConfig = new ObjectMapperConfiguration();
        return mapperConfig.serializingObjectMapper();
    }

}
