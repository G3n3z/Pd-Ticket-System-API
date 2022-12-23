package PD2022.PDTicketApi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorDetails {
    String message;
    Date timestamp;
}
