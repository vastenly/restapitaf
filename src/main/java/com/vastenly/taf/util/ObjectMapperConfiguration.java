package com.vastenly.taf.util;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


/**
 * Common object mapper configuration.
 * <br>Support custom date-time serializing/deserializing and security provider injecting.
 */
@NoArgsConstructor
//@RequiredArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "common")
public class ObjectMapperConfiguration {

    private class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
            return LocalDateTime.parse(parser.getValueAsString(), dateTimeFormatter);
        }
    }

    private class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
        @Override
        public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException {
            gen.writeString(value.format(dateTimeFormatter));
        }
    }

    private class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
        @Override
        public LocalDate deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
            return LocalDate.parse(parser.getValueAsString(), dateFormatter);
        }
    }

    private class LocalDateSerializer extends JsonSerializer<LocalDate> {
        @Override
        public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException {
            gen.writeString(value.format(dateFormatter));
        }
    }

    private class LocalTimeDeserializer extends JsonDeserializer<LocalTime> {
        @Override
        public LocalTime deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
            return LocalTime.parse(parser.getValueAsString(), timeFormatter);
        }
    }

    private class LocalTimeSerializer extends JsonSerializer<LocalTime> {
        @Override
        public void serialize(LocalTime value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException {
            gen.writeString(value.format(timeFormatter));
        }
    }


    @Getter
    @Setter
    private String dateFormat;

    @Getter
    @Setter
    private String timeFormat;

    @Getter
    @Setter
    private String dateTimeFormat;

    @Getter
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Getter
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    @Getter
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");


    /**
     * Configures formatters.
     */
    @PostConstruct
    public void configure() {
        if (dateFormat != null) {
            dateFormatter = DateTimeFormatter.ofPattern(dateFormat);
        }
        if (timeFormat != null) {
            timeFormatter = DateTimeFormatter.ofPattern(timeFormat);
        }
        if (dateTimeFormat != null) {
            dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeFormat);
        }
    }


    /**
     * Creates object mapper with registered date-time serializer/deserializer.
     *
     * @return object mapper
     */
    @Bean
    @Primary
    public ObjectMapper serializingObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer());
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer());
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer());
        objectMapper.registerModule(javaTimeModule);

        InjectableValues injectableValues = new InjectableValues.Std();
        objectMapper.setInjectableValues(injectableValues);

        return objectMapper;
    }

}
