package stemplate.sucurity_project.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class RegistrationUserDto {
    private String email;
    private String password;
    private String confirmPassword;

}
