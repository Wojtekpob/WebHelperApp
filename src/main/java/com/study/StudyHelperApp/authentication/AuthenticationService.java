package com.study.StudyHelperApp.authentication;


import com.study.StudyHelperApp.config.JwtService;
import com.study.StudyHelperApp.exceptions.UsernameAlreadyExistsException;
import com.study.StudyHelperApp.user.Role;
import com.study.StudyHelperApp.user.User;
import com.study.StudyHelperApp.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class AuthenticationService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request){
        User user = User.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .username(request.getUsername())
                .roles(new ArrayList<Role>() {{add(Role.STUDENT);};})
                .password(new BCryptPasswordEncoder().encode(request.getPassword()))
                .build();
        userRepository.findByUsername(user.getUsername())
                        .ifPresent(foundUser->{
                            throw new UsernameAlreadyExistsException("User with username: "+foundUser.getUsername()+" already exists.");
                        });
        userRepository.save(user);
        String jwtToken = jwtService.GenerateJwtToken(new HashMap<String, Object>(), user);
        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String jwtToken = jwtService.GenerateJwtToken(new HashMap<String,Object>(),user);
        return new AuthenticationResponse(jwtToken);
    }

}
