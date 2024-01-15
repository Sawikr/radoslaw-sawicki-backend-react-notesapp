package com.radoslawsawicki.backendreactnotesapp.security.service;

import com.radoslawsawicki.backendreactnotesapp.security.domain.Role;
import com.radoslawsawicki.backendreactnotesapp.security.domain.User;
import com.radoslawsawicki.backendreactnotesapp.security.dto.LoginDto;
import com.radoslawsawicki.backendreactnotesapp.security.dto.RegisterDto;
import com.radoslawsawicki.backendreactnotesapp.security.exception.SecurityException;
import com.radoslawsawicki.backendreactnotesapp.security.repository.RoleRepository;
import com.radoslawsawicki.backendreactnotesapp.security.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;
import java.util.*;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    @Override
    public String register(RegisterDto registerDto) {

        if (userRepository.existsByUsername(registerDto.getUsername())){
            throw new SecurityException(HttpStatus.BAD_REQUEST, "Username already exists!");
        }

        if (userRepository.existsByEmail(registerDto.getEmail())){
            throw new SecurityException(HttpStatus.BAD_REQUEST, "Email is already exists!");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER");
        roles.add(userRole);

        user.setRoles(roles);
        userRepository.save(user);

        return "User registered successfully!";
    }

    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "User logged-in successfully!";
    }

    @Override
    public String updatePasswordByFields(String emailName, Map<String, Object> fields) {
        Optional<User> existingNote = userRepository.findByEmail(emailName);
        System.out.println("ExistingNote is: " + existingNote);
        if (existingNote.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(User.class, key);
                Objects.requireNonNull(field).setAccessible(true);
                if (key.contains("password")) {
                    ReflectionUtils.setField(field, existingNote.get(), passwordEncoder.encode((CharSequence) value));
                }
            });
            userRepository.save(existingNote.get());
        }

        return "Reset password successfully!";
    }
}
