package service.parse.parts;

import model.data.category.CustomerQuestionType;
import model.data.category.CustomerServiceType;
import model.data.HostData;
import model.data.QueryData;
import model.data.ReplyToCustomerData;
import service.split.LineAttrOrder;

import static service.split.LineAttrOrder.*;

import java.util.Map;

public class ServiceQuestionTypeParser {

    public HostData parseServQuesType(Map<LineAttrOrder,String> attrMap, boolean isQuery) {
        HostData hostData;
        if (isQuery) hostData = new QueryData();
        else hostData = new ReplyToCustomerData();

        CustomerServiceType customerServiceType = parseCustomerServiceType(attrMap.get(SERVICE_TYPE));
        CustomerQuestionType customerQuestionType = parseCustomerQuestionType(attrMap.get(QUESTION_TYPE));
        hostData.setFirstResponse(attrMap.get(P_N).equals("P"));

        hostData.setCustomerServiceType(customerServiceType);
        hostData.setCustomerQuestionType(customerQuestionType);
        return hostData;
    }

    private CustomerServiceType parseCustomerServiceType(String lineAttr) {
        String[] lineAttributesArray = lineAttr.split("\\.");
        CustomerServiceType serviceType = new CustomerServiceType();
        if (lineAttributesArray.length > 1) serviceType.setVariationId(Integer.parseInt(lineAttributesArray[1]));
        serviceType.setServiceId(Integer.parseInt(lineAttributesArray[0]));
        return serviceType;
    }

    private CustomerQuestionType parseCustomerQuestionType(String lineAttr) {
        String[] lineAttributesArray = lineAttr.split("\\.");
        CustomerQuestionType customerQuestionType = new CustomerQuestionType();
        customerQuestionType.setQuestionTypeId(Integer.parseInt(lineAttributesArray[0]));
        if (lineAttributesArray.length > 1)
            customerQuestionType.setCategoryId(Integer.parseInt(lineAttributesArray[1]));
        if (lineAttributesArray.length > 2)
            customerQuestionType.setSubCategoryId(Integer.parseInt(lineAttributesArray[2]));
        return customerQuestionType;
    }
}
