package service.parse.parts;

import model.date.FromToDate;
import service.Service;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;


/**
 * The class provides processing string, that are DATE part of input line,
 * and returns parsed {@link FromToDate} from it.
 */
public class LocalDateParser implements Service {
    DateTimeFormatter formatterWithLeadZero = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
    DateTimeFormatter formatterWithNoLeadZero = DateTimeFormatter.ofPattern("d.M.yyyy", Locale.ENGLISH);

    public FromToDate parseDateForQuery(String datesString) {
        FromToDate fromToDate = new FromToDate();
        String[] betweenDate = datesString.split("-");
        //if it is query for period between dates, save two dates
        if (betweenDate.length > 1) {
            fromToDate.setToDate(parseDate(betweenDate[1]));
        }
        fromToDate.setFromDate(parseDate(betweenDate[0]));
        return fromToDate;
    }

    private java.time.LocalDate parseDate(String date) {
        java.time.LocalDate result;
        try {
            result = java.time.LocalDate.parse(date, formatterWithLeadZero);
        } catch (DateTimeParseException e) {
            result = java.time.LocalDate.parse(date, formatterWithNoLeadZero);
        }
        return result;
    }
}