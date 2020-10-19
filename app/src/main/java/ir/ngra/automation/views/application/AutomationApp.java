package ir.ngra.automation.views.application;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;
import ir.mlcode.latifiarchitecturelibrary.application.APP_Latifi;
import ir.mlcode.latifiarchitecturelibrary.customs.ML_Button;
import ir.mlcode.latifiarchitecturelibrary.customs.ML_EditText;
import ir.ngra.automation.R;
import ir.ngra.automation.models.MD_SettingInfo;
import ir.ngra.automation.models.MD_SpinnerItem;
import ir.ngra.automation.models.MD_Token;
import ir.ngra.automation.utility.RetrofitApiInterface;
import ir.ngra.automation.views.customs.searchspinner.MLSpinnerDialog;

public class AutomationApp extends APP_Latifi {

    public static String Host = "http://2.144.243.200:70";
    public static String client_id_value = "vRIzEFYjpzYwHHSUbx/ODg==";
    public static String client_secret_value = "n5r+sej/lFv7xVhM9F7+kOG9yI64d/JIkGzl0NvgwMM=";
    public static String grant_type_value = "client_credentials";
    //    public static String grant_type_password = "password";
    public static String grant_type_value_Refresh_Token = "refresh_token";
    public static String grant_type_value_Login_Code = "login_code";


    private Context context;
    private RetrofitApiInterface retrofitApiInterface;


    //______________________________________________________________________________________________ onCreate
    @Override
    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();
        setContext(context);

