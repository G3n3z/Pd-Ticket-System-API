package PD2022.PDTicketApi.controllers;

import PD2022.PDTicketApi.annotation.DateFormat;
import PD2022.PDTicketApi.exception.ApiException;
import PD2022.PDTicketApi.model.Espetaculo;
import PD2022.PDTicketApi.payload.EspetaculoDto;
import PD2022.PDTicketApi.services.EspetaculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("espectaculos")
public class EspetaculosController {

    @Autowired
    EspetaculoService espetaculoService;

    @GetMapping()
    public ResponseEntity<List<EspetaculoDto>> espectaculos(@RequestParam(name = "dataInicio", required = false)Date dataInicio,
                                                            @RequestParam(name = "dataFim", required = false)@DateFormat(formatsToTry = {"yyyy/MM/dd HH:mm:ss","yyyy/MM/dd"})Date dataFim){

        List<Espetaculo> espetaculos;
        List<EspetaculoDto> dto = new ArrayList<>();

        if (dataInicio != null && dataFim != null){
            if(dataInicio.compareTo(dataFim) > 0){
                throw new ApiException("Data de fim superior à data de inicio", HttpStatus.BAD_REQUEST);
            }
            espetaculos = espetaculoService.getEspecatulosBeforeAndAfter(dataInicio, dataFim);
        }
        else if(dataInicio != null){
            espetaculos = espetaculoService.getEspecatulosAfter(dataInicio);
        }else if(dataFim != null){
            espetaculos = espetaculoService.getEspecatulosBefore(dataFim);
        }else{
            espetaculos = espetaculoService.getEspetaculos();
        }
        if(espetaculos != null){
            dto = espetaculos.stream().map(EspetaculoDto::mapToDto).toList();
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }



}
