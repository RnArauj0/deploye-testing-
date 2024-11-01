package com.example.nutrisend.platform.user.interfaces.rest;


import com.example.nutrisend.platform.user.application.internal.UserCommandServiceImpl;
import com.example.nutrisend.platform.user.domain.model.aggregates.Users;
import com.example.nutrisend.platform.user.domain.model.commands.CreateUserCommand;
import com.example.nutrisend.platform.user.domain.services.UserQueryService;
import com.example.nutrisend.platform.user.interfaces.rest.resources.CreateUserResource;
import com.example.nutrisend.platform.user.interfaces.rest.resources.UserResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/users", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "Operations related to users")
public class UserController {
    private final UserCommandServiceImpl userCommandService;
    private final UserQueryService userQueryService; // Agrega el servicio de consultas

    public UserController(UserCommandServiceImpl userCommandService, UserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService; // Inicializa el servicio de consultas
    }

    @Operation(
            summary = "Create a user",
            description = "Create a user with the provided details"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
    })
    @PostMapping
    public ResponseEntity<UserResource> createUser(@RequestBody CreateUserResource resource) {
        CreateUserCommand command = new CreateUserCommand(
                resource.name(),
                resource.surname(),
                resource.email(),
                resource.password(),
                resource.plan()
        );

        // Aquí se llamaría al servicio para crear el usuario y obtener el recurso creado
        UserResource userResource = userCommandService.createUser(command);

        return new ResponseEntity<>(userResource, CREATED);
    }

    @Operation(
            summary = "Get all users",
            description = "Retrieve a list of all users"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of users retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No users found")
    })
    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> userResources = userQueryService.getAllUsers(); // Llama al servicio para obtener todos los usuarios
        if (userResources.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retorna 404 si no hay usuarios
        }
        return ResponseEntity.ok(userResources); // Retorna la lista de usuarios con 200 OK
    }
}