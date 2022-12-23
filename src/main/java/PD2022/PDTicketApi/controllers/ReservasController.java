package PD2022.PDTicketApi.controllers;

import PD2022.PDTicketApi.model.Reserva;
import PD2022.PDTicketApi.payload.ReservaDto;
import PD2022.PDTicketApi.services.ReservasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("reservas")
public class ReservasController {

    @Autowired
    ReservasService reservasService;

    @GetMapping("/Details/Pagas")
    public ResponseEntity<List<ReservaDto>> reservasPagasDetails(Principal principal){
        List<Reserva> reservas = reservasService.getReservas(principal.getName(), 1);
        List<ReservaDto> dto = reservas.stream().map(ReservaDto::mapToDto).toList();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/Details/NaoPagas")
    public ResponseEntity<List<ReservaDto>> reservasNaoPagasDetails(Principal principal){
        List<Reserva> reservas = reservasService.getReservas(principal.getName(), 0);
        List<ReservaDto> dto = reservas.stream().map(ReservaDto::mapToDto).toList();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
