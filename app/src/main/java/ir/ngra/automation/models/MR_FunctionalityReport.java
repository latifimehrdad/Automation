package ir.ngra.automation.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MR_FunctionalityReport extends MR_Primary {

    @SerializedName("result")
    private List<MD_FunctionalityReport> result;

    public MR_FunctionalityReport(ArrayList<MD_Message> messages, List<MD_FunctionalityReport> result) {
        super(messages);
        this.result = result;
    }


    public List<MD_FunctionalityReport> getResult() {
        return result;
    }

    public void setResult(List<MD_FunctionalityReport> result) {
        this.result = result;
    }
}
