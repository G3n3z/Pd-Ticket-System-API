package PD2022.PDTicketApi.annotation;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

public class DateFormatAnnotationFormatterFactory implements AnnotationFormatterFactory<DateFormat> {
    @Override
    public Set<Class<?>> getFieldTypes() {
        return Collections.singleton(Date.class);
    }

    @Override
    public Printer<?> getPrinter(DateFormat annotation, Class<?> fieldType) {
        return new DateFormater(Arrays.asList(annotation.formatsToTry()));
    }

    @Override
    public Parser<?> getParser(DateFormat annotation, Class<?> fieldType) {
        return new DateFormater(Arrays.asList(annotation.formatsToTry()));
    }
}
