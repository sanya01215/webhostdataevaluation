package model.data.category;

import java.util.Objects;

public class CustomerQuestionType {
    private int questionTypeId;
    private int categoryId;
    private int subCategoryId;

    public int getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(int questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerQuestionType that = (CustomerQuestionType) o;
        return questionTypeId == that.questionTypeId && categoryId == that.categoryId && subCategoryId == that.subCategoryId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionTypeId, categoryId, subCategoryId);
    }
}
