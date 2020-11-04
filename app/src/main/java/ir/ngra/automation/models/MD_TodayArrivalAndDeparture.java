package ir.ngra.automation.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class MD_TodayArrivalAndDeparture {


    @SerializedName("arrival")
    private Date arrival;

    @SerializedName("departure")
    private Date departure;

    public MD_TodayArrivalAndDeparture(Date arrival, Date departure) {
        this.arrival = arrival;
        this.departure = departure;
    }


    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }
}
