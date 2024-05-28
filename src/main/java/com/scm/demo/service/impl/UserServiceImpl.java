package com.scm.demo.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.scm.demo.Entities.Providers;
import com.scm.demo.Entities.User;
import com.scm.demo.exception.ResourceNotFoundException;
import com.scm.demo.repo.UserRepo;
import com.scm.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    //logger
    private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    //Default profile picture
    @Value("${DefaultProfilePicture}")
    private String DefaultProfilePicture;

    //repo autowired
    @Autowired
    private UserRepo userRepo;


    @Override
    public User saveUser(User user) {
        //encrypt password
        //user.setPassword(user.getPassword());

        //set default profile picture
        user.setProfilePicLink(DefaultProfilePicture);
        user.setProvider(Providers.SELF);
        User saveUser = userRepo.save(user);
        return saveUser;
    }

    @Override
    public User getUserById(Integer id) {
        User user = userRepo.findById(id).
            orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
        return user;
    }


    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users;
    }

    @Override
    public Boolean userExists(Integer id) {
        Boolean userCheck = userRepo.existsById(id);
        return userCheck;
    }

    @Override
    public Boolean userExists(String email) {
        Boolean userCheck = userRepo.existsByEmail(email);
        return userCheck;
    }

    @Override
    public User updateUser(User user) {
        User usertoUpdate = userRepo.findById(user.getId()).orElseThrow(() -> new ResourceNotFoundException("User", "id", user.getId()));
        usertoUpdate.setName(user.getName());
        usertoUpdate.setEmail(user.getEmail());
        usertoUpdate.setPassword(user.getPassword());
        usertoUpdate.setAbout(user.getAbout());
        usertoUpdate.setProfilePicLink(user.getProfilePicLink());
        usertoUpdate.setPhoneNumber(user.getPhoneNumber());
        usertoUpdate.setEnabled(user.isEnabled());
        usertoUpdate.setEmailVarified(user.isEmailVarified());
        usertoUpdate.setPhoneVarified(user.isPhoneVarified());
        usertoUpdate.setProvider(user.getProvider());
        usertoUpdate.setProviderUserId(user.getProviderUserId());

        User updatedUser = userRepo.save(usertoUpdate);
        return updatedUser;
    }


    @Override
    public void deleteUser(Integer id) {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        userRepo.delete(user);
    }










    

}
