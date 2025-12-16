package com.learning.service;

import com.learning.model.User;

public interface UserService {

    void save(User user);

    User getById(String id);

    User getByEmail(String email);
}
