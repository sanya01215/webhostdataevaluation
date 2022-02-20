package service.handle;

import model.data.QueryData;
import model.data.CustomerData;
import model.data.category.CustomerQuestionType;
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
    public List<CustomerData> handleQuery(QueryData queryData, List<CustomerData> customerDataList, String outputFilePath) {
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
        if (queryData.getCustomerServiceType().getServiceId() == -1) return true;
        if (queryData.getCustomerServiceType().getVariationId() == 0) {
            return queryData.getCustomerServiceType().getServiceId()
                    == customerData.getCustomerServiceType().getServiceId();
        }
        return customerData.getCustomerServiceType().equals(queryData.getCustomerServiceType());
    }

    private boolean checkQuestionType(CustomerData customerData, QueryData queryData) {
        CustomerQuestionType customerQType = customerData.getCustomerQuestionType();
        CustomerQuestionType queryQType = queryData.getCustomerQuestionType();
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
        return customerData.getCustomerQuestionType().equals(queryData.getCustomerQuestionType());
    }

    private boolean checkDate(CustomerData customerData, QueryData queryData) {
        LocalDate checkedDate = customerData.getFromToDate().getFromDate();
        LocalDate fromDate = queryData.getFromToDate().getFromDate();
        LocalDate toDate = queryData.getFromToDate().getToDate();
        //if query date is not period
        if (toDate == null) return checkedDate.equals(fromDate);
        //if query date is period
        return checkedDate.isAfter(fromDate) && checkedDate.isBefore(toDate);
    }
}
