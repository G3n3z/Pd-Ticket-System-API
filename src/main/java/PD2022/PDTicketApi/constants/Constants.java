package PD2022.PDTicketApi.constants;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Constants {

    public static final String ADMIN_ROLE_NAME = "ADMIN";
    public static SimpleGrantedAuthority ADMIN = new SimpleGrantedAuthority(ADMIN_ROLE_NAME);
    public static SimpleGrantedAuthority USER = new SimpleGrantedAuthority("USER");

    public static Date stringToDate(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.parse(date);
    }
    public static String dateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(date);
    }
}
