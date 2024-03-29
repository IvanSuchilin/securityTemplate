package stemplate.sucurity_project.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class JwtRequest {
    private String email;
    private String password;
}
