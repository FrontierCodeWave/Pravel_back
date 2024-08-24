package com.tour.prevel.auth.mapper;

import com.tour.prevel.auth.domain.User;
import com.tour.prevel.auth.dto.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    UserResponse toUserResponse(User user);
}
