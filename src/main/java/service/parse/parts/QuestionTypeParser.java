package service.parse.parts;

import model.data.HostData;
import model.data.QueryData;
import model.data.ReplyToCustomerData;
import model.data.category.CustomerQuestionType;
import model.data.category.CustomerServiceType;
import service.split.LineAttrOrder;

import java.util.Map;

import static service.split.LineAttrOrder.*;

public class QuestionTypeParser {
    public CustomerQuestionType parseQuestionType(Map<LineAttrOrder, String> attrMap) {
//        hostData.setFirstResponse(attrMap.get(P_N).equals("P"));
        return parseCustomerQuestionType(attrMap.get(QUESTION_TYPE));
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
