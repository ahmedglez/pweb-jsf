package cu.edu.cujae.pwebjsf.data.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;

public class DateController {

    public static LocalDate getActualDate(){ return LocalDate.now(); }

    public static int extensionDate(LocalDate initial_date, LocalDate final_date) {
        return Math.toIntExact(ChronoUnit.DAYS.between(initial_date, final_date));
    }

    public static LocalDate futureDate(LocalDate date, int day){return date.plusDays(day);}
}
