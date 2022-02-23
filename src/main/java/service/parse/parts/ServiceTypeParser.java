package service.parse.parts;

import model.data.category.ServiceType;
import service.Service;
import service.split.LineAttrOrderEnum;

import static appconst.StringConst.ALL_SYMBOL;
import static appconst.StringConst.SUB_ARG_DELIMITER;

/**
 * The class provides processing string, that specifies SERVICE_TYPE {@link LineAttrOrderEnum} part of input line ,
 * and returns parsed {@link ServiceType} from it.
 */
public class ServiceTypeParser implements Service {
    public ServiceType parseServiceType(String serviceTypePart) {
        return parseCustomerServiceType(serviceTypePart);
    }

    private ServiceType parseCustomerServiceType(String lineAttr) {
        ServiceType serviceType = new ServiceType();
        String[] lineAttributesArray = lineAttr.split(SUB_ARG_DELIMITER);
//if all types (*) selected, lets specify it by -1 value
        if (lineAttributesArray[0].equals(ALL_SYMBOL)) {
            serviceType.setServiceId(-1);
            return serviceType;
        }
        if (lineAttributesArray.length > 1) serviceType.setVariationId(Integer.parseInt(lineAttributesArray[1]));
        serviceType.setServiceId(Integer.parseInt(lineAttributesArray[0]));
        return serviceType;
    }
}
