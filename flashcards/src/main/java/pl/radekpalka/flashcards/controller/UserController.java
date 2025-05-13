package pl.radekpalka.flashcards.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.radekpalka.flashcards.dto.user.RegisterRequestDto;
import pl.radekpalka.flashcards.dto.user.UserResponseDto;
import pl.radekpalka.flashcards.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> register(@RequestBody @Valid RegisterRequestDto dto) {
        UserResponseDto response = userService.register(dto);
        return ResponseEntity.ok(response);
    }
}
