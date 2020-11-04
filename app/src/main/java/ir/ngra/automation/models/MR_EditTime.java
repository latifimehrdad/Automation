package ir.ngra.automation.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MR_EditTime extends MR_Primary {

    @SerializedName("result")
    private List<MD_EditTime> result;

    public MR_EditTime(ArrayList<MD_Message> messages, List<MD_EditTime> result) {
        super(messages);
        this.result = result;
    }


    public List<MD_EditTime> getResult() {
        return result;
    }

    public void setResult(List<MD_EditTime> result) {
        this.result = result;
    }
}