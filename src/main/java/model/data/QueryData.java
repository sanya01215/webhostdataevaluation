package model.data;

import model.data.category.QuestionType;
import model.data.category.ServiceType;
import model.date.FromToDate;

import java.time.LocalDate;

/**
 * The class represents Query for analytical service
 * which includes: type of question, category, service
 * and period of time, which have to be processed in QueryHandler.
 */
public class QueryData extends Data{
    private FromToDate fromToQueryDate;
    private LocalDate queryDate;

    public LocalDate getQueryDate() {
        return queryDate;
    }

    public void setQueryDate(LocalDate queryDate) {
        this.queryDate = queryDate;
    }

    public FromToDate getFromToQueryDate() {
        return fromToQueryDate;
    }

    public void setFromToQueryDate(FromToDate fromToQueryDate) {
        this.fromToQueryDate = fromToQueryDate;
    }


    public QueryData(QuestionType questionType, ServiceType serviceType, FromToDate fromToQueryDate, boolean isFirstResponse) {
        super(questionType,serviceType,isFirstResponse);
        this.fromToQueryDate = fromToQueryDate;
    }
}
