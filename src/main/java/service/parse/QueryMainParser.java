package service.parse;

import model.data.QueryData;
import model.data.CustomerData;
import service.Service;
import service.factory.ServiceFactory;
import service.parse.parts.LocalDateParser;
import service.parse.parts.QuestionTypeParser;
import service.parse.parts.ServiceTypeParser;
import service.split.LineAttrOrderEnum;

import java.util.List;
import java.util.Map;

import static service.split.LineAttrOrderEnum.*;

/**
 * The class provides parsing all query data from map,
 * which contains attributes from input line that specifies query data.
 */
public class QueryMainParser implements Service {

    private final QuestionTypeParser questionTypeParser;
    private final ServiceTypeParser serviceTypeParser;
    private final LocalDateParser localDateParser;

    public QueryMainParser() {
        this.questionTypeParser = ServiceFactory.getQuestionTypeParser();
        this.serviceTypeParser = ServiceFactory.getServiceTypeParser();
        this.localDateParser = ServiceFactory.getLocalDateParser();
    }

    public QueryData parseQuery(Map<LineAttrOrderEnum, String> attrMap, List<CustomerData> customerDataList, String outputFilePath) {
        QueryData queryData = new QueryData();
        queryData.setCustomerQuestionType(questionTypeParser.parseQuestionType(attrMap.get(QUESTION_TYPE)));
        queryData.setCustomerServiceType(serviceTypeParser.parseServiceType(attrMap.get(SERVICE_TYPE)));
        queryData.setFromToDate(localDateParser.parseDateForQuery(attrMap.get(DATE)));
        return queryData;
    }
}
