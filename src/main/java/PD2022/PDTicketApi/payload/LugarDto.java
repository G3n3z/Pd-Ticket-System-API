package PD2022.PDTicketApi.payload;

import PD2022.PDTicketApi.model.Lugar;
import lombok.Data;

@Data
public class LugarDto {
    int id;
    String fila;
    String assento;
    double preco;

    public static LugarDto mapToDto(Lugar lugar) {
        LugarDto l = new LugarDto();
        l.assento = lugar.getAssento();
        l.fila = lugar.getFila();
        l.id = lugar.getId();
        l.preco = lugar.getPreco();
        return l;
    }
}
