package PD2022.PDTicketApi.payload;

import PD2022.PDTicketApi.model.User;
import lombok.Data;

@Data
public class UserDto {

    int id;
    String username;
    String nome;
    int autenticado;
    int administrador;

    public static UserDto mapToDto(User user){
        UserDto dto = new UserDto();
        dto.administrador = user.getAdministrador();
        dto.id = user.getId();
        dto.autenticado = user.getAutenticado();
        dto.nome = user.getNome();
        dto.username = user.getUsername();
        return dto;
    }


}
