package com.example.reqru2.controller;

import com.example.reqru2.dto.UserDto;
import com.example.reqru2.jwt.JwtTokenProvider;
import com.example.reqru2.model.Role;
import com.example.reqru2.model.User;
import com.example.reqru2.service.StripleService;
import com.example.reqru2.service.UserService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController

public class UserController {

    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private UserService userService;
    @Autowired
   private AuthenticationManager authenticationManager;
    @Autowired
   private StripleService stripleService;



    @PostMapping("/api/user/registration")
    public ResponseEntity<?> register(  @RequestBody @Valid User user){

    if(userService.findByUsername(user.getUsername()) != null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        user.setRole(Role.USER);
       User user1= userService.saveUser(user);
       UserDto userDto = new UserDto();
       userDto.setName(user.getName());
       userDto.setUsername(user.getUsername());


        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

@GetMapping("/api/user/login")
public ResponseEntity<?> authenticateUser( @RequestBody User user) throws StripeException {

    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    user.getUsername(),
                    user.getPassword()
            )
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = tokenProvider.generateToken(authentication);

    return ResponseEntity.ok(jwt);

}



}
