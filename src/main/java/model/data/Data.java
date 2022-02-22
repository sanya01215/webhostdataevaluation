package model.data;

import model.data.category.QuestionType;
import model.data.category.ServiceType;

public class Data {
    private QuestionType questionType;
    private ServiceType serviceType;
    private boolean firstResponse;

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public boolean isFirstResponse() {
        return firstResponse;
    }

    public void setFirstResponse(boolean firstResponse) {
        this.firstResponse = firstResponse;
    }

    public Data(QuestionType questionType, ServiceType serviceType, boolean firstResponse) {
        this.questionType = questionType;
        this.serviceType = serviceType;
        this.firstResponse = firstResponse;
    }
}
