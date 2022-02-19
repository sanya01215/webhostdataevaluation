package service.parse.parts;

import model.data.category.CustomerServiceType;
import service.split.LineAttrOrder;

import java.util.Map;

import static service.split.LineAttrOrder.*;

public class ServiceTypeParser {
    public CustomerServiceType parseServQuesType(Map<LineAttrOrder, String> attrMap) {
        return parseCustomerServiceType(attrMap.get(SERVICE_TYPE));
    }

    private CustomerServiceType parseCustomerServiceType(String lineAttr) {
        CustomerServiceType serviceType = new CustomerServiceType();
        String[] lineAttributesArray = lineAttr.split("\\.");
//if all types (*) selected, lets specify it by -1 value
        if(lineAttributesArray[0].equals("*")) {
            serviceType.setServiceId(-1);
            return serviceType;
        }
        if (lineAttributesArray.length > 1) serviceType.setVariationId(Integer.parseInt(lineAttributesArray[1]));
        serviceType.setServiceId(Integer.parseInt(lineAttributesArray[0]));
        return serviceType;
    }
}
