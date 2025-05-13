package pl.radekpalka.flashcards.mapper;


import pl.radekpalka.flashcards.dto.user.RegisterRequestDto;
import pl.radekpalka.flashcards.dto.user.UserResponseDto;
import pl.radekpalka.flashcards.model.User;
public class UserMapper {

    public static User toUser(RegisterRequestDto dto) {
        User user = new User();
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        return user;
    }

    public static UserResponseDto toUserResponseDto(User user) {
        return new UserResponseDto(user.getId(), user.getLogin());
    }
}

