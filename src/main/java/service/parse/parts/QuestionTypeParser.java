package service.parse.parts;

import model.data.category.QuestionType;
import service.Service;

import static stringconst.StringConst.ALL_SYMBOL;
import static stringconst.StringConst.SUB_ARG_DELIMITER;

/**
 * The class provides processing string, that are QUESTION_TYPE part of input line ,
 * and returns parsed {@link QuestionType} from it.
 */
public class QuestionTypeParser implements Service {
    public QuestionType parseQuestionType(String questionTypePart) {
        return parseCustomerQuestionType(questionTypePart);
    }

    private QuestionType parseCustomerQuestionType(String lineAttr) {
        String[] lineAttributesArray = lineAttr.split(SUB_ARG_DELIMITER);
        QuestionType questionType = new QuestionType();
        //if all types (*) selected, let's specify it by -1 value for processing it in queryHandler
        if (lineAttributesArray[0].equals(ALL_SYMBOL)) {
            questionType.setQuestionTypeId(-1);
            return questionType;
        }
        questionType.setQuestionTypeId(Integer.parseInt(lineAttributesArray[0]));
        if (lineAttributesArray.length > 1)
            questionType.setCategoryId(Integer.parseInt(lineAttributesArray[1]));
        if (lineAttributesArray.length > 2)
            questionType.setSubCategoryId(Integer.parseInt(lineAttributesArray[2]));
        return questionType;
    }

}
