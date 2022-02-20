package service.parse.parts;

import model.data.CustomerData;
import service.Service;
import service.handle.QueryHandler;
import service.factory.ServiceFactory;
import service.split.LineAttrOrderEnum;
import service.split.StringSplitter;

import java.util.Map;

import static service.split.LineAttrOrderEnum.*;

public class IncomeData implements Service {

    private final QueryHandler queryHandler;
    private final QuestionType questionTypeParser;
    private final ServiceType serviceTypeParser;
    private final LocalDate localDateParser;
    private final StringSplitter splitter;

    public IncomeData() {
        this.questionTypeParser = ServiceFactory.getQuestionTypeParser();
        this.serviceTypeParser = ServiceFactory.getServiceTypeParser();
        this.queryHandler = ServiceFactory.getQueryHandler();
        this.localDateParser = ServiceFactory.getLocalDateParser();
        this.splitter = ServiceFactory.getStringSplitter();
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
