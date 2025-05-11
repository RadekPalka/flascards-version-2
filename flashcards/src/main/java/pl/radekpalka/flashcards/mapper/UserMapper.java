package pl.radekpalka.flashcards.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.radekpalka.flashcards.dto.user.RegisterRequestDto;
import pl.radekpalka.flashcards.dto.user.UserResponseDto;
import pl.radekpalka.flashcards.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(RegisterRequestDto dto);

    UserResponseDto toUserResponseDto(User user);
}
