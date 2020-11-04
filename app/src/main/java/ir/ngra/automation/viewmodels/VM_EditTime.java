package ir.ngra.automation.viewmodels;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import ir.mlcode.latifiarchitecturelibrary.viewmodels.VM_Latifi;
import ir.ngra.automation.R;
import ir.ngra.automation.models.MD_EditTime;
import ir.ngra.automation.models.MR_EditTime;
import ir.ngra.automation.utility.ObservableActions;
import ir.ngra.automation.views.application.AutomationApp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VM_EditTime extends VM_Latifi {


    private List<MD_EditTime> md_editTimeList;


    //______________________________________________________________________________________________ VM_Mission
    public VM_EditTime(Activity context) {
        setContext(context);
    }
    //______________________________________________________________________________________________ VM_Mission


    //______________________________________________________________________________________________ getEditTime
    public void getEditTime(Byte attendanceType) {

        String authorization = getAuthorizationTokenFromSharedPreferences(R.string.ML_SharePreferences,
                R.string.ML_AccessToken);

        setPrimaryCall(AutomationApp.getAutomationApp(getContext())
                .getRetrofitApiInterface()
                .getRequestsEditTime(attendanceType, authorization));

        if (getPrimaryCall() == null)
            return;

        getPrimaryCall().enqueue(new Callback<MR_EditTime>() {
            @Override
            public void onResponse(Call<MR_EditTime> call, Response<MR_EditTime> response) {
                setResponseMessage(checkResponse(response, true));
                if (getResponseMessage() == null) {
                    md_editTimeList = response.body().getResult();
                    sendActionToObservable(ObservableActions.getMissionList);
                } else
                    onFailureRequest();
            }

            @Override
            public void onFailure(Call<MR_EditTime> call, Throwable t) {
                onFailureRequest();
            }
        });

    }
    //______________________________________________________________________________________________ getEditTime



    //______________________________________________________________________________________________ getMd_EditTimeList
    public List<MD_EditTime> getMd_EditTimeList() {
        if (md_editTimeList == null)
            md_editTimeList = new ArrayList<>();
        return md_editTimeList;
    }
    //______________________________________________________________________________________________ getMd_EditTimeList



}