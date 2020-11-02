package ir.ngra.automation.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class MD_WorkVacation {


    @SerializedName("from")
    private Date from;

    @SerializedName("to")
    private Date to;

    @SerializedName("description")
    private String description;

    @SerializedName("state")
    private Byte state;

    @SerializedName("createDate")
    private Date createDate;


    public MD_WorkVacation(Date from, Date to, String description, Byte state, Date createDate) {
        this.from = from;
        this.to = to;
        this.description = description;
        this.state = state;
        this.createDate = createDate;
    }


    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
