package PD2022.PDTicketApi.model;




import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Entity(name = "utilizador")
@Getter
@Setter
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String username;
    String nome;
    String password;
    int autenticado;
    int administrador;

    @OneToMany(mappedBy = "utilizador")
    List<Reserva> reservas;

}
