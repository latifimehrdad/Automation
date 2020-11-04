package ir.ngra.automation.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MR_Mission extends MR_Primary {

    @SerializedName("result")
    private List<MD_Mission> result;

    public MR_Mission(ArrayList<MD_Message> messages, List<MD_Mission> result) {
        super(messages);
        this.result = result;
    }


    public List<MD_Mission> getResult() {
        return result;
    }

    public void setResult(List<MD_Mission> result) {
        this.result = result;
    }
}
