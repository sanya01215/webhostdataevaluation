package service.parse.parts;

import exception.ParseInputParametersException;
import model.date.FromToDate;
import service.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;


/**
 * The class provides processing string, that are DATE part of input line,
 * and returns parsed date from it.
 */
public class LocalDateParser implements Service {
    private final DateTimeFormatter formatterWithLeadZero
            = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
    private final DateTimeFormatter formatterWithNoLeadZero
            = DateTimeFormatter.ofPattern("d.M.yyyy", Locale.ENGLISH);

    public FromToDate parseTwoDate(String datesString,String dateDelimiter) {
        String[] betweenDate = datesString.split(dateDelimiter);
        if(betweenDate.length != 2)
            throw new ParseInputParametersException("Invalid number of Dates");
        LocalDate fromDate = parseDate(betweenDate[0]);
        LocalDate toDate = parseDate(betweenDate[1]);
        return new FromToDate(fromDate,toDate);
    }
    public LocalDate parseOneDate(String date){
        return parseDate(date);
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
