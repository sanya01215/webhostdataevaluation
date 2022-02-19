package service.handle;

import model.data.QueryData;
import model.data.ReplyToCustomerData;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class QueryHandler {
    public void handleQuery(QueryData queryData, List<ReplyToCustomerData> replyToCustomerDataList, String outputFilePath) {
        List<ReplyToCustomerData> matchToQueryData = new ArrayList<>();
        for (ReplyToCustomerData rtcData : replyToCustomerDataList) {

            if (!checkServiceType(rtcData, queryData)) continue;
            if (!checkQuestionType(rtcData, queryData)) continue;
            if (!checkDate(rtcData, queryData)) continue;
            matchToQueryData.add(rtcData);
        }
            processAndWriteOutput(matchToQueryData, outputFilePath);
    }

    private boolean checkServiceType(ReplyToCustomerData customerData, QueryData queryData) {
        //if service id == -1 means all matches
        if (queryData.getCustomerServiceType().getServiceId() == -1) return true;
        if (queryData.getCustomerServiceType().getVariationId() == 0) {
            return queryData.getCustomerServiceType().getServiceId()
                    == customerData.getCustomerServiceType().getServiceId();
        }
        return customerData.getCustomerServiceType().equals(queryData.getCustomerServiceType());
    }

    private boolean checkQuestionType(ReplyToCustomerData customerData, QueryData queryData) {
        //if service id == -1 means all matches
        if (queryData.getCustomerQuestionType().getQuestionTypeId() == -1) return true;
        if (queryData.getCustomerQuestionType().getCategoryId() == 0 && (queryData.getCustomerQuestionType().getSubCategoryId() == 0))
        {
            return queryData.getCustomerQuestionType().getQuestionTypeId()
                    == customerData.getCustomerQuestionType().getQuestionTypeId();
        }
        if (queryData.getCustomerQuestionType().getSubCategoryId() == 0)
        {
            boolean result1 = false;
            boolean result2 = false;
             result1 = queryData.getCustomerQuestionType().getQuestionTypeId()
                    == customerData.getCustomerQuestionType().getQuestionTypeId();
             result2 = queryData.getCustomerQuestionType().getCategoryId()
                     == customerData.getCustomerQuestionType().getCategoryId();
             return (result1 && result2);
        }
        return customerData.getCustomerQuestionType().equals(queryData.getCustomerQuestionType());
    }

    private boolean checkDate(ReplyToCustomerData customerData, QueryData queryData) {
        LocalDate checkedDate = customerData.getFromToDate().getFromDate();
        LocalDate fromDate = queryData.getFromToDate().getFromDate();
        LocalDate toDate = queryData.getFromToDate().getToDate();
        if (toDate == null) return checkedDate.equals(fromDate);
        boolean result = checkedDate.isAfter(fromDate) && checkedDate.isBefore(toDate);
        return result;
    }

    private void processAndWriteOutput(List<ReplyToCustomerData> matchToQueryData, String outputFilePath) {
        Path path = Paths.get(outputFilePath);
        try {
            Files.writeString(path, processOutput(matchToQueryData)+System.lineSeparator(), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String processOutput(List<ReplyToCustomerData> matchToQueryData) {
        if (matchToQueryData.size() > 1) return outputAverageMinutes(matchToQueryData);
        else if(matchToQueryData.size() ==1) return String.valueOf(matchToQueryData.get(0).getReplyTimeInMinutes());
        else return "-";
    }

    private String outputAverageMinutes(List<ReplyToCustomerData> matchToQueryData) {
        int sumMinutes = 0;
        for (ReplyToCustomerData customerData : matchToQueryData) {
            sumMinutes += customerData.getReplyTimeInMinutes();
        }
        return String.valueOf(sumMinutes / matchToQueryData.size());
    }
}
