package com.example.nutrisend.platform.user.domain.services;


import com.example.nutrisend.platform.user.domain.model.aggregates.Users;

import java.util.List;

public interface UserQueryService {
    List<Users> getAllUsers();
}
