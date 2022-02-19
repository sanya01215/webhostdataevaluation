package service.parse.parts;

import model.data.category.CustomerQuestionType;
import service.split.LineAttrOrder;

import java.util.Map;

import static service.split.LineAttrOrder.*;

public class QuestionTypeParser {
    public CustomerQuestionType parseQuestionType(Map<LineAttrOrder, String> attrMap) {
        return parseCustomerQuestionType(attrMap.get(QUESTION_TYPE));
    }

    private CustomerQuestionType parseCustomerQuestionType(String lineAttr) {
        String[] lineAttributesArray = lineAttr.split("\\.");
        CustomerQuestionType customerQuestionType = new CustomerQuestionType();
        //if all types (*) selected, lets specify it by -1 value
        if(lineAttributesArray[0].equals("*")) {
            customerQuestionType.setQuestionTypeId(-1);
            return customerQuestionType;
        }
        customerQuestionType.setQuestionTypeId(Integer.parseInt(lineAttributesArray[0]));
        if (lineAttributesArray.length > 1)
            customerQuestionType.setCategoryId(Integer.parseInt(lineAttributesArray[1]));
        if (lineAttributesArray.length > 2)
            customerQuestionType.setSubCategoryId(Integer.parseInt(lineAttributesArray[2]));
        return customerQuestionType;
    }
}
