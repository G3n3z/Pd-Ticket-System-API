package PD2022.PDTicketApi.services;

import PD2022.PDTicketApi.constants.Constants;
import PD2022.PDTicketApi.exception.ApiException;
import PD2022.PDTicketApi.model.User;
import PD2022.PDTicketApi.payload.NewUser;
import PD2022.PDTicketApi.payload.UserDto;
import PD2022.PDTicketApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public UserDto insertUser(NewUser userDto) {
        if (userDto.getAdministrador() < 0 || userDto.getAdministrador() > 1){
            throw new ApiException("Admnistrador deve ser 0 ou 1", HttpStatus.BAD_REQUEST);
        }
        User user = NewUser.mapToEntity(userDto);
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        if (userRepository.existsUserByUsernameOrNome(user.getUsername(), user.getNome())){
            throw new ApiException("Username ou nome já utilizados", HttpStatus.BAD_REQUEST);
        }
        try{
            userRepository.save(user);
        }catch (Exception e){
            throw new ApiException("Não foi satisfazer o seu pedido", HttpStatus.BAD_REQUEST);
        }

        return UserDto.mapToDto(user);
    }

    public UserDto deleteUser(Integer id) {
        User user = null;
        user = userRepository.findById(id).orElseThrow(() -> new ApiException("Id inexistente", HttpStatus.BAD_REQUEST));
        try {
            userRepository.delete(user);
        }catch (Exception e){
            throw new ApiException("Não foi possivel satisfazer o seu pedido", HttpStatus.BAD_REQUEST);
        }

        return UserDto.mapToDto(user);
    }
}
