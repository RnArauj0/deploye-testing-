package com.example.nutrisend.platform.user.application.internal;


import com.example.nutrisend.platform.user.domain.model.commands.CreateUserCommand;
import com.example.nutrisend.platform.user.domain.services.UserCommandService;
import com.example.nutrisend.platform.user.interfaces.rest.resources.UserResource;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private static final AtomicLong idCounter = new AtomicLong(1); // Contador para generar IDs únicos

    @Override
    public UserResource createUser(CreateUserCommand command) {
        Long userId = idCounter.getAndIncrement(); // Genera un nuevo ID
        // Aquí iría la lógica para crear un usuario en la base de datos.

        // Simulación de creación de usuario
        System.out.println("Creating user: " + command.name() + " " + command.surname());

        // Retorna el recurso del usuario creado con el ID
        return new UserResource(userId, command.name(), command.surname(), command.email());
    }
}