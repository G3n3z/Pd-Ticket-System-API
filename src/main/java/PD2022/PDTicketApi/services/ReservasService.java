package PD2022.PDTicketApi.services;

import PD2022.PDTicketApi.exception.ApiException;
import PD2022.PDTicketApi.model.Reserva;
import PD2022.PDTicketApi.model.User;
import PD2022.PDTicketApi.repository.ReservasRepository;
import PD2022.PDTicketApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservasService {


    @Autowired
    ReservasRepository reservasRepository;

    @Autowired
    UserRepository userRepository;
    public List<Reserva> getReservas(String username, int pago) {

        return reservasRepository.findAllByUtilizadorUsernameAndPago(username, pago);
    }
}
