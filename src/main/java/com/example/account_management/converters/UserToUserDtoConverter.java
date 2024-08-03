package com.example.account_management.converters;

import com.example.account_management.dto.UserDto;
import com.example.account_management.entities.User;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface UserToUserDtoConverter extends Converter<User, UserDto> {
    @Override
    UserDto convert(@NonNull final User source);
}
