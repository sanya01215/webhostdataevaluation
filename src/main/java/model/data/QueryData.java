package model.data;

import model.date.FromToDate;

public class QueryData extends HostData {
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
