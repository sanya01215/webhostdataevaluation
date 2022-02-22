package service.parse;

import model.data.CustomerData;
import model.data.category.QuestionType;
import model.data.category.ServiceType;
import service.Service;
import service.factory.ServiceFactory;
import service.parse.parts.LocalDateParser;
import service.parse.parts.QuestionTypeParser;
import service.parse.parts.ServiceTypeParser;
import service.split.LineAttrOrderEnum;

import java.time.LocalDate;
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
        QuestionType cusQT=questionTypeParser.parseQuestionType(attrMap.get(QUESTION_TYPE));
        ServiceType cusST = serviceTypeParser.parseServiceType(attrMap.get(SERVICE_TYPE));
        boolean cusIsFirstResponse = attrMap.get(P_N).equals("P");
        int cusReplyInMinutes = Integer.parseInt(attrMap.get(REPLY_TIME));
        LocalDate cusDate = localDateParser.parseOneDate(attrMap.get(DATE));
        return new CustomerData(cusQT,cusST,cusIsFirstResponse,cusReplyInMinutes,cusDate);
    }
}
