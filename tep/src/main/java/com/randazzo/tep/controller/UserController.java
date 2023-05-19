package com.randazzo.tep.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.randazzo.tep.model.EClass;
import com.randazzo.tep.model.ERole;
import com.randazzo.tep.model.ESection;
import com.randazzo.tep.model.User;
import com.randazzo.tep.payload.LoginBody;
import com.randazzo.tep.payload.RegisterBody;
import com.randazzo.tep.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserController(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginBody loginRequest) {
        try {
            Optional<User> user = userRepository.findByUsername(loginRequest.getUsername());
            if(user.isPresent()) {
                User userData = user.get();
                Boolean match = passwordEncoder.matches(loginRequest.getPassword(), userData.getPassword());
                if(!match) {
                    return ResponseEntity.status(401).build();
                }
                return ResponseEntity.ok(userData);
            } else {
                return ResponseEntity.status(401).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
	}

    @PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterBody registerRequest) {
        try {
            if (userRepository.existsByUsername(registerRequest.getUsername())) {
                return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
            }

            if (userRepository.existsByEmail(registerRequest.getEmail())) {
                return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already in use!");
            }

            String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
            ERole userRole;
            EClass userClass;
            ESection userSection;

            switch(registerRequest.getRole()) {
                case "studente":
                    userRole = ERole.ROLE_STUDENTE;
                    break;
                case "professore":
                    userRole = ERole.ROLE_PROFESSORE;
                    break;
                case "vicepreside":
                    userRole = ERole.ROLE_VICEPRESIDE;
                    break;
                default:
                    return ResponseEntity.badRequest().body("Error: invalid user role!");
            }

            switch(registerRequest.getClasse()) {
                case "i":
                    userClass = EClass.CLASS_I;
                    break;
                case "ii":
                    userClass = EClass.CLASS_II;
                    break;
                case "iii":
                userClass = EClass.CLASS_III;
                    break;
                case "iv":
                userClass = EClass.CLASS_IV;
                    break;
                case "v":
                userClass = EClass.CLASS_V;
                    break;
                default:
                    return ResponseEntity.badRequest().body("Error: invalid user class!");
            }

            switch(registerRequest.getSection()) {
                case "a":
                    userSection = ESection.SECTION_A;
                    break;
                case "b":
                    userSection = ESection.SECTION_B;
                    break;
                case "c":
                    userSection = ESection.SECTION_C;
                    break;
                case "d":
                    userSection = ESection.SECTION_D;
                    break;
                case "e":
                    userSection = ESection.SECTION_E;
                    break;
                case "f":
                    userSection = ESection.SECTION_F;
                    break;
                case "g":
                    userSection = ESection.SECTION_G;
                    break;
                case "h":
                    userSection = ESection.SECTION_H;
                    break;
                default:
                    return ResponseEntity.badRequest().body("Error: invalid user section!");
            }

            User user = new User(
                null,
                registerRequest.getUsername(), 
                registerRequest.getEmail(),
                encodedPassword,
                userRole,
                userClass,
                userSection
            );
    
            userRepository.save(user);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
	}
}