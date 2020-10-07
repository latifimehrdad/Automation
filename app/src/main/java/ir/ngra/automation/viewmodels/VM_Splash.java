package ir.ngra.automation.viewmodels;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;

import ir.mlcode.latifiarchitecturelibrary.viewmodels.VM_Latifi;
import ir.ngra.automation.R;
import ir.ngra.automation.models.MD_Hi;
import ir.ngra.automation.utility.ObservableActions;

public class VM_Splash extends VM_Latifi {

    private MD_Hi md_hi;

    //______________________________________________________________________________________________ VM_Splash
    public VM_Splash(Activity context) {
        setContext(context);
    }
    //______________________________________________________________________________________________ VM_Splash



    //______________________________________________________________________________________________ callHI
    public void callHI() {
//        callHiService();
        checkToken();
    }
    //______________________________________________________________________________________________ callHI




    //______________________________________________________________________________________________ callHiService
    public void callHiService() {

        checkUpdate();

/*        RetrofitComponent retrofitComponent =
                ApplicationWMS
                        .getApplicationWMS(getContext())
                        .getRetrofitComponent();


        setPrimaryCall(retrofitComponent
                .getRetrofitApiInterface()
                .getHi(RetrofitApis.client_id_value,
                        RetrofitApis.client_secret_value,
                        getContext().getResources().getString(R.string.UpdateAppName)));

        if (getPrimaryCall() == null)
            return;

        getPrimaryCall().enqueue(new Callback<MR_Hi>() {
            @Override
            public void onResponse(Call<MR_Hi> call, Response<MR_Hi> response) {
                setResponseMessage(checkResponse(response, true));
                if (getResponseMessage() == null) {
                    md_hi = response.body().getResult();
                    setResponseMessage("");
                    checkUpdate();
                } else
                    sendActionToObservable(StaticValues.ML_ResponseError);
            }

            @Override
            public void onFailure(Call<MR_Hi> call, Throwable t) {
                onFailureRequest();
            }
        });*/
    }
    //______________________________________________________________________________________________ callHiService




    //______________________________________________________________________________________________ checkUpdate
    private void checkUpdate() {
        PackageInfo pInfo;
        float versionName = 0;
        try {
            pInfo = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0);
            versionName = Float.valueOf(pInfo.versionName);
        } catch (PackageManager.NameNotFoundException ignored) {
        }

        String v = md_hi.getVersion();
        v = v.replaceAll("v", "");
        float lastVersion = Float.valueOf(v);


        if (versionName < lastVersion)
            sendActionToObservable(ObservableActions.gotoUpdate);
        else
            checkToken();
    }
    //______________________________________________________________________________________________ checkUpdate




    //______________________________________________________________________________________________ checkToken
    public void checkToken() {

        SharedPreferences prefs = getContext().getSharedPreferences(getContext().getString(R.string.ML_SharePreferences), 0);
        if (prefs == null) {
            getTokenFromServer();
        } else {
            String accessToken = prefs.getString(getContext().getString(R.string.ML_AccessToken), null);
            String expires = prefs.getString(getContext().getString(R.string.ML_Expires), null);
            String phoneNumber = prefs.getString(getContext().getString(R.string.ML_PhoneNumber), null);
            if ((accessToken == null) || (expires == null))
                getTokenFromServer();
            else {
                if (phoneNumber != null) {
                    getLoginInformation();
                } else
                    getTokenFromServer();
            }
        }
    }
    //______________________________________________________________________________________________ checkToken




    //______________________________________________________________________________________________ getTokenFromServer
    public void getTokenFromServer() {

        Handler handler = new Handler();
        handler.postDelayed(() -> sendActionToObservable(ObservableActions.goToLogin),3000);


/*        RetrofitComponent retrofitComponent = ApplicationWMS
                .getApplicationWMS(getContext())
                .getRetrofitComponent();

        setPrimaryCall(retrofitComponent
                .getRetrofitApiInterface()
                .getToken(
                        RetrofitApis.client_id_value,
                        RetrofitApis.client_secret_value,
                        RetrofitApis.grant_type_value));

        if (getPrimaryCall() == null)
            return;

        getPrimaryCall().enqueue(new Callback<MD_Token>() {
            @Override
            public void onResponse(Call<MD_Token> call, Response<MD_Token> response) {
                setResponseMessage(checkResponse(response, true));
                if (getResponseMessage() == null) {
                    md_Token = response.body();
                    if (getUtility().saveToken(getContext(), md_Token))
                        sendActionToObservable(StaticValues.ML_GotoLogin);
                } else
                    sendActionToObservable(StaticValues.ML_ResponseError);
            }

            @Override
            public void onFailure(Call<MD_Token> call, Throwable t) {
                onFailureRequest();
            }
        });*/
    }
    //______________________________________________________________________________________________ getTokenFromServer





    //______________________________________________________________________________________________ getLoginInformation
    public void getLoginInformation() {


        Handler handler = new Handler();
        handler.postDelayed(() -> sendActionToObservable(ObservableActions.goToLogin),3000);


/*        RetrofitComponent retrofitComponent =
                ApplicationWMS
                        .getApplicationWMS(getContext())
                        .getRetrofitComponent();

        String authorization = getAuthorizationTokenFromSharedPreferences();

        setPrimaryCall(retrofitComponent
                .getRetrofitApiInterface()
                .getSettingInfo(
                        authorization));

        if (getPrimaryCall() == null)
            return;

        getPrimaryCall().enqueue(new Callback<ModelSettingInfo>() {
            @Override
            public void onResponse(Call<ModelSettingInfo> call, Response<ModelSettingInfo> response) {
                setResponseMessage(checkResponse(response, true));
                if (getResponseMessage() == null) {
                    profile = response.body().getResult();
                    if (profile != null) {
                        if (getUtility().saveProfile(getContext(), profile))
                            sendActionToObservable(StaticValues.ML_GoToHome);
                    } else {
                        if (getUtility().logOut(getContext()))
                            getTokenFromServer();
                    }
                } else
                    refreshToken();
            }

            @Override
            public void onFailure(Call<ModelSettingInfo> call, Throwable t) {
                onFailureRequest();
            }
        });*/
    }
    //______________________________________________________________________________________________ getLoginInformation


    //______________________________________________________________________________________________ getMd_hi
    public MD_Hi getMd_hi() {
        return md_hi;
    }
    //______________________________________________________________________________________________ getMd_hi


}
