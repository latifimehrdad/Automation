package ir.ngra.automation.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MR_TodayEntrance extends MR_Primary {


    @SerializedName("result")
    private MD_TodayEntrance result;

    public MR_TodayEntrance(ArrayList<MD_Message> messages, MD_TodayEntrance result) {
        super(messages);
        this.result = result;
    }

    public MD_TodayEntrance getResult() {
        return result;
    }

    public void setResult(MD_TodayEntrance result) {
        this.result = result;
    }
}
