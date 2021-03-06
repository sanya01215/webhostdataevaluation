package controller;

import exception.ParseInputParametersException;
import model.data.QueryData;
import model.data.CustomerData;
import service.factory.ServiceFactory;
import service.handle.InputOutputHandler;
import service.handle.QueryExecutor;
import service.parse.CustomerDataMainParser;
import service.parse.QueryMainParser;
import service.split.LineAttrOrderEnum;
import service.split.StringSplitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static service.split.LineAttrOrderEnum.CUSTOMER_OR_QUERY_SYMBOL_POSITION;
import static appconst.IntConst.EXPECT_CUSTOMER_ARG_NUMBER;
import static appconst.IntConst.EXPECT_QUERY_ARG_NUMBER;
import static appconst.StringConst.*;

/**
 * The class controller combines all needed application services,
 * and set application functionality logic between them.
 */
public class MainController {
    private final StringSplitter splitter;
    private final CustomerDataMainParser customerDataMainParserParser;
    private final QueryMainParser queryMainParser;
    private final QueryExecutor queryExecutor;
    private final InputOutputHandler inputOutputHandler;
    public MainController() {
        this.queryExecutor = ServiceFactory.getQueryHandler();
        this.customerDataMainParserParser = ServiceFactory.getIncomeDataParser();
        this.splitter = ServiceFactory.getStringSplitter();
        this.queryMainParser = ServiceFactory.getQueryParser();
        this.inputOutputHandler = ServiceFactory.getInputOutputHandler();
    }

    public void processData(String inputFilePath, String outputFilePath) {
        List<CustomerData> customerDataList = new ArrayList<>();
        List<String> inputList = inputOutputHandler.getListFromFile(inputFilePath);
        int parsingLineCount = 0;
        for (String s : inputList) {
            Map<LineAttrOrderEnum, String> argumentsMap = splitter.splitAttrLine(s);
            switch (argumentsMap.get(CUSTOMER_OR_QUERY_SYMBOL_POSITION)) {
                case  CUSTOMER_SYMBOL -> {
                    //check invalid number of customer arguments
                    if (argumentsMap.size() != EXPECT_CUSTOMER_ARG_NUMBER) throw new ParseInputParametersException("Invalid number of customer arguments");
                    customerDataList.add(customerDataMainParserParser.parseIncomeData(argumentsMap));
                }
                case QUERY_SYMBOL -> {
                    //check invalid number of query arguments
                    if (argumentsMap.size() != EXPECT_QUERY_ARG_NUMBER) throw new ParseInputParametersException("Invalid number of query arguments");
                    executeQuery(argumentsMap, customerDataList, outputFilePath);
                }
                default -> parsingLineCount = Integer.parseInt(argumentsMap.get(CUSTOMER_OR_QUERY_SYMBOL_POSITION));
            }
        }
    }

    private void executeQuery(Map<LineAttrOrderEnum, String> attrMap, List<CustomerData> customerDataList, String outputFilePath) {
        QueryData queryData = queryMainParser.parseQuery(attrMap);
        List<CustomerData> matchToQueryData = queryExecutor.handleQuery(queryData, customerDataList);
        inputOutputHandler.processAndWriteOutput(matchToQueryData, outputFilePath);
    }
}
