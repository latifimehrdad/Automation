package ir.ngra.automation.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MR_TodayArrivalAndDeparture extends MR_Primary {


    @SerializedName("result")
    private MD_TodayArrivalAndDeparture result;

    public MR_TodayArrivalAndDeparture(ArrayList<MD_Message> messages, MD_TodayArrivalAndDeparture result) {
        super(messages);
        this.result = result;
    }

    public MD_TodayArrivalAndDeparture getResult() {
        return result;
    }

    public void setResult(MD_TodayArrivalAndDeparture result) {
        this.result = result;
    }
}
