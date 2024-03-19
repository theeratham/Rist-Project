package com.example.translator.controller;

import com.example.translator.entity.request.AuthRequest;
import com.example.translator.entity.request.UserRequest;
import com.example.translator.entity.response.DataResponse;
import com.example.translator.repository.UserRepository;
import com.example.translator.service.JwtService;
import com.example.translator.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<DataResponse> authenticateUser(@RequestBody AuthRequest authRequest) {
        DataResponse response = new DataResponse();
        Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()){
            response.setMessage("Welcome");
            return ResponseEntity.ok().body(response);
        } else {
            response.setMessage("Invalid User Request");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<DataResponse> registerUser(@RequestBody UserRequest request) {
        DataResponse response = new DataResponse();
        if (!userInfoService.isInputNull(request)){
            if (userRepository.existsByEmail(request.getEmail())){
                response.setMessage("Email Already Existed");
                return ResponseEntity.badRequest().body(response);
            }
            if (userRepository.existsByUsername(request.getUsername())){
                response.setMessage("Username Already Existed");
                return ResponseEntity.badRequest().body(response);
            }
            else {
                userInfoService.addUser(request);
                response.setMessage("User Added Successfully!!");
                return ResponseEntity.ok().body(response);
            }
        }
        response.setMessage("Input Field Cannot Be Empty");
        return ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<DataResponse> registerAdminUser(@RequestBody UserRequest request) {
        DataResponse response = new DataResponse();
        if (!userInfoService.isInputNull(request)){
            if (userRepository.existsByEmail(request.getEmail())){
                response.setMessage("Email Already Existed");
                return ResponseEntity.badRequest().body(response);
            }
            if (userRepository.existsByUsername(request.getUsername())){
                response.setMessage("Username Already Existed");
                return ResponseEntity.badRequest().body(response);
            }
            else {
                userInfoService.addUser2(request);
                response.setMessage("User Added Successfully!!");
                return ResponseEntity.ok().body(response);
            }
        }
        response.setMessage("Input Field Cannot Be Empty");
        return ResponseEntity.badRequest().body(response);
    }

}
