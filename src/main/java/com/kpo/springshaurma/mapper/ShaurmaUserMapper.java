package com.kpo.springshaurma.mapper;

import com.kpo.springshaurma.api.models.ShaurmaUserObject;
import com.kpo.springshaurma.model.ShaurmaUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static com.kpo.springshaurma.util.SerializationConstants.DATE_TIME_FORMAT;

@Mapper(componentModel = "spring", uses = MapperHelper.class)
public interface ShaurmaUserMapper {

    @Mapping(target = "registrationDate", source = "createdAt", dateFormat = DATE_TIME_FORMAT)
    @Mapping(target = "password", ignore = true)
    ShaurmaUserObject shaurmaUserModel2ShaurmaUserObject(ShaurmaUser shaurmaUser);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "vkId", ignore = true)
    @Mapping(target = "appleId", ignore = true)
    @Mapping(target = "googleId", ignore = true)
    @Mapping(target = "facebookId", ignore = true)
    @Mapping(target = "refreshTokens", ignore = true)
    @Mapping(target = "ban", defaultValue = "false")
    @Mapping(target = "active", defaultValue = "true")
    @Mapping(target = "role", defaultValue = "USER")
    ShaurmaUser shaurmaUserObject2ShaurmaUserModel(ShaurmaUserObject shaurmaUserObject);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "vkId", ignore = true)
    @Mapping(target = "appleId", ignore = true)
    @Mapping(target = "googleId", ignore = true)
    @Mapping(target = "facebookId", ignore = true)
    @Mapping(target = "refreshTokens", ignore = true)
    void updateShaurmaUserModelFromShaurmaUserObject(@MappingTarget ShaurmaUser shaurmaUser,
                                                     ShaurmaUserObject shaurmaUserObject);
}
