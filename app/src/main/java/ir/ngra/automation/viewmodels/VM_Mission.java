package ir.ngra.automation.viewmodels;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import ir.mlcode.latifiarchitecturelibrary.viewmodels.VM_Latifi;
import ir.ngra.automation.R;
import ir.ngra.automation.models.MD_Mission;
import ir.ngra.automation.models.MD_WorkVacation;
import ir.ngra.automation.models.MR_Mission;
import ir.ngra.automation.models.MR_WorkVacation;
import ir.ngra.automation.utility.AttendanceType;
import ir.ngra.automation.utility.ObservableActions;
import ir.ngra.automation.views.application.AutomationApp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VM_Mission extends VM_Latifi {


    private List<MD_Mission> md_missionsList;


    //______________________________________________________________________________________________ VM_Mission
    public VM_Mission(Activity context) {
        setContext(context);
    }
    //______________________________________________________________________________________________ VM_Mission


    //______________________________________________________________________________________________ getMission
    public void getMission() {

        String authorization = getAuthorizationTokenFromSharedPreferences(R.string.ML_SharePreferences,
                R.string.ML_AccessToken);

        setPrimaryCall(AutomationApp.getAutomationApp(getContext())
                .getRetrofitApiInterface()
                .getRequestsMission(AttendanceType.Mission, authorization));

        if (getPrimaryCall() == null)
            return;

        getPrimaryCall().enqueue(new Callback<MR_Mission>() {
            @Override
            public void onResponse(Call<MR_Mission> call, Response<MR_Mission> response) {
                setResponseMessage(checkResponse(response, true));
                if (getResponseMessage() == null) {
                    md_missionsList = response.body().getResult();
                    sendActionToObservable(ObservableActions.getMissionList);
                } else
                    onFailureRequest();
            }

            @Override
            public void onFailure(Call<MR_Mission> call, Throwable t) {
                onFailureRequest();
            }
        });

    }
    //______________________________________________________________________________________________ getMission



    //______________________________________________________________________________________________ getMd_MissionList
    public List<MD_Mission> getMd_MissionList() {
        if (md_missionsList == null)
            md_missionsList = new ArrayList<>();
        return md_missionsList;
    }
    //______________________________________________________________________________________________ getMd_MissionList



}
