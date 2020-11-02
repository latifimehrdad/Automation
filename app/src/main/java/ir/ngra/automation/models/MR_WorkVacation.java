package ir.ngra.automation.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MR_WorkVacation extends MR_Primary {

    @SerializedName("result")
    private List<MD_WorkVacation> result;

    public MR_WorkVacation(ArrayList<MD_Message> messages, List<MD_WorkVacation> result) {
        super(messages);
        this.result = result;
    }


    public List<MD_WorkVacation> getResult() {
        return result;
    }

    public void setResult(List<MD_WorkVacation> result) {
        this.result = result;
    }
}
