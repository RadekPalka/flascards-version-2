package pl.radekpalka.flashcards.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import pl.radekpalka.flashcards.dto.user.LoginRequestDto;
import pl.radekpalka.flashcards.dto.user.RegisterRequestDto;
import pl.radekpalka.flashcards.dto.user.UserResponseDto;
import pl.radekpalka.flashcards.mapper.UserMapper;
import pl.radekpalka.flashcards.model.User;
import pl.radekpalka.flashcards.repository.UserRepository;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto register(RegisterRequestDto dto){
        if (userRepository.findByLogin(dto.getLogin()).isPresent()) {
            throw new RuntimeException("Login already in use");
        }
        User user = UserMapper.toUser(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        User saved = userRepository.save(user);
        return new UserResponseDto(saved.getId(), saved.getLogin());
    }

    public UserResponseDto login(LoginRequestDto dto){
        User user = userRepository.findByLogin(dto.getLogin())
                .orElseThrow(()-> new RuntimeException("Invalid login or password"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid login or password");
        }

        return  new UserResponseDto(user.getId(), user.getLogin());


    }
}
