package cu.edu.cujae.pweb.utils;

import javafx.util.converter.LocalDateStringConverter;

import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DateController {

    public static Date getDate(LocalDate localDate){ return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());}

    public static LocalDate getLocalDate(Date date){return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();}

    public static int extensionDate(LocalDate initial_date, LocalDate final_date) {
        return Math.toIntExact(ChronoUnit.DAYS.between(initial_date, final_date));

    }

    public static LocalDate getMostRecent(List<LocalDate> dates){
        LocalDate mostRecent = LocalDate.now();
        int minExtension = 1000000;
        for(LocalDate d : dates){
            int extension = extensionDate(d,LocalDate.now());
            if(extension<minExtension){
                minExtension = extension;
                mostRecent = d;
            }
        }
        return mostRecent;
    }

    public static LocalDate getActualDate(){ return LocalDate.now(); }

    public static LocalDate getLocalDateByString(String date) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
        return LocalDate.parse(date, formatter);
    }

    public static LocalDate futureDate(LocalDate date, int day){return date.plusDays(day);}
}
