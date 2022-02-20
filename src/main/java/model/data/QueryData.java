package model.data;

import model.date.FromToDate;
/**
 * The class represents Query for analytical service
 * which includes: type of question, category, service
 * and period of time, which have to be processed in QueryHandler.
 */
public class QueryData extends IncomeData {
    private FromToDate fromToDate;

    @Override
    public FromToDate getFromToDate() {
        return fromToDate;
    }

    @Override
    public void setFromToDate(FromToDate fromToDate) {
        this.fromToDate = fromToDate;
    }
}
