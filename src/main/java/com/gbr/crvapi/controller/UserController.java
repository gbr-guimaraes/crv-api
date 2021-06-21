package com.gbr.crvapi.controller;

import com.gbr.crvapi.model.User;
import com.gbr.crvapi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User user){

        if(user.getCpf().isEmpty() || user.getEmail().isEmpty() || user.getNome().isEmpty() || user.getDataNascimento().isEmpty() || !userRepository.findById(user.getCpf()).isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados invalidos!");
        }
        User saved = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);

    }

}
