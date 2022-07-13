package ru.kmetha.externalapi.mapper;

import org.mapstruct.Mapper;
import ru.kmetha.externalapi.entity.security.AccountUser;
import ru.kmetha.gbapimay.security.UserDto;

@Mapper
public interface UserMapper {

    AccountUser toAccountUser(UserDto userDto);

    UserDto toUserDto(AccountUser accountUser);
}
