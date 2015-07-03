package com.noveria.absencemanagement.service.dto.mapper.maps;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;

/**
 * Created by lynseymcgregor on 03/07/2015.
 */
public abstract class DTOMapper<Source,Destination> {

    protected abstract void registerMapping();

    protected abstract MapperFactory getMapperFactory();

    protected abstract Class<Source> getSourceClass();

    protected abstract Class<Destination> getDestinationClass();

    public Destination map(Source dto) {
        BoundMapperFacade<Source, Destination> boundMapper =
                getMapperFactory().getMapperFacade(
                        getSourceClass(), getDestinationClass());

        return boundMapper.map(dto);
    }


}
