package stemplate.sucurity_project.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import stemplate.sucurity_project.dto.JwtRequest;
import stemplate.sucurity_project.dto.RegistrationUserDto;
import stemplate.sucurity_project.services.AuthService;
import stemplate.sucurity_project.services.UserService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final UserService userService;

    private final AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest jwtRequestAuth) {
        log.info("Запрос авторизации для " + jwtRequestAuth.getEmail());
        return authService.createAuthToken(jwtRequestAuth);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        log.info("запрос регистарции для пользователя " + registrationUserDto.getEmail());
        return userService.createNewUser(registrationUserDto);
    }
}
