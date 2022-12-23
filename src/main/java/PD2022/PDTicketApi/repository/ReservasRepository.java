package PD2022.PDTicketApi.repository;

import PD2022.PDTicketApi.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservasRepository extends JpaRepository<Reserva, Integer> {

    List<Reserva> findAllByUtilizadorUsernameAndPago(String username, int pago);
}
