package pl.radekpalka.flashcards.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.radekpalka.flashcards.dto.user.RegisterRequestDto;
import pl.radekpalka.flashcards.dto.user.UserResponseDto;
import pl.radekpalka.flashcards.mapper.UserMapper;
import pl.radekpalka.flashcards.model.User;
import pl.radekpalka.flashcards.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto register(RegisterRequestDto dto){
        if (userRepository.findByLogin(dto.getLogin()).isPresent()) {
            throw new RuntimeException("Login already in use");
        }
        User user = userMapper.toUser(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        User saved = userRepository.save(user);
        return userMapper.toUserResponseDto(saved);
    }
}
