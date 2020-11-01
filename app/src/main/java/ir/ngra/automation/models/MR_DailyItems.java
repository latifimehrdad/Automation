package ir.ngra.automation.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MR_DailyItems extends MR_Primary {


    @SerializedName("result")
    private MD_DailyItems result;

    public MR_DailyItems(ArrayList<MD_Message> messages, MD_DailyItems result) {
        super(messages);
        this.result = result;
    }

    public MD_DailyItems getResult() {
        return result;
    }

    public void setResult(MD_DailyItems result) {
        this.result = result;
    }
}
