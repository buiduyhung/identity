package com.devtia.identity.service;

import com.devtia.identity.dto.request.AuthenticationRequest;
import com.devtia.identity.exception.AppException;
import com.devtia.identity.exception.ErrorCode;
import com.devtia.identity.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;

    public boolean authenticate(AuthenticationRequest request){
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXITED));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        return passwordEncoder.matches(request.getPassword(), user.getPassword());
    }
}
