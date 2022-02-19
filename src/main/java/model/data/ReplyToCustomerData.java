package model.data;


public class ReplyToCustomerData extends HostData {
    private int replyTimeInMinutes;

    public ReplyToCustomerData() {
    }

    public int getReplyTimeInMinutes() {
        return replyTimeInMinutes;
    }

    public void setReplyTimeInMinutes(int replyTimeInMinutes) {
        this.replyTimeInMinutes = replyTimeInMinutes;
    }
}
