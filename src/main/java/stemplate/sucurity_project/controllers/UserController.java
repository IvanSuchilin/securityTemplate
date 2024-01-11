package stemplate.sucurity_project.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    @GetMapping("/unsecured")
    public String unsecuredData() {
        log.info("вход в /unsecured");
        return "Unsecured Data";
    }

    @GetMapping("/secured")
    public String securedData() {
        log.info("вход в /secured");
        return "Secured Data";
    }

    @GetMapping("/admin")
    public String adminData() {
        log.info("вход в /admin");
        return "Admin Data";
    }

    @GetMapping("/info")
    public String userData(Principal principal) {
        log.info("вход в /info");
        return principal.getName();
    }
}
