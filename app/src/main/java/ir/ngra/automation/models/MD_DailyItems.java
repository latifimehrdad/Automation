package ir.ngra.automation.models;

import com.google.gson.annotations.SerializedName;

public class MD_DailyItems {


    @SerializedName("arrival")
    private String arrival;

    @SerializedName("exit")
    private String exit;

    public MD_DailyItems(String arrival, String exit) {
        this.arrival = arrival;
        this.exit = exit;
    }


    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getExit() {
        return exit;
    }

    public void setExit(String exit) {
        this.exit = exit;
    }
}
