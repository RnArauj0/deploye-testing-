package com.example.nutrisend.platform.user.domain.services;


import com.example.nutrisend.platform.user.domain.model.commands.CreateUserCommand;
import com.example.nutrisend.platform.user.interfaces.rest.resources.UserResource;

public interface UserCommandService {
    UserResource createUser(CreateUserCommand command);
}
