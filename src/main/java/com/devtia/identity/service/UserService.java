package com.devtia.identity.service;

import com.devtia.identity.dto.request.UserCreationRequest;
import com.devtia.identity.dto.request.UserUpdateRequest;
import com.devtia.identity.dto.response.UserResponse;
import com.devtia.identity.entity.User;
import com.devtia.identity.exception.AppException;
import com.devtia.identity.exception.ErrorCode;
import com.devtia.identity.mapper.UserMapper;
import com.devtia.identity.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor   // same as @Autowired
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true) // same as file=private
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    public User createRequest(UserCreationRequest request) {
        //User user = new User();

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXITED);
        }
        User user = userMapper.toUser(request); //dùng mapper để lấy dữ liệu

        //user.setUsername(request.getUsername());
        //user.setPassword(request.getPassword());
        //user.setFirstName(request.getFirstName());
        //user.setLastName(request.getLastName());
        //user.setDob(request.getDob());

        //Mã hóa password
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(user);
    }

    public UserResponse updateRequest(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateUser(user, request);

        //user.setPassword(request.getPassword());
        //user.setFirstName(request.getFirstName());
        //user.setLastName(request.getLastName());
        //user.setDob(request.getDob());

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //public User getUser(String id){
    //    return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    //}

    public UserResponse getUser(String id){
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found")));
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
