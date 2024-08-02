package com.example.account_management.converters;

import com.example.account_management.dto.AccountDto;
import com.example.account_management.entities.Account;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface AccountToAccountDtoConverter extends Converter<Account, AccountDto> {
    @Override
    AccountDto convert(@NonNull final Account source);
}
