package com.noveria.absencemanagement.service.dto.mapper.factory;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by lynseymcgregor on 03/07/2015.
 */
@Component
public class DTOMappingFactory {

    MapperFactory mapperFactory;

    @PostConstruct
    public void init(){
        mapperFactory = new DefaultMapperFactory.Builder().build();
    }

    public MapperFactory getMapperFactory() {
        return mapperFactory;
    }
}
