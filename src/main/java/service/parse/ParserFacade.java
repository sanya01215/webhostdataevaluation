package service.parse;

import model.data.QueryData;
import model.data.ReplyToCustomerData;
import service.handle.QueryHandler;
import service.parse.parts.LocalDateParser;
import service.parse.parts.QuestionTypeParser;
import service.parse.parts.ServiceTypeParser;
import service.split.LineAttrOrder;
import service.split.StringSplitter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static service.split.LineAttrOrder.*;

public class ParserFacade {
    private final QueryHandler queryHandler;
    private final QuestionTypeParser questionTypeParser;
    private final ServiceTypeParser serviceTypeParser;
    private final LocalDateParser localDateParser;
    private final StringSplitter splitter;

    public ParserFacade() {
        this.questionTypeParser = new QuestionTypeParser();
        this.serviceTypeParser = new ServiceTypeParser();
        this.queryHandler = new QueryHandler();
        this.localDateParser = new LocalDateParser();
        this.splitter = new StringSplitter();
    }

    public void parseData(String inputFilePath, String outputFilePath) {
        List<ReplyToCustomerData> replyToCustomerDataList = new ArrayList<>();
        List<String> inputList = getListFromFile(inputFilePath);
        int parsingLineCount = 0;

        for (String s : inputList) {
            Map<LineAttrOrder, String> attrMap = splitter.splitAttrLine(s);

            if (attrMap.get(C_D).equals("C"))
                replyToCustomerDataList.add(parseIncomeData(attrMap));
            else if(attrMap.get(C_D).equals("D")) {
                parseQuery(attrMap,replyToCustomerDataList,outputFilePath);
            }
            else parsingLineCount = Integer.parseInt(attrMap.get(C_D));
        }
    }

    private ReplyToCustomerData parseIncomeData(Map<LineAttrOrder, String> attrMap) {
        ReplyToCustomerData replyToCustomerData = new ReplyToCustomerData();
        replyToCustomerData.setCustomerQuestionType(questionTypeParser.parseQuestionType(attrMap));
        replyToCustomerData.setCustomerServiceType(serviceTypeParser.parseServQuesType(attrMap));
        replyToCustomerData.setFirstResponse(attrMap.get(P_N).equals("P"));
        replyToCustomerData.setReplyTimeInMinutes(Integer.parseInt(attrMap.get(REPLY_TIME)));
        replyToCustomerData.setFromToDate(localDateParser.parseDateForQuery(attrMap));
        return replyToCustomerData;
    }

    private void parseQuery(Map<LineAttrOrder,String> attrMap, List<ReplyToCustomerData> replyToCustomerDataList,String outputFilePath) {
        QueryData queryData = new QueryData();
        queryData.setCustomerQuestionType(questionTypeParser.parseQuestionType(attrMap));
        queryData.setCustomerServiceType(serviceTypeParser.parseServQuesType(attrMap));
        queryData.setFromToDate(localDateParser.parseDateForQuery(attrMap));
        queryHandler.handleQuery(queryData,replyToCustomerDataList,outputFilePath);
    }

    private List<String> getListFromFile(String path) {
        List<String> result = null;
        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            result = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
