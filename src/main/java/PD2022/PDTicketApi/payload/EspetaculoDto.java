package PD2022.PDTicketApi.payload;

import PD2022.PDTicketApi.model.Espetaculo;
import PD2022.PDTicketApi.model.Lugar;
import PD2022.PDTicketApi.model.Reserva;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;
@Data
public class EspetaculoDto {

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


    List<LugarDto> lugares = new ArrayList<>();

    List<ReservaDto> reservas = new ArrayList<>();


    public static EspetaculoDto mapToDto(Espetaculo espetaculo, Boolean details){
        EspetaculoDto e = new EspetaculoDto();
        e.classificacao_etaria = espetaculo.getClassificacao_etaria();
        e.descricao = espetaculo.getDescricao();
        e.duracao = espetaculo.getDuracao();
        e.local = espetaculo.getLocal();
        e.localidade = espetaculo.getLocalidade();
        e.data_hora = espetaculo.getData_hora();
        e.id = espetaculo.getId();
        e.pais = espetaculo.getPais();
        e.tipo = espetaculo.getTipo();
        e.visivel = espetaculo.getVisivel();
        if(details != null && details){
            e.reservas = espetaculo.getReservas().stream().map(ReservaDto::mapToDto).toList();
            e.lugares = espetaculo.getLugares().stream().map(LugarDto::mapToDto).toList();
        }
        return e;
    }

}
