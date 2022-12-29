package PD2022.PDTicketApi.controllers;

import PD2022.PDTicketApi.exception.ApiException;
import PD2022.PDTicketApi.model.Reserva;
import PD2022.PDTicketApi.payload.ReservaDto;
import PD2022.PDTicketApi.services.ReservasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("reservas")
public class ReservasController {

    @Autowired
    ReservasService reservasService;

    @GetMapping()
    public ResponseEntity<List<ReservaDto>> reservasPagasDetails(@RequestParam(name = "estado", required = true) String estado,
                                                                     Principal principal){
        List<Reserva> reservas;
        if(estado.equalsIgnoreCase("pagas")){
            reservas = reservasService.getReservas(principal.getName(), 1);
        }else if(estado.equalsIgnoreCase("naopagas")){
            reservas = reservasService.getReservas(principal.getName(), 0);
        }else{
            throw new ApiException("Deve passar como parametro o estado das reservas. \n" +
                    "O estado deve ser igual a pagas ou naopagas",HttpStatus.BAD_REQUEST);
        }
        List<ReservaDto> dto = reservas.stream().map(ReservaDto::mapToDto).toList();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
