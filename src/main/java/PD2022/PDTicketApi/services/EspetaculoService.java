package PD2022.PDTicketApi.services;

import PD2022.PDTicketApi.constants.Constants;
import PD2022.PDTicketApi.model.Espetaculo;
import PD2022.PDTicketApi.repository.EspetaculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EspetaculoService {

    @Autowired
    EspetaculoRepository espetaculoRepostory;


    public List<Espetaculo> getEspecatulosBeforeAndAfter(Date dataInicio, Date dataFim) {
        String data1 = Constants.dateToString(dataInicio);
        String data2 = Constants.dateToString(dataFim);
        return espetaculoRepostory.findEspetaculosByData_horaAfterAndData_horaBefore(data1, data2);
    }

    public List<Espetaculo> getEspecatulosAfter(Date dataInicio) {
        String data1 = Constants.dateToString(dataInicio);
        return espetaculoRepostory.findEspetaculosByData_horaAfter(data1);
    }

    public List<Espetaculo> getEspecatulosBefore(Date dataFim) {
        String data1 = Constants.dateToString(dataFim);
        return espetaculoRepostory.findEspetaculosByData_horaBefore(data1);
    }

    public List<Espetaculo> getEspetaculos() {
        return espetaculoRepostory.findAll();
    }
}
