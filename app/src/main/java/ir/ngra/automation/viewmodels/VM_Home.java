package ir.ngra.automation.viewmodels;

import android.app.Activity;

import ir.mlcode.latifiarchitecturelibrary.viewmodels.VM_Latifi;
import ir.ngra.automation.R;
import ir.ngra.automation.models.MD_TodayArrivalAndDeparture;
import ir.ngra.automation.models.MR_TodayArrivalAndDeparture;
import ir.ngra.automation.views.application.AutomationApp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VM_Home extends VM_Latifi {


    private MD_TodayArrivalAndDeparture md_todayArrivalAndDeparture;

    //______________________________________________________________________________________________ VM_Home
    public VM_Home(Activity context) {
        setContext(context);
    }
    //______________________________________________________________________________________________ VM_Home



    //______________________________________________________________________________________________ getTodayArrivalAndDeparture
    public void getTodayArrivalAndDeparture() {

        String authorization = getAuthorizationTokenFromSharedPreferences(R.string.ML_SharePreferences,
                R.string.ML_AccessToken);

        setPrimaryCall(AutomationApp.getAutomationApp(getContext())
                .getRetrofitApiInterface()
                .getTodayArrivalAndDeparture(authorization));

        if (getPrimaryCall() == null)
            return;

        getPrimaryCall().enqueue(new Callback<MR_TodayArrivalAndDeparture>() {
            @Override
            public void onResponse(Call<MR_TodayArrivalAndDeparture> call, Response<MR_TodayArrivalAndDeparture> response) {
                setResponseMessage(checkResponse(response, true));
                if (getResponseMessage() == null) {
                    md_todayArrivalAndDeparture = response.body().getResult();
                    notifyChange();
                } else
                    onFailureRequest();
            }

            @Override
            public void onFailure(Call<MR_TodayArrivalAndDeparture> call, Throwable t) {
                onFailureRequest();
            }
        });
    }
    //______________________________________________________________________________________________ getTodayArrivalAndDeparture



    //______________________________________________________________________________________________ getMd_todayEntrance
    public MD_TodayArrivalAndDeparture getMd_todayArrivalAndDeparture() {
        if (md_todayArrivalAndDeparture == null)
            md_todayArrivalAndDeparture = new MD_TodayArrivalAndDeparture(null, null);
        return md_todayArrivalAndDeparture;
    }
    //______________________________________________________________________________________________ getMd_todayEntrance


}
