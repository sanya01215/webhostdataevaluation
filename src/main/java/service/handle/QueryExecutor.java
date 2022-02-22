package service.handle;

import model.data.QueryData;
import model.data.CustomerData;
import model.data.category.QuestionType;
import model.data.category.ServiceType;
import service.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 * The class provides executing query by comparing parameters to all CustomerData in list
 * and outputting suitable CustomerData.
 * If category value equals -1 it means all categories(*);
 */
public class QueryExecutor implements Service {
    public List<CustomerData> handleQuery(QueryData queryData, List<CustomerData> customerDataList) {
        List<CustomerData> matchToQueryData = new ArrayList<>();
        for (CustomerData rtcData : customerDataList) {
            //check if all query parameters are match
            if (!checkServiceType(rtcData, queryData)) continue;
            if (!checkQuestionType(rtcData, queryData)) continue;
            if (!checkDate(rtcData, queryData)) continue;
            matchToQueryData.add(rtcData);
        }
        return matchToQueryData;
    }

    private boolean checkServiceType(CustomerData customerData, QueryData queryData) {
        //if service id == -1 means all matches
        ServiceType queryServiceType = queryData.getServiceType();
        ServiceType customerServiceType = customerData.getServiceType();
        if (queryServiceType.getServiceId() == -1) return true;
        if (queryServiceType.getVariationId() == 0) {
            return queryServiceType.getServiceId()
                    == customerServiceType.getServiceId();
        }
        return customerServiceType.equals(queryServiceType);
    }

    private boolean checkQuestionType(CustomerData customerData, QueryData queryData) {
        QuestionType customerQType = customerData.getQuestionType();
        QuestionType queryQType = queryData.getQuestionType();

        int customerQId = customerQType.getQuestionTypeId();
        int customerCatId = customerQType.getCategoryId();
        int customerSubCatId = customerQType.getSubCategoryId();
        int queryQId = queryQType.getQuestionTypeId();
        int queryCatId = queryQType.getCategoryId();
        int querySubCatId = queryQType.getSubCategoryId();

        //if query service id == -1 means all matches
        if (queryQId == -1) return true;
        if (queryCatId == 0 && querySubCatId == 0) return queryQId == customerQId;
        if (customerSubCatId == 0) return (queryQId == customerQId && queryCatId == customerCatId);
        return customerQType.equals(queryQType);
    }

    private boolean checkDate(CustomerData customerData, QueryData queryData) {
        LocalDate checkedDate = customerData.getCustomerDate();
        LocalDate fromDate = queryData.getFromToQueryDate().getFromDate();
        LocalDate toDate = queryData.getFromToQueryDate().getToDate();
        //if query date is not period
        if (toDate == null) return checkedDate.equals(fromDate);
        //if query date is period
        return checkedDate.isAfter(fromDate) && checkedDate.isBefore(toDate);
    }
}
