package ir.ngra.automation.viewmodels;

import android.app.Activity;

import java.util.ArrayList;

import ir.ngra.automation.R;
import ir.ngra.automation.models.MD_SpinnerItem;
import ir.ngra.automation.models.MR_Primary;
import ir.ngra.automation.utility.ObservableActions;
import ir.ngra.automation.views.application.AutomationApp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VM_NewMission extends VM_Primary {


    private ArrayList<MD_SpinnerItem> workVacationTypeList;

    private ArrayList<MD_SpinnerItem> substituteList;

    private String description;

    private String fromDate;

    private String toDate;


    //______________________________________________________________________________________________ VM_NewMission
    public VM_NewMission(Activity context) {
        setContext(context);
    }
    //______________________________________________________________________________________________ VM_NewMission



    //______________________________________________________________________________________________ requestMission
    public void requestMission() {

        String authorization = getAuthorizationTokenFromSharedPreferences(R.string.ML_SharePreferences,
                R.string.ML_AccessToken);

        setPrimaryCall(AutomationApp.getAutomationApp(getContext())
                .getRetrofitApiInterface()
                .RequestMission(getFromDate(),getToDate(),getDescription(),authorization));

        if (getPrimaryCall() == null)
            return;

        getPrimaryCall().enqueue(new Callback<MR_Primary>() {
            @Override
            public void onResponse(Call<MR_Primary> call, Response<MR_Primary> response) {
                setResponseMessage(checkResponse(response, true));
                if (getResponseMessage() == null) {
                    setResponseMessage(getResponseMessages(response.body().getMessages()));
                    sendActionToObservable(ObservableActions.getWorkVacationList);
                } else
                    onFailureRequest();
            }

            @Override
            public void onFailure(Call<MR_Primary> call, Throwable t) {
                onFailureRequest();
            }
        });
    }
    //______________________________________________________________________________________________ requestMission





    //______________________________________________________________________________________________ getWorkVacationType
    public void getWorkVacationType() {
        workVacationTypeList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            workVacationTypeList.add(new MD_SpinnerItem(i, "item " + i, ""));
        }
        substituteList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            substituteList.add(new MD_SpinnerItem(i, "item " + i, ""));
        }
    }
    //______________________________________________________________________________________________ getWorkVacationType


    //______________________________________________________________________________________________ getWorkVacationTypeList
    public ArrayList<MD_SpinnerItem> getWorkVacationTypeList() {
        if (workVacationTypeList == null)
            workVacationTypeList = new ArrayList<>();

        return workVacationTypeList;
    }
    //______________________________________________________________________________________________ getWorkVacationTypeList


    //______________________________________________________________________________________________ getSubstituteList
    public ArrayList<MD_SpinnerItem> getSubstituteList() {
        if (substituteList == null)
            substituteList = new ArrayList<>();

        return substituteList;
    }
    //______________________________________________________________________________________________ getSubstituteList


    //______________________________________________________________________________________________ getSubstituteList
    public String getDescription() {
        return description;
    }
    //______________________________________________________________________________________________ getSubstituteList


    //______________________________________________________________________________________________ getSubstituteList
    public void setDescription(String description) {
        this.description = description;
    }
    //______________________________________________________________________________________________ getSubstituteList


    //______________________________________________________________________________________________ getFromDate
    public String getFromDate() {
/*        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd'T'HH:mm");
        Date from = null;
        try {
            from = format.parse(fromDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        return fromDate;
    }
    //______________________________________________________________________________________________ getFromDate


    //______________________________________________________________________________________________ setFromDate
    public void setFromDate(String fromDate, String fromTime) {

        this.fromDate = AutomationApp.getAutomationApp(getContext()).getUtilityComponent()
                .getApplicationUtility()
                .solarDateToGregorian(fromDate)
                .getDateString();

        this.fromDate = this.fromDate + "T" + fromTime;
    }
    //______________________________________________________________________________________________ setFromDate



    //______________________________________________________________________________________________ getToDate
    public String getToDate() {
/*        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd'T'HH:mm");
        Date to = null;
        try {
            to = format.parse(toDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        return toDate;
    }
    //______________________________________________________________________________________________ getToDate



    //______________________________________________________________________________________________ setToDate
    public void setToDate(String toDate, String toTime) {

        this.toDate = AutomationApp.getAutomationApp(getContext()).getUtilityComponent()
                .getApplicationUtility()
                .solarDateToGregorian(toDate)
                .getDateString();

        this.toDate = this.toDate + "T" + toTime;
    }
    //______________________________________________________________________________________________ setToDate

}