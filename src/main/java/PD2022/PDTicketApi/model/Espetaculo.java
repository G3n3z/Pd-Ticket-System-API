package PD2022.PDTicketApi.model;



import PD2022.PDTicketApi.constants.Constants;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.*;

@Getter
@Setter
@Entity(name = "espetaculo")
public class Espetaculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String descricao;
    String tipo;
    String data_hora;

    int duracao;
    String local;
    String localidade;
    String pais;
    String classificacao_etaria;
    int visivel;
    //@ElementCollection
    @OneToMany(mappedBy = "espetaculo")
    List<Lugar> lugares = new ArrayList<>();

    @OneToMany(mappedBy = "espetaculo")
    List<Reserva> reservas = new ArrayList<>();

    public Espetaculo() {
        //lugares = new HashSet<>();
    }

}
