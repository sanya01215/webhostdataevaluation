package service.parse.parts;

import model.data.category.CustomerServiceType;
import service.Service;
import service.split.LineAttrOrderEnum;

/**
 * The class provides processing string, that specifies SERVICE_TYPE {@link LineAttrOrderEnum} part of input line ,
 * and returns parsed {@link CustomerServiceType} from it.
 */
public class ServiceTypeParser implements Service {
    public CustomerServiceType parseServiceType(String serviceTypePart) {
        return parseCustomerServiceType(serviceTypePart);
    }

    private CustomerServiceType parseCustomerServiceType(String lineAttr) {
        CustomerServiceType serviceType = new CustomerServiceType();
        String[] lineAttributesArray = lineAttr.split("\\.");
//if all types (*) selected, lets specify it by -1 value
        if (lineAttributesArray[0].equals("*")) {
            serviceType.setServiceId(-1);
            return serviceType;
        }
        if (lineAttributesArray.length > 1) serviceType.setVariationId(Integer.parseInt(lineAttributesArray[1]));
        serviceType.setServiceId(Integer.parseInt(lineAttributesArray[0]));
        return serviceType;
    }
}
