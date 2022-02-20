package service.factory;

import service.Service;
import service.handle.InputOutputHandler;
import service.handle.QueryHandler;
import service.parse.parts.*;
import service.split.StringSplitter;

import java.util.HashMap;
import java.util.Map;

import static service.factory.ServiceNamesEnum.*;

public class ServiceFactory {
    private static final Map<ServiceNamesEnum, Service> cashedServices;

    static {
        cashedServices = new HashMap<>();
    }


    public static QuestionType getQuestionTypeParser() {
        return (QuestionType) getFromCacheOrMakeParser(QUESTION_TYPE_PARSER);
    }

    public static IncomeData getIncomeDataParser() {
        return (IncomeData) getFromCacheOrMakeParser(INCOME_DATA_PARSER);
    }

    public static LocalDate getLocalDateParser() {
        return (LocalDate) getFromCacheOrMakeParser(LOCAL_DATE_PARSER);
    }

    public static Query getQueryParser() {
        return (Query) getFromCacheOrMakeParser(QUERY_PARSER);
    }

    public static ServiceType getServiceTypeParser() {
        return (ServiceType) getFromCacheOrMakeParser(SERVICE_TYPE_PARSER);
    }

    public static QueryHandler getQueryHandler() {
        return (QueryHandler) getFromCacheOrMakeParser(QUERY_HANDLER);
    }

    public static StringSplitter getStringSplitter() {
        return (StringSplitter) getFromCacheOrMakeParser(STRING_SPLITTER);
    }
    public static InputOutputHandler getInputOutputHandler(){
        return (InputOutputHandler)getFromCacheOrMakeParser(INPUT_OUTPUT_HANDLER);
    }


    private static Service getFromCacheOrMakeParser(ServiceNamesEnum serviceNameEnum) {
        if (cashedServices.containsKey(serviceNameEnum)) {
            return cashedServices.get(serviceNameEnum);
        }
        Service service = null;
        switch (serviceNameEnum) {
            case QUESTION_TYPE_PARSER -> service = new QuestionType();
            case INCOME_DATA_PARSER -> service = new IncomeData();
            case LOCAL_DATE_PARSER -> service = new LocalDate();
            case QUERY_PARSER -> service = new Query();
            case SERVICE_TYPE_PARSER -> service = new ServiceType();
            case QUERY_HANDLER -> service = new QueryHandler();
            case STRING_SPLITTER -> service = new StringSplitter();
            case INPUT_OUTPUT_HANDLER -> service = new InputOutputHandler();
        }
        cashedServices.put(serviceNameEnum, service);
        return service;
    }

}
