package ir.ngra.automation.viewmodels;

import java.util.ArrayList;

import ir.mlcode.latifiarchitecturelibrary.viewmodels.VM_Latifi;
import ir.ngra.automation.models.MD_Message;


public class VM_Primary extends VM_Latifi {


    //______________________________________________________________________________________________ getResponseMessages
    public String getResponseMessages(ArrayList<MD_Message> md_messages) {
        StringBuilder result = new StringBuilder();
        if (md_messages != null && md_messages.size() > 0)
            for (MD_Message message : md_messages)
                result.append(message.getMessage()).append(System.getProperty("line.separator"));
        else
            result.append(getContext().getResources().getString(ir.mlcode.latifiarchitecturelibrary.R.string.NetworkError));

        return result.toString();
    }
    //______________________________________________________________________________________________ getResponseMessages




}
