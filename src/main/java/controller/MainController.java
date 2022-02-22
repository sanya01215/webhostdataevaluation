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

import static service.split.LineAttrOrderEnum.C_D;

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
            switch (argumentsMap.get(C_D)) {
                case "C" -> {
                    //check invalid number of line arguments
                    if (argumentsMap.size() != 6) throw new ParseInputParametersException();
                    customerDataList.add(customerDataMainParserParser.parseIncomeData(argumentsMap));
                }
                case "D" -> {
                    //check invalid number of line arguments
                    if (argumentsMap.size() != 5) throw new ParseInputParametersException();
                    executeQuery(argumentsMap, customerDataList, outputFilePath);
                }
                default -> parsingLineCount = Integer.parseInt(argumentsMap.get(C_D));
            }
        }
    }

    private void executeQuery(Map<LineAttrOrderEnum, String> attrMap, List<CustomerData> customerDataList, String outputFilePath) {
        QueryData queryData = queryMainParser.parseQuery(attrMap);
        List<CustomerData> matchToQueryData = queryExecutor.handleQuery(queryData, customerDataList);
        inputOutputHandler.processAndWriteOutput(matchToQueryData, outputFilePath);
    }
}