        configurationRetrofit();
        //configurationCalligraphy();
    }
    //______________________________________________________________________________________________ onCreate


    //______________________________________________________________________________________________ configurationCalligraphy
    private void configurationCalligraphy() {
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("font/vazir_light.ttf")
                                .setFontAttrId(ir.mlcode.latifiarchitecturelibrary.R.attr.fontPath)
                                .build()))
                .build());

    }
    //______________________________________________________________________________________________ configurationCalligraphy


    //______________________________________________________________________________________________ configurationRetrofit
    private void configurationRetrofit() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        setHost(Host,gson);
        retrofitApiInterface = getRetrofitComponent().getRetrofit().create(RetrofitApiInterface.class);
    }
    //______________________________________________________________________________________________ configurationRetrofit


    //______________________________________________________________________________________________ getAutomationApp
    public static AutomationApp getAutomationApp(Context context) {
        return (AutomationApp) context.getApplicationContext();
    }
    //______________________________________________________________________________________________ getAutomationApp


    //______________________________________________________________________________________________ getRetrofitApiInterface
    public RetrofitApiInterface getRetrofitApiInterface() {
        return retrofitApiInterface;
    }
    //______________________________________________________________________________________________ getRetrofitApiInterface


    //______________________________________________________________________________________________ saveToken
    public boolean saveToken(MD_Token MDToken) {

        SharedPreferences.Editor token = context
                .getSharedPreferences(context.getString(R.string.ML_SharePreferences), 0)
                .edit();

        token.putString(context.getString(R.string.ML_AccessToken), MDToken.getAccess_token());
        token.putString(context.getString(R.string.ML_TokenType), MDToken.getToken_type());
        token.putInt(context.getString(R.string.ML_ExpireSin), MDToken.getExpires_in());
        token.putString(context.getString(R.string.ML_PhoneNumber), MDToken.getPhoneNumber());
        token.putString(context.getString(R.string.ML_ClientId), MDToken.getClient_id());
        token.putString(context.getString(R.string.ML_Issued), MDToken.getIssued());
        token.putString(context.getString(R.string.ML_Expires), MDToken.getExpires());
        token.putString(context.getString(R.string.ML_RefreshToken), MDToken.getRefresh_token());
        token.apply();
        return true;

    }
    //______________________________________________________________________________________________ saveToken


    //______________________________________________________________________________________________ saveProfile
    public boolean saveProfile(
            Context context,
            MD_SettingInfo.ModelProfileSetting profile) {
        SharedPreferences.Editor token = context
                .getSharedPreferences(context.getString(R.string.ML_SharePreferences), 0)
                .edit();
        token.putString(context.getString(R.string.ML_Name), profile.getName());
        token.putString(context.getString(R.string.ML_lastName), profile.getLastName());
        token.putInt(context.getString(R.string.ML_Gender), profile.getGender());
        token.apply();
        return true;
    }
    //______________________________________________________________________________________________ saveProfile


    //______________________________________________________________________________________________ logOut
    public boolean logOut(Context context) {
        SharedPreferences.Editor token =
                context.getSharedPreferences(context.getString(R.string.ML_SharePreferences), 0).edit();

        token.putString(context.getString(R.string.ML_AccessToken), null);
        token.putString(context.getString(R.string.ML_TokenType), null);
        token.putInt(context.getString(R.string.ML_ExpireSin), 0);
        token.putString(context.getString(R.string.ML_PhoneNumber), null);
        token.putString(context.getString(R.string.ML_ClientId), null);
        token.putString(context.getString(R.string.ML_Issued), null);
        token.putString(context.getString(R.string.ML_Expires), null);
        token.apply();
        return true;
    }
    //______________________________________________________________________________________________ logOut


    //______________________________________________________________________________________________ getUserName
    public String getUserName() {
        SharedPreferences share = context.getSharedPreferences(context.getString(R.string.ML_SharePreferences), 0);
        if (share == null)
            return null;
        else {
            String name = share.getString(context.getResources().getString(R.string.ML_Name), "");
            String last = share.getString(context.getResources().getString(R.string.ML_lastName), "");
            if (name.equalsIgnoreCase("") && last.equalsIgnoreCase(""))
                return null;
            else
                return name + " " + last;
        }
    }
    //______________________________________________________________________________________________ getUserName


    //______________________________________________________________________________________________ chooseDate
    public void chooseDate(Activity activity, ML_EditText ml_editText, String type) {

        PersianDatePickerDialog pickerDialog = new PersianDatePickerDialog(activity);
        pickerDialog.setListener(new Listener() {
            @Override
            public void onDateSelected(PersianCalendar persianCalendar) {
                StringBuilder sb = new StringBuilder();
                sb.append(persianCalendar.getPersianYear());
                sb.append("/");
                sb.append(String.format("%02d", persianCalendar.getPersianMonth()));
                sb.append("/");
                sb.append(String.format("%02d", persianCalendar.getPersianDay()));
                String text = type + System.getProperty("line.separator") + sb.toString();
                ml_editText.setText(text);
                ml_editText.removeError();
            }

            @Override
            public void onDismissed() {

            }
        });
        pickerDialog.show();
    }
    //______________________________________________________________________________________________ chooseDate



    //______________________________________________________________________________________________ chooseTime
    public void chooseTime(Activity activity, ML_EditText ml_editText, String title, String type) {
        Dialog dialog = new Dialog(activity);
        dialog.setCancelable(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_time_picker);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = dialog.getWindow();
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);

        TimePicker timePicker = dialog.findViewById(R.id.timePicker);

        TextView textViewTitle = dialog.findViewById(R.id.textViewTitle);
        textViewTitle.setText(title);

        ML_Button ml_ButtonCancel = dialog.findViewById(R.id.ml_ButtonCancel);

        ml_ButtonCancel.setOnClickListener(v -> dialog.dismiss());

        ML_Button ml_ButtonChoose = dialog.findViewById(R.id.ml_ButtonChoose);

        ml_ButtonChoose.setOnClickListener(v -> {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%02d", timePicker.getCurrentHour()));
            sb.append(":");
            sb.append(String.format("%02d", timePicker.getCurrentMinute()));
            String text = type + System.getProperty("line.separator") + sb.toString();
            ml_editText.setText(text);
            ml_editText.removeError();
            dialog.dismiss();
        });

        dialog.show();
    }
    //______________________________________________________________________________________________ chooseTime



    //______________________________________________________________________________________________ showSpinner
    public void showSpinner(Activity activity, ML_EditText ml_editText, ArrayList<MD_SpinnerItem> items, String titleSearch, String type) {
        MLSpinnerDialog spinner = new MLSpinnerDialog(
                activity,
                items,
                titleSearch,
                R.style.DialogAnimations_SmileWindow,
                getResources().getString(R.string.Ignore));

        spinner.setCancellable(true); // for cancellable
        spinner.setShowKeyboard(false);// for open keyboard by default
        spinner.bindOnSpinnerListener((item, position) -> {
            String text = type + System.getProperty("line.separator") + item;
            ml_editText.setAdditionalValue(position);
            ml_editText.setText(text);
            ml_editText.removeError();
        });
        spinner.showSpinnerDialog();
    }
    //______________________________________________________________________________________________ showSpinner

}
