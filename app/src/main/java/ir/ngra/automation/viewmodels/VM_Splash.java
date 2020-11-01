package ir.ngra.automation.viewmodels;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;

import ir.mlcode.latifiarchitecturelibrary.utility.StaticValues;
import ir.mlcode.latifiarchitecturelibrary.viewmodels.VM_Latifi;
import ir.ngra.automation.R;
import ir.ngra.automation.models.MD_Hi;
import ir.ngra.automation.models.MD_SettingInfo;
import ir.ngra.automation.models.MD_Token;
import ir.ngra.automation.models.MR_Hi;
import ir.ngra.automation.utility.ObservableActions;
import ir.ngra.automation.views.application.AutomationApp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VM_Splash extends VM_Primary {

    private MD_Hi md_hi;

    //______________________________________________________________________________________________ VM_Splash
    public VM_Splash(Activity context) {
        setContext(context);
    }
    //______________________________________________________________________________________________ VM_Splash



    //______________________________________________________________________________________________ callHI
    public void callHI() {
        cancelRequest();
        Handler handler = new Handler();
        handler.postDelayed(() -> callHiService(), 3000);
    }
    //______________________________________________________________________________________________ callHI




    //______________________________________________________________________________________________ callHiService
    public void callHiService() {

        setPrimaryCall(AutomationApp.getAutomationApp(getContext())
                .getRetrofitApiInterface()
                .getHi(AutomationApp.client_id_value,
                        AutomationApp.client_secret_value,
                        getContext().getResources().getString(R.string.UpdateAppName)));

        if (getPrimaryCall() == null)
            return;

        getPrimaryCall().enqueue(new Callback<MR_Hi>() {
            @Override
            public void onResponse(Call<MR_Hi> call, Response<MR_Hi> response) {
                setResponseMessage(checkResponse(response, true));
                if (getResponseMessage() == null) {
                    md_hi = response.body().getResult();
                    if (md_hi == null) {
                        setResponseMessage(getResponseMessages(response.body().getMessages()));
                        sendActionToObservable(StaticValues.ML_ResponseError);
                    } else {
                        setResponseMessage("");
                        checkUpdate();
                    }
                } else {
                    refreshToken();
                }
            }

            @Override
            public void onFailure(Call<MR_Hi> call, Throwable t) {
                onFailureRequest();
            }
        });
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


        if (versionName < lastVersion) {
            setResponseMessage(getContext().getResources().getString(R.string.newVersionIsAvailable));
            sendActionToObservable(ObservableActions.gotoUpdate);
        } else
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


        setPrimaryCall(AutomationApp.getAutomationApp(getContext())
                .getRetrofitApiInterface()
                .getToken(AutomationApp.client_id_value,
                        AutomationApp.client_secret_value,
                        AutomationApp.grant_type_value));

        if (getPrimaryCall() == null)
            return;

        getPrimaryCall().enqueue(new Callback<MD_Token>() {
            @Override
            public void onResponse(Call<MD_Token> call, Response<MD_Token> response) {
                setResponseMessage(checkResponse(response, true));
                if (getResponseMessage() == null) {
                    if (AutomationApp.getAutomationApp(getContext()).saveToken(response.body()))
                        sendActionToObservable(ObservableActions.goToLogin);
                } else
                    sendActionToObservable(StaticValues.ML_ResponseError);
            }

            @Override
            public void onFailure(Call<MD_Token> call, Throwable t) {
                onFailureRequest();
            }
        });
    }
    //______________________________________________________________________________________________ getTokenFromServer





    //______________________________________________________________________________________________ getLoginInformation
    public void getLoginInformation() {

        String authorization = getAuthorizationTokenFromSharedPreferences(R.string.ML_SharePreferences,
                R.string.ML_AccessToken);

        setPrimaryCall(AutomationApp.getAutomationApp(getContext())
                .getRetrofitApiInterface()
                .getSettingInfo(
                        authorization));

        if (getPrimaryCall() == null)
            return;

        getPrimaryCall().enqueue(new Callback<MD_SettingInfo>() {
            @Override
            public void onResponse(Call<MD_SettingInfo> call, Response<MD_SettingInfo> response) {
                setResponseMessage(checkResponse(response, true));
                if (getResponseMessage() == null) {
                    MD_SettingInfo.ModelProfileSetting profile = response.body().getResult();
                    if (profile != null) {
                        if (AutomationApp.getAutomationApp(getContext()).saveProfile(getContext(), profile))
                            setResponseMessage("خوش آمدید");
                            sendActionToObservable(ObservableActions.gotoHome);
                    } else {
                        if (AutomationApp.getAutomationApp(getContext()).logOut(getContext()))
                            getTokenFromServer();
                    }
                } else
                    refreshToken();
            }

            @Override
            public void onFailure(Call<MD_SettingInfo> call, Throwable t) {
                onFailureRequest();
            }
        });
    }
    //______________________________________________________________________________________________ getLoginInformation




    //______________________________________________________________________________________________ refreshToken
    public void refreshToken() {

        String refresh_token = getRefreshTokenFromSharedPreferences(R.string.ML_SharePreferences,
                R.string.ML_RefreshToken);

        setPrimaryCall(AutomationApp.getAutomationApp(getContext())
                .getRetrofitApiInterface()
                .getRefreshToken(
                        AutomationApp.client_id_value,
                        AutomationApp.client_secret_value,
                        AutomationApp.grant_type_value_Refresh_Token,
                        refresh_token));

        if (getPrimaryCall() == null)
            return;

        getPrimaryCall().enqueue(new Callback<MD_Token>() {
            @Override
            public void onResponse(Call<MD_Token> call, Response<MD_Token> response) {
                setResponseMessage(checkResponse(response, true));
                if (getResponseMessage() == null) {
                    if (AutomationApp.getAutomationApp(getContext()).saveToken(response.body()))
                        sendActionToObservable(ObservableActions.gotoHome);
                } else {
                    AutomationApp.getAutomationApp(getContext()).logOut(getContext());
                    getTokenFromServer();
                }
            }

            @Override
            public void onFailure(Call<MD_Token> call, Throwable t) {
                onFailureRequest();
            }
        });
    }
    //______________________________________________________________________________________________ refreshToken



    //______________________________________________________________________________________________ getMd_hi
    public MD_Hi getMd_hi() {
        return md_hi;
    }
    //______________________________________________________________________________________________ getMd_hi


}
