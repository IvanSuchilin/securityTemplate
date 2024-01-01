package stemplate.sucurity_project.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Getter
@Setter
public class JwtResponse {
    private String token;

    public JwtResponse(String token) {
        this.token = token;
    }
}
