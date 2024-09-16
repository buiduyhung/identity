package com.devtia.identity.mapper;

import com.devtia.identity.dto.request.UserCreationRequest;
import com.devtia.identity.dto.request.UserUpdateRequest;
import com.devtia.identity.dto.response.UserResponse;
import com.devtia.identity.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    //@Mapping(source = "firstName", target = "lastName")  -- mapping lastName theo firstName
    //@Mapping(target = "lastName", ignore=true)  -- hidden lastName

    UserResponse toUserResponse(User user);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
