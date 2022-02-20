package service.factory;

import service.Service;
import service.handle.InputOutputHandler;
import service.handle.QueryExecutor;
import service.parse.*;
import service.parse.parts.LocalDateParser;
import service.parse.parts.QuestionTypeParser;
import service.parse.parts.ServiceTypeParser;
import service.split.StringSplitter;

import java.util.HashMap;
import java.util.Map;

import static service.factory.ServiceNamesEnum.*;

/**
 * The class provides static factory for Service objects,
 * it implements caching in HashMap,
 * so it returns same service object every time.
 */
public class ServiceFactory {
    private static final Map<ServiceNamesEnum, Service> cashedServices;

    static {
        cashedServices = new HashMap<>();
    }

    public static QuestionTypeParser getQuestionTypeParser() {
        return (QuestionTypeParser) getFromCacheOrMakeParser(QUESTION_TYPE_PARSER);
    }

    public static CustomerDataMainParser getIncomeDataParser() {
        return (CustomerDataMainParser) getFromCacheOrMakeParser(INCOME_DATA_PARSER);
    }

    public static LocalDateParser getLocalDateParser() {
        return (LocalDateParser) getFromCacheOrMakeParser(LOCAL_DATE_PARSER);
    }

    public static QueryMainParser getQueryParser() {
        return (QueryMainParser) getFromCacheOrMakeParser(QUERY_PARSER);
    }

    public static ServiceTypeParser getServiceTypeParser() {
        return (ServiceTypeParser) getFromCacheOrMakeParser(SERVICE_TYPE_PARSER);
    }

    public static QueryExecutor getQueryHandler() {
        return (QueryExecutor) getFromCacheOrMakeParser(QUERY_HANDLER);
    }

    public static StringSplitter getStringSplitter() {
        return (StringSplitter) getFromCacheOrMakeParser(STRING_SPLITTER);
    }

    public static InputOutputHandler getInputOutputHandler() {
        return (InputOutputHandler) getFromCacheOrMakeParser(INPUT_OUTPUT_HANDLER);
    }


    private static Service getFromCacheOrMakeParser(ServiceNamesEnum serviceNameEnum) {
        if (cashedServices.containsKey(serviceNameEnum)) {
            return cashedServices.get(serviceNameEnum);
        }
        Service service = null;
        switch (serviceNameEnum) {
            case QUESTION_TYPE_PARSER -> service = new QuestionTypeParser();
            case INCOME_DATA_PARSER -> service = new CustomerDataMainParser();
            case LOCAL_DATE_PARSER -> service = new LocalDateParser();
            case QUERY_PARSER -> service = new QueryMainParser();
            case SERVICE_TYPE_PARSER -> service = new ServiceTypeParser();
            case QUERY_HANDLER -> service = new QueryExecutor();
            case STRING_SPLITTER -> service = new StringSplitter();
            case INPUT_OUTPUT_HANDLER -> service = new InputOutputHandler();
        }
        cashedServices.put(serviceNameEnum, service);
        return service;
    }

}
