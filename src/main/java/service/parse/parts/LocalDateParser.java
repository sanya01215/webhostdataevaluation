package service.parse.parts;

import model.date.FromToDate;
import service.split.LineAttrOrder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Map;

import static service.split.LineAttrOrder.DATE;

public class LocalDateParser {
    public FromToDate parseDateForQuery(Map<LineAttrOrder, String> attrMap){
        FromToDate fromToDate = new FromToDate();
        String[] betweenDate = attrMap.get(DATE).split("-");
        if (betweenDate.length > 1) {
            fromToDate.setToDate(parseDate(betweenDate[1]));
        }
        fromToDate.setFromDate(parseDate(betweenDate[0]));
        return fromToDate;
    }
    private LocalDate parseDate(String date) {
        DateTimeFormatter formatter;
        LocalDate result;
        try {
             formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
            result = LocalDate.parse(date, formatter);
        }
        catch (DateTimeParseException e){
            formatter = DateTimeFormatter.ofPattern("d.M.yyyy", Locale.ENGLISH);
            result =LocalDate.parse(date, formatter);
        }
        return result;
    }
}
