package cu.edu.cujae.pweb.utils;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateController {

    public static Date getDate(LocalDate localDate){ return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());}

    public static LocalDate getLocalDate(Date date){return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();}

    public static int extensionDate(LocalDate initial_date, LocalDate final_date) {
        return Math.toIntExact(ChronoUnit.DAYS.between(initial_date, final_date));
    }

    public static LocalDate futureDate(LocalDate date, int day){return date.plusDays(day);}
}
