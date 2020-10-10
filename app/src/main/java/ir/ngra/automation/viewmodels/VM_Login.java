package ir.ngra.automation.viewmodels;

import android.app.Activity;
import android.os.Handler;

import ir.mlcode.latifiarchitecturelibrary.models.MD_ResponsePrimary;
import ir.mlcode.latifiarchitecturelibrary.utility.StaticValues;
import ir.mlcode.latifiarchitecturelibrary.viewmodels.VM_Latifi;
import ir.ngra.automation.R;
import ir.ngra.automation.utility.ObservableActions;
import ir.ngra.automation.views.application.AutomationApp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VM_Login extends VM_Latifi {


    private String phoneNumber;

    //______________________________________________________________________________________________ VM_Login
    public VM_Login(Activity context) {
        setContext(context);
    }
    //______________________________________________________________________________________________ VM_Login



    //______________________________________________________________________________________________ getLoginCode
    public void getLoginCode() {

        setPhoneNumber(getUtility().persianToEnglish(getPhoneNumber()));

        String authorization = getAuthorizationTokenFromSharedPreferences(R.string.ML_SharePreferences,
                R.string.ML_AccessToken);

        setPrimaryCall(AutomationApp.getAutomationApp(getContext())
                .getRetrofitApiInterface()
                .LoginCode(AutomationApp.client_id_value,
                        AutomationApp.client_secret_value,
                        getPhoneNumber(),
                        authorization));

        if (getPrimaryCall() == null)
            return;

        getPrimaryCall().enqueue(new Callback<MD_ResponsePrimary>() {
            @Override
            public void onResponse(Call<MD_ResponsePrimary> call, Response<MD_ResponsePrimary> response) {
                setResponseMessage(checkResponse(response, false));
                if (getResponseMessage() == null) {
                    setResponseMessage(getResponseMessage(response.body()));
                    sendActionToObservable(ObservableActions.gotoVerify);
                } else {
                    sendActionToObservable(StaticValues.ML_ResponseError);
                }
            }

            @Override
            public void onFailure(Call<MD_ResponsePrimary> call, Throwable t) {
                onFailureRequest();
            }
        });

    }
    //______________________________________________________________________________________________ getLoginCode




    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
