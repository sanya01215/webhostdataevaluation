package model.date;

import java.time.LocalDate;

/**
 * The class represents period between dates or just one date.
 */
public class FromToDate {
    private LocalDate fromDate;
    private LocalDate toDate;

    public FromToDate(LocalDate fromDate, LocalDate toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public FromToDate(LocalDate fromDate) {
        this.fromDate = fromDate;
        this.toDate = fromDate;
    }

    public void setFromToDate(LocalDate fromDate, LocalDate toDate) {
        this.fromDate = fromDate;
    }

    public void setFromToDate(LocalDate fromDate) {
        this.fromDate = fromDate;
        this.toDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }
}
