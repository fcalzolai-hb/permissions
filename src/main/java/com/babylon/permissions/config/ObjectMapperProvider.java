package com.babylon.permissions.config;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
//import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javax.ws.rs.ext.ContextResolver;

//import com.babylonhealth.locusauthoring.infrastructure.serde.EnumDeserializer;
//import com.babylonhealth.locusauthoring.model.type.Genders;
//import com.babylonhealth.locusauthoring.model.type.Persons;
//
//import static com.babylonhealth.locusauthoring.LocusAuthoringSvc.zoneIdModule;

public class ObjectMapperProvider implements ContextResolver<ObjectMapper> {
  private final ObjectMapper mapper = new ObjectMapper();

  public ObjectMapperProvider() {
    this.mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    this.mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    this.mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
//    this.mapper.registerModule(new JavaTimeModule());
    this.mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
//    this.mapper.registerModule(new Jdk8Module());
    this.mapper.registerModule(new Hibernate5Module());
    this.mapper.setSerializationInclusion(Include.NON_ABSENT);
    SimpleModule module = new SimpleModule();
    mapper.registerModule(module);
  }

  public ObjectMapper getContext(Class<?> type) {
    return this.mapper;
  }

  public ObjectMapper getMapper() {
    return this.mapper;
  }
}
