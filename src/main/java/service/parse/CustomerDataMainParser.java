package service.parse;

import model.data.CustomerData;
import service.Service;
import service.factory.ServiceFactory;
import service.parse.parts.LocalDateParser;
import service.parse.parts.QuestionTypeParser;
import service.parse.parts.ServiceTypeParser;
import service.split.LineAttrOrderEnum;

import java.util.Map;

import static service.split.LineAttrOrderEnum.*;

/**
 * The class provides parsing customer all data from map,
 * which contains attributes from input line that specifies customer data.
 */
public class CustomerDataMainParser implements Service {

    private final QuestionTypeParser questionTypeParser;
    private final ServiceTypeParser serviceTypeParser;
    private final LocalDateParser localDateParser;

    public CustomerDataMainParser() {
        this.questionTypeParser = ServiceFactory.getQuestionTypeParser();
        this.serviceTypeParser = ServiceFactory.getServiceTypeParser();
        this.localDateParser = ServiceFactory.getLocalDateParser();
    }

    public CustomerData parseIncomeData(Map<LineAttrOrderEnum, String> attrMap) {
        CustomerData customerData = new CustomerData();
        customerData.setCustomerQuestionType(questionTypeParser.parseQuestionType(attrMap.get(QUESTION_TYPE)));
        customerData.setCustomerServiceType(serviceTypeParser.parseServiceType(attrMap.get(SERVICE_TYPE)));
        customerData.setFirstResponse(attrMap.get(P_N).equals("P"));
        customerData.setReplyTimeInMinutes(Integer.parseInt(attrMap.get(REPLY_TIME)));
        customerData.setFromToDate(localDateParser.parseDateForQuery(attrMap.get(DATE)));
        return customerData;
    }
}
