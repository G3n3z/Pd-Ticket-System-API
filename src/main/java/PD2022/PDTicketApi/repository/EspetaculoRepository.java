package PD2022.PDTicketApi.repository;

import PD2022.PDTicketApi.model.Espetaculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EspetaculoRepository extends JpaRepository<Espetaculo, Integer> {

    @Query("select e from espetaculo e where e.data_hora >= ?1 and e.data_hora <= ?2")
    List<Espetaculo> findEspetaculosByData_horaAfterAndData_horaBefore(String dataInicio, String dataFim);

    @Query("select e from espetaculo e where e.data_hora >= ?1")
    List<Espetaculo> findEspetaculosByData_horaAfter(String dataInicio);

    @Query("select e from espetaculo e where e.data_hora <= ?1")
    List<Espetaculo> findEspetaculosByData_horaBefore(String dataFim);

}
