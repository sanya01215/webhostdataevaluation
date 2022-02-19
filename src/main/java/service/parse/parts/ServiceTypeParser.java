package service.parse.parts;

import model.data.HostData;
import model.data.QueryData;
import model.data.ReplyToCustomerData;
import model.data.category.CustomerQuestionType;
import model.data.category.CustomerServiceType;
import service.split.LineAttrOrder;

import java.util.Map;

import static service.split.LineAttrOrder.*;

public class ServiceTypeParser {
    public CustomerServiceType parseServQuesType(Map<LineAttrOrder, String> attrMap) {
        return parseCustomerServiceType(attrMap.get(SERVICE_TYPE));
    }

    private CustomerServiceType parseCustomerServiceType(String lineAttr) {
        String[] lineAttributesArray = lineAttr.split("\\.");
        CustomerServiceType serviceType = new CustomerServiceType();
        if (lineAttributesArray.length > 1) serviceType.setVariationId(Integer.parseInt(lineAttributesArray[1]));
        serviceType.setServiceId(Integer.parseInt(lineAttributesArray[0]));
        return serviceType;
    }
}
