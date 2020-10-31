package ir.ngra.automation.viewmodels;

import android.app.Activity;

import ir.mlcode.latifiarchitecturelibrary.viewmodels.VM_Latifi;
import ir.ngra.automation.R;
import ir.ngra.automation.models.MD_SettingInfo;
import ir.ngra.automation.models.MD_TodayEntrance;
import ir.ngra.automation.models.MR_TodayEntrance;
import ir.ngra.automation.utility.ObservableActions;
import ir.ngra.automation.views.application.AutomationApp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VM_Home extends VM_Latifi {


    private MD_TodayEntrance md_todayEntrance;

    //______________________________________________________________________________________________ VM_Home
    public VM_Home(Activity context) {
        setContext(context);
    }
    //______________________________________________________________________________________________ VM_Home



    //______________________________________________________________________________________________ getTodayEntrance
    public void getTodayEntrance() {

        String authorization = getAuthorizationTokenFromSharedPreferences(R.string.ML_SharePreferences,
                R.string.ML_AccessToken);

        setPrimaryCall(AutomationApp.getAutomationApp(getContext())
                .getRetrofitApiInterface()
                .getTodayEntrance(authorization));

        if (getPrimaryCall() == null)
            return;

        getPrimaryCall().enqueue(new Callback<MR_TodayEntrance>() {
            @Override
            public void onResponse(Call<MR_TodayEntrance> call, Response<MR_TodayEntrance> response) {
                setResponseMessage(checkResponse(response, true));
                if (getResponseMessage() == null) {
                    md_todayEntrance = response.body().getResult();
                    notifyChange();
                } else
                    onFailureRequest();
            }

            @Override
            public void onFailure(Call<MR_TodayEntrance> call, Throwable t) {
                onFailureRequest();
            }
        });
    }
    //______________________________________________________________________________________________ getTodayEntrance



    //______________________________________________________________________________________________ getMd_todayEntrance
    public MD_TodayEntrance getMd_todayEntrance() {
        if (md_todayEntrance == null)
            md_todayEntrance = new MD_TodayEntrance(null, null);
        return md_todayEntrance;
    }
    //______________________________________________________________________________________________ getMd_todayEntrance


}
