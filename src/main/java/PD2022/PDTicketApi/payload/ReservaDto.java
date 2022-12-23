package PD2022.PDTicketApi.payload;

import PD2022.PDTicketApi.model.Espetaculo;
import PD2022.PDTicketApi.model.Lugar;
import PD2022.PDTicketApi.model.Reserva;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReservaDto {

    int id;
    String data_hora;
    int pago;
    int id_utilizador;

    int espetaculo_id;
    List<LugarDto> lugares = new ArrayList<>();


    public static ReservaDto mapToDto(Reserva r){
        ReservaDto dto = new ReservaDto();
        dto.data_hora = r.getData_hora();
        dto.espetaculo_id = r.getEspetaculo().getId();
        dto.pago = r.getPago();
        //dto.id_utilizador = r.getId_utilizador();
        dto.lugares = r.getLugares().stream().map(LugarDto::mapToDto).toList();
        return dto;
    }

}
