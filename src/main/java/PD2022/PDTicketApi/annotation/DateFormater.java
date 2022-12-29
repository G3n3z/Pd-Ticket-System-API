package PD2022.PDTicketApi.annotation;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DateFormater implements Formatter<Date> {
    private final List<String> formatsToTry;

    public DateFormater(List<String> formatsToTry) {
        this.formatsToTry = formatsToTry;
    }

    @Override
    public Date parse(String text, Locale locale) throws ParseException {

        for (String format : formatsToTry) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat(format);
                Calendar cal = Calendar.getInstance();
                Date date = dateFormat.parse(text);
                if(text.length() == 10){
                    cal.setTime(date);
                    cal.set(Calendar.HOUR, 23);
                    cal.set(Calendar.MINUTE, 59);
                    cal.set(Calendar.SECOND, 59);
                    date = cal.getTime();
                }
                return date;
            }catch (ParseException ignore) {
                // or log the exception
            }
        }
        throw new IllegalArgumentException("Unable to parse \"" + text
                + "\" as LocalDate using formats = " + String.join(", ", formatsToTry));
    }



    @Override
    public String print(Date object, Locale locale) {
        return object.toString();
    }
}
