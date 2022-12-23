package PD2022.PDTicketApi.payload;

import PD2022.PDTicketApi.model.User;
import lombok.Data;

@Data
public class NewUser {

    String username;
    String nome;
    String password;
    int administrador;


    public static User mapToEntity(NewUser userDto) {
        User user = new User();
        user.setAdministrador(userDto.getAdministrador());
        user.setNome(userDto.nome);
        user.setPassword(userDto.password);
        user.setAutenticado(0);
        user.setUsername(userDto.username);
        return user;
    }

}
