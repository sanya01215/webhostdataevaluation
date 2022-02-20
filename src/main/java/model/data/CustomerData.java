package model.data;

/**
 * The class represents the information of customer support via email.
 * It records reply waiting time, type of question, category, and service.
 */
public class CustomerData extends IncomeData {
    private int replyTimeInMinutes;

    public CustomerData() {
    }

    public int getReplyTimeInMinutes() {
        return replyTimeInMinutes;
    }

    public void setReplyTimeInMinutes(int replyTimeInMinutes) {
        this.replyTimeInMinutes = replyTimeInMinutes;
    }
}
