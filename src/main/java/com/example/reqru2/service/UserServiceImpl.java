package com.example.reqru2.service;

import com.example.reqru2.model.User;
import com.example.reqru2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
//    @Override
//    public Long findUserByName(String name){
//       User user = userRepository.findUserByUsername(name);
//    Long id =    user.getId();
//        System.out.println(id);
//        return id;
//    }

        @Override
    public Long getCurrentlyUserId()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String name = authentication.getPrincipal().toString();

               User user =   findByUsername(name);


        return   user.getId();
    }

    @Override
    public User findByUsername(String username){
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user){
        return userRepository.save(user);
    }
}
