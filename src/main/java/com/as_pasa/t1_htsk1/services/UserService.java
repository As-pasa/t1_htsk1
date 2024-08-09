package com.as_pasa.t1_htsk1.services;


import com.as_pasa.t1_htsk1.annotations.IgnoreLogging;
import com.as_pasa.t1_htsk1.exceptions.ApplicationException;
import com.as_pasa.t1_htsk1.models.User;
import com.as_pasa.t1_htsk1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository repo){
        this.userRepository=repo;
    }
    public User persistUser(User user){
        return userRepository.save(user);
    }
    public User findById(Long id)  {
        return userRepository.findById(id).orElseThrow(()->new ApplicationException("User Not Found"));
    }
    public User modifyUser(Long id, User details){
        User user = findById(id);
        user.setState(details);
        return userRepository.save(user);
    }
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
    public List<User> listUsers(){
        return userRepository.findAll();
    }

    @IgnoreLogging
    public User initUser(String name, String email){
        return new User(name,email,new ArrayList<>());
    }
}
