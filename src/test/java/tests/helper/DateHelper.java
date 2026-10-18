package tests.helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateHelper {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);

    public static String formatDate(int year, int month, int day) {
        LocalDate date = LocalDate.of(year, month, day);
        return date.format(FORMATTER);
    }
}
