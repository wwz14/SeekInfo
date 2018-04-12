/**
 * Created by alice on 18/4/11.
 */
public class FactModel {
    /**
     * 事件发生时间
     */
    private String date;
    /**
     * 发生原因
     */
    private String reason;
    /**
     * 具体过程
     */
    private String details;

    public FactModel(String date, String reason, String details) {
        this.date = date;
        this.reason = reason;
        this.details = details;
    }

    public FactModel() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String dateInfo) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
