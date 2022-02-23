package model.data;

import model.data.category.QuestionType;
import model.data.category.ServiceType;

import java.time.LocalDate;

/**
 * The class represents the information of customer support via email.
 * It records reply waiting time, type of question, category, and service.
 */
public class CustomerData extends Data {
    private int replyTimeInMinutes;
    private LocalDate customerDate;

    public CustomerData(QuestionType questionType, ServiceType serviceType, boolean firstResponse, int replyTimeInMinutes, LocalDate customerDate) {
        super(questionType, serviceType, firstResponse);
        this.replyTimeInMinutes = replyTimeInMinutes;
        this.customerDate = customerDate;
    }


    public LocalDate getCustomerDate() {
        return customerDate;
    }

    public void setCustomerDate(LocalDate customerDate) {
        this.customerDate = customerDate;
    }

    public int getReplyTimeInMinutes() {
        return replyTimeInMinutes;
    }

    public void setReplyTimeInMinutes(int replyTimeInMinutes) {
        this.replyTimeInMinutes = replyTimeInMinutes;
    }
}
