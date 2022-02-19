package model.data;

import java.time.LocalDate;

public class ReplyToCustomerData extends HostData {
    private LocalDate replyDate;
    private int replyTimeInMinutes;

    public ReplyToCustomerData() {
    }
    public LocalDate getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(LocalDate replyDate) {
        this.replyDate = replyDate;
    }

    public int getReplyTimeInMinutes() {
        return replyTimeInMinutes;
    }

    public void setReplyTimeInMinutes(int replyTimeInMinutes) {
        this.replyTimeInMinutes = replyTimeInMinutes;
    }
}
