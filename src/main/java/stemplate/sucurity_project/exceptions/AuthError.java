package stemplate.sucurity_project.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

import java.util.Date;
@Getter
@Setter
public class AuthError {
    private int status;
    private String message;
    private Date time;

    public AuthError(int status, String message) {
        this.status = status;
        this.message = message;
        this.time = new Date();
    }
}
