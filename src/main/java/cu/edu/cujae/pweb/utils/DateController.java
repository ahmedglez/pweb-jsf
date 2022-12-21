package cu.edu.cujae.pweb.utils;

import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

public class DateController {

    public static Date getDate(LocalDate localDate){ return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());}

    public static LocalDate getLocalDate(Date date){return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();}

    public static int extensionDate(LocalDate initial_date, LocalDate final_date) {
        return Math.toIntExact(ChronoUnit.DAYS.between(initial_date, final_date));

    }

    public static LocalDate getLocalDateByString(String date) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-d", Locale.US);
        return LocalDate.parse(date, formatter);
    }

    public static LocalDate futureDate(LocalDate date, int day){return date.plusDays(day);}
}
