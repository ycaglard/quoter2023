package com.quoter.quoter.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

import com.quoter.quoter.dto.LoginDTO;
import com.quoter.quoter.dto.SignUpDto;
import com.quoter.quoter.model.Role;
import com.quoter.quoter.model.User;
import com.quoter.quoter.repository.RoleRepository;
import com.quoter.quoter.repository.UserRepository;
import com.quoter.quoter.security.EmailValidator;
import com.quoter.quoter.security.token.ConfirmationToken;
import com.quoter.quoter.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class HomeController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailValidator emailValidator;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDTO loginDto) {

        return new ResponseEntity<>("User login successfully!...", HttpStatus.OK);
    }


    @PostMapping("/signup")
    public String registerUser(@RequestBody SignUpDto signUpDto){
        // checking for username exists in a database
        boolean isValidEmail = emailValidator.test(signUpDto.getEmail());
        if(!isValidEmail)
            return "invalid email";
        if(userRepository.existsByUsername(signUpDto.getUsername())){
            return "username exists";
        }
        // checking for email exists in a database
        if(userRepository.existsByemail(signUpDto.getEmail())){
            return "email exists";
        }
        // creating user object
        User user = new User();
        user.setName(signUpDto.getName());
        user.setUserName(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        Role roles = roleRepository.findByName("ROLE_ADMIN");
        user.setRoles(Collections.singleton(roles));
        userRepository.save(user);
        String tokenBody = UUID.randomUUID().toString();
        ConfirmationToken token  = new ConfirmationToken(tokenBody, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15),user);
        tokenService.saveToken(token);
        return tokenBody;
    }

    @PostMapping("/confirm")
    public String registerUser(@RequestBody String token){
        return tokenService.confirmToken(token);
    }
    @GetMapping("/user")
    public User getUser(@RequestParam String username){ return userRepository.findByUsername(username);}
}