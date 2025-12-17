package com.learning.service.impl;

import com.learning.model.User;
import com.learning.repository.UserRepository;
import com.learning.service.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        validate(user);
        user.setId(generateUserId());
        userRepository.save(user);
    }

    @Override
    public User getById(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        return optionalUser.get();
    }

    @Override
    public User getByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        return optionalUser.get();
    }

    private void validate(User user) {
        if(ObjectUtils.isEmpty(user)) {
            throw new IllegalArgumentException("User must not be empty");
        }

        if(StringUtils.isEmpty(user.getName())) {
            throw new IllegalArgumentException("User name must not be empty");
        }

        if(StringUtils.isEmpty(user.getEmail())) {
            throw new IllegalArgumentException("User email must not be empty");
        }

        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        if(optionalUser.isPresent()) {
            throw new IllegalArgumentException("User email already exists");
        }
    }

    private String generateUserId(){
        int randomNum = ThreadLocalRandom.current().nextInt(1000, 9999);
        return "U-" + randomNum;
    }
}
