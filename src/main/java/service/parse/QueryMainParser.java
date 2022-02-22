package service.parse;

import model.data.QueryData;
import model.data.category.QuestionType;
import model.data.category.ServiceType;
import model.date.FromToDate;
import service.Service;
import service.factory.ServiceFactory;
import service.parse.parts.LocalDateParser;
import service.parse.parts.QuestionTypeParser;
import service.parse.parts.ServiceTypeParser;
import service.split.LineAttrOrderEnum;

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

    public QueryData parseQuery(Map<LineAttrOrderEnum, String> attrMap) {
        QuestionType queryQT = questionTypeParser.parseQuestionType(attrMap.get(QUESTION_TYPE));
        ServiceType queryST = serviceTypeParser.parseServiceType(attrMap.get(SERVICE_TYPE));
        boolean isFirstResponse = attrMap.get(P_N).equals("P");
        FromToDate queryFromToDate;
        String dateToParse = attrMap.get(DATE);
        String twoDateDelimiter = "-";
        if(dateToParse.contains(twoDateDelimiter))
            queryFromToDate=localDateParser.parseTwoDate(dateToParse,twoDateDelimiter);
        else queryFromToDate=new FromToDate(localDateParser.parseOneDate(dateToParse));

        return new QueryData(queryQT,queryST,queryFromToDate,isFirstResponse);
    }

}
