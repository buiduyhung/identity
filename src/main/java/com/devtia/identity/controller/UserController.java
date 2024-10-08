package com.devtia.identity.controller;

import com.devtia.identity.dto.request.ApiResponse;
import com.devtia.identity.dto.request.UserCreationRequest;
import com.devtia.identity.dto.request.UserUpdateRequest;
import com.devtia.identity.dto.response.UserResponse;
import com.devtia.identity.entity.User;
import com.devtia.identity.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request){

        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createRequest(request));

        return apiResponse;
    }

    @GetMapping
    List<User> getUsers(){
        return userService.getAllUsers();
    }

    //@GetMapping("/{userId}")
    //User getUser(@PathVariable("userId") String userId){
    //    return  userService.getUser(userId);
    //}

    @GetMapping("/{userId}")
    UserResponse getUser(@PathVariable("userId") String userId){
        return  userService.getUser(userId);
    }

    //@PutMapping("/{userId}")
    //User updateUser(@PathVariable("userId") String userId, @RequestBody UserUpdateRequest request){
    //    return userService.updateRequest(userId, request);
    //}

    @PutMapping("/{userId}")
    UserResponse updateUser(@PathVariable("userId") String userId, @RequestBody UserUpdateRequest request){
        return userService.updateRequest(userId, request);
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable("userId") String userId){
        userService.deleteUser(userId);
        return "User deleted";
    }
}
