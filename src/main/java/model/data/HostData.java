package model.data;

import model.data.category.CustomerQuestionType;
import model.data.category.CustomerServiceType;
import model.date.FromToDate;

public class HostData {
    private CustomerServiceType customerServiceType;
    private CustomerQuestionType customerQuestionType;
    private boolean isFirstResponse;
    private FromToDate fromToDate;

    public CustomerServiceType getCustomerServiceType() {
        return customerServiceType;
    }

    public void setCustomerServiceType(CustomerServiceType customerServiceType) {
        this.customerServiceType = customerServiceType;
    }

    public CustomerQuestionType getCustomerQuestionType() {
        return customerQuestionType;
    }

    public void setCustomerQuestionType(CustomerQuestionType customerQuestionType) {
        this.customerQuestionType = customerQuestionType;
    }

    public boolean isFirstResponse() {
        return isFirstResponse;
    }

    public void setFirstResponse(boolean firstResponse) {
        isFirstResponse = firstResponse;
    }

    public FromToDate getFromToDate() {
        return fromToDate;
    }

    public void setFromToDate(FromToDate fromToDate) {
        this.fromToDate = fromToDate;
    }
}
