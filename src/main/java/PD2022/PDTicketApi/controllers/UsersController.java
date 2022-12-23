package PD2022.PDTicketApi.controllers;

import PD2022.PDTicketApi.constants.Constants;
import PD2022.PDTicketApi.model.User;
import PD2022.PDTicketApi.payload.NewUser;
import PD2022.PDTicketApi.payload.UserDto;
import PD2022.PDTicketApi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UsersController {

    @Autowired
    UserService userService;

//    @PreAuthorize("hasRole('" + Constants.ADMIN_ROLE_NAME + "')")
//    @Secured(Constants.ADMIN_ROLE_NAME)
    @GetMapping()
    public ResponseEntity<List<UserDto>> getUsers(Authentication authentication){
        List<User> users = userService.getUsers();
        List<UserDto> response = users.stream().map(UserDto::mapToDto).toList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UserDto> insertUser(@RequestBody NewUser userDto){

        UserDto response = userService.insertUser(userDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable(name = "id")Integer id){
        UserDto response = userService.deleteUser(id);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
