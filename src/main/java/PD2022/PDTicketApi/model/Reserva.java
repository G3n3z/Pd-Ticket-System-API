package PD2022.PDTicketApi.model;

import PD2022.PDTicketApi.constants.Constants;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String data_hora;
    int pago;

    @ManyToOne
    @JoinColumn(name = "id_utilizador")
    User utilizador;


    @ManyToOne
    @JoinColumn(name = "id_espetaculo")
    Espetaculo espetaculo;

    @OneToMany()
    @JoinTable(name = "reserva_lugar",
            joinColumns = @JoinColumn(name = "id_reserva", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_lugar", referencedColumnName = "id"))
    List<Lugar> lugares = new ArrayList<>();

    public Reserva() {
    }


}
