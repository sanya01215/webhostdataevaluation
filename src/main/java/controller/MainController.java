package controller;

import model.data.QueryData;
import model.data.CustomerData;
import service.factory.ServiceFactory;
import service.handle.InputOutputHandler;
import service.handle.QueryHandler;
import service.parse.parts.IncomeData;
import service.parse.parts.Query;
import service.split.LineAttrOrderEnum;
import service.split.StringSplitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static service.split.LineAttrOrderEnum.C_D;

public class MainController {
    private final StringSplitter splitter;
    private final IncomeData incomeDataParser;
    private final Query queryParser;
    private final QueryHandler queryHandler;
    private final InputOutputHandler inputOutputHandler;

    public MainController() {
        this.queryHandler = ServiceFactory.getQueryHandler();
        this.incomeDataParser = ServiceFactory.getIncomeDataParser();
        this.splitter = ServiceFactory.getStringSplitter();
        this.queryParser = ServiceFactory.getQueryParser();
        this.inputOutputHandler = ServiceFactory.getInputOutputHandler();
    }

    public void processData(String inputFilePath, String outputFilePath) {
        List<CustomerData> customerDataList = new ArrayList<>();
        List<String> inputList = inputOutputHandler.getListFromFile(inputFilePath);
        int parsingLineCount = 0;

        for (String s : inputList) {
            Map<LineAttrOrderEnum, String> attrMap = splitter.splitAttrLine(s);
            switch (attrMap.get(C_D)) {
                case "C" -> customerDataList.add(incomeDataParser.parseIncomeData(attrMap));
                case "D" -> executeQuery(attrMap, customerDataList, outputFilePath);
                default -> parsingLineCount = Integer.parseInt(attrMap.get(C_D));
            }
        }
    }

    private void executeQuery(Map<LineAttrOrderEnum, String> attrMap, List<CustomerData> customerDataList, String outputFilePath) {
        QueryData queryData = queryParser.parseQuery(attrMap, customerDataList, outputFilePath);
        List<CustomerData> matchToQueryData = queryHandler.handleQuery(queryData, customerDataList, outputFilePath);
        inputOutputHandler.processAndWriteOutput(matchToQueryData, outputFilePath);
    }

}
