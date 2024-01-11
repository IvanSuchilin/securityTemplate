package stemplate.sucurity_project.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stemplate.sucurity_project.dto.RegistrationUserDto;
import stemplate.sucurity_project.dto.UserDto;
import stemplate.sucurity_project.entities.User;
import stemplate.sucurity_project.exceptions.AuthError;
import stemplate.sucurity_project.repositories.RoleRepository;
import stemplate.sucurity_project.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor (onConstructor_={@Lazy})
@Service
@Slf4j
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> findByUserEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = findByUserEmail(email).orElseThrow(()-> new UsernameNotFoundException(String.format(
                "Пользователь %s не найден", email)));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                user.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList()));
    }

    public User buildNewUser(RegistrationUserDto registrationUserDto){
        User newUser = new User();
        newUser.setEmail(registrationUserDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
        newUser.setRoles(List.of(roleRepository.findByName("ROLE_USER").get()));
         return userRepository.save(newUser);
    }

    public ResponseEntity<?> createNewUser(RegistrationUserDto registrationUserDto) {
        if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())){
            return new ResponseEntity<>(new AuthError(HttpStatus.BAD_REQUEST.value(), "Пароли не совпадают"),HttpStatus.BAD_REQUEST);
        }
        if (findByUserEmail(registrationUserDto.getEmail()).isPresent()){
            return new ResponseEntity<>(new AuthError(HttpStatus.BAD_REQUEST.value(), "Пользователь с такой почтой существует"),HttpStatus.BAD_REQUEST);
        }
        User storedUser = buildNewUser(registrationUserDto);
        return ResponseEntity.ok(new UserDto(storedUser.getId(), storedUser.getEmail()));
    }
}
