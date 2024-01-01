package stemplate.sucurity_project.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import stemplate.sucurity_project.dto.JwtRequest;
import stemplate.sucurity_project.dto.JwtResponse;
import stemplate.sucurity_project.exceptions.AuthError;
import stemplate.sucurity_project.services.UserService;
import stemplate.sucurity_project.utils.JwtTokenUtils;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest jwtRequestAuth) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequestAuth.getEmail(), jwtRequestAuth.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AuthError(HttpStatus.UNAUTHORIZED.value(), "Wrong passsword or login"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(jwtRequestAuth.getEmail());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
