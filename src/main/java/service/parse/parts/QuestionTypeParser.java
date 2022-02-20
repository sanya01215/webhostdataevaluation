package service.parse.parts;

import model.data.category.CustomerQuestionType;
import service.Service;

/**
 * The class provides processing string, that are QUESTION_TYPE part of input line ,
 * and returns parsed {@link CustomerQuestionType} from it.
 */
public class QuestionTypeParser implements Service {
    public CustomerQuestionType parseQuestionType(String questionTypePart) {
        return parseCustomerQuestionType(questionTypePart);
    }

    private CustomerQuestionType parseCustomerQuestionType(String lineAttr) {
        String[] lineAttributesArray = lineAttr.split("\\.");
        CustomerQuestionType customerQuestionType = new CustomerQuestionType();
        //if all types (*) selected, let's specify it by -1 value for processing it in queryHandler
        if (lineAttributesArray[0].equals("*")) {
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