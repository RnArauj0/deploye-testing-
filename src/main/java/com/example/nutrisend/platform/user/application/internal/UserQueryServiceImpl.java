package com.example.nutrisend.platform.user.application.internal;


import com.example.nutrisend.platform.user.domain.model.aggregates.Users;
import com.example.nutrisend.platform.user.domain.services.UserQueryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    @Override
    public List<Users> getAllUsers() {
        // Aquí puedes cargar los usuarios desde una base de datos o desde db.json.
        // Para propósitos de demostración, estamos utilizando una lista estática.
        return Arrays.asList(
                new Users(1L, "Camila", "Espinoza", "example@gmail.com", "12345", "free"),
                new Users(2L, "Renzo", "Araujo", "example69@gmail.com", "123456789", "premium"),
                new Users(3L, "Isabella", "Romeroz", "example59@gmail.com", "12345678", "free"),
                new Users(4L, "Patrick", "Espinoza", "hola@gmail.com", "12345", "premium")
        );
    }
}