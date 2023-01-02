package PD2022.PDTicketApi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Entity(name = "lugar")
public class Lugar implements Comparable<Lugar> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String fila;
    String assento;
    double preco;
    //int espetaculo_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "espetaculo_id")
    Espetaculo espetaculo;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "reserva_lugar",
            joinColumns = @JoinColumn(name = "id_lugar", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_reserva", referencedColumnName = "id"))
    Reserva reserva;

    //Reserva reserva;
    public Lugar() {
    }

    @Override
    public int compareTo(Lugar o) {
        return Integer.parseInt(assento.trim()) - Integer.parseInt(o.assento.trim());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lugar lugar = (Lugar) o;

        //if (getEspetaculo_id() != lugar.getEspetaculo_id()) return false;
        if (!getFila().equals(lugar.getFila())) return false;
        return getAssento().equals(lugar.getAssento());
    }

    @Override
    public int hashCode() {
        int result = getFila().hashCode();
        result = 31 * result + getAssento().hashCode();
        //result = 31 * result + getEspetaculo_id();
        return result;
    }
}
