package service.parse.parts;

import model.data.QueryData;
import model.data.CustomerData;
import service.Service;
import service.factory.ServiceFactory;
import service.split.LineAttrOrderEnum;

import java.util.List;
import java.util.Map;

import static service.split.LineAttrOrderEnum.*;

public class Query implements Service {

    private final QuestionType questionTypeParser;
    private final ServiceType serviceTypeParser;
    private final LocalDate localDateParser;

    public Query() {
        this.questionTypeParser = ServiceFactory.getQuestionTypeParser();
        this.serviceTypeParser = ServiceFactory.getServiceTypeParser();
        this.localDateParser = ServiceFactory.getLocalDateParser();
    }
    public QueryData parseQuery(Map<LineAttrOrderEnum,String> attrMap, List<CustomerData> customerDataList, String outputFilePath) {
        QueryData queryData = new QueryData();
        queryData.setCustomerQuestionType(questionTypeParser.parseQuestionType(attrMap.get(QUESTION_TYPE)));
        queryData.setCustomerServiceType(serviceTypeParser.parseServiceType(attrMap.get(SERVICE_TYPE)));
        queryData.setFromToDate(localDateParser.parseDateForQuery(attrMap.get(DATE)));
        return queryData;
    }
}
