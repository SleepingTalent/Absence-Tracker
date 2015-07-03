package com.noveria.absencemanagement.service.dto.mapper.maps;

import com.noveria.absencemanagement.model.user.entities.User;
import com.noveria.absencemanagement.service.dto.mapper.factory.DTOMappingFactory;
import com.noveria.absencemanagement.service.dto.user.UserDTO;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by lynseymcgregor on 03/07/2015.
 */
@Component
public class UserMapper extends DTOMapper<UserDTO,User> {

    @Autowired
    DTOMappingFactory dtoMappingFactory;

    @PostConstruct
    public void init(){
       registerMapping();
    }

    @Override
    protected void registerMapping() {
        dtoMappingFactory.getMapperFactory().classMap(
                UserDTO.class, User.class).byDefault().register();
    }

    @Override
    protected MapperFactory getMapperFactory() {
        return dtoMappingFactory.getMapperFactory();
    }

    @Override
    protected Class<UserDTO> getSourceClass() {
        return UserDTO.class;
    }

    @Override
    protected Class<User> getDestinationClass() {
        return User.class;
    }
}
