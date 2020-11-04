package ir.ngra.automation.views.adapter;


import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ir.mlcode.latifiarchitecturelibrary.customs.ML_EditText;
import ir.mlcode.latifiarchitecturelibrary.utility.wave.LatifiWaveProgressView;
import ir.ngra.automation.R;
import ir.ngra.automation.models.MD_TodayArrivalAndDeparture;
import ir.ngra.automation.utility.AttendanceRequestState;
import ir.ngra.automation.views.application.AutomationApp;

public class BindingAdapters {


    //______________________________________________________________________________________________ setLeaveWave
    @BindingAdapter("bind:setLeaveWave")
    public static void setLeaveWave(LatifiWaveProgressView wave, int progress) {

        Resources resources = wave.getContext().getResources();
        wave.setProgress(progress);
        int max = wave.getMax();
        float degree = (progress * 100) / max;
        if (degree < 30) {
            wave.setBehindWaveColor(resources.getColor(R.color.ML_WaveTextColor));
            wave.setFrontWaveColor(resources.getColor(R.color.ML_WaveTextMin));
            wave.setBorderColor(resources.getColor(R.color.colorPrimaryDark));
            wave.setTextColor(resources.getColor(R.color.ML_WaveTextColor));
        } else if (degree > 29 && degree < 75) {
            wave.setBehindWaveColor(resources.getColor(R.color.mlWave2));
            wave.setFrontWaveColor(resources.getColor(R.color.mlWave));
            wave.setBorderColor(resources.getColor(R.color.ML_WaveTextColor));
            wave.setTextColor(resources.getColor(R.color.ML_WaveTextColor));
        } else if (degree > 74) {
            wave.setBehindWaveColor(resources.getColor(R.color.ML_WaveTextMaxLight));
            wave.setFrontWaveColor(resources.getColor(R.color.ML_WaveTextMaxDark));
            wave.setBorderColor(resources.getColor(R.color.ML_WaveTextMaxText));
            wave.setTextColor(resources.getColor(R.color.ML_WaveTextMaxText));
        }

    }
    //______________________________________________________________________________________________ setLeaveWave


    //______________________________________________________________________________________________ setState
    @BindingAdapter(value = "setState")
    public static void setState(ML_EditText ml_editText, Byte state) {
        String text = ml_editText.getContext().getResources().getString(R.string.state);
        Context context = ml_editText.getContext();

        if (state.equals(AttendanceRequestState.Pending)) {
            text = text + " : " + ml_editText.getContext().getResources().getString(R.string.pending);
            ml_editText.getTextView().setTextColor(context.getResources().getColor(R.color.ML_Error));
        } else if (state.equals(AttendanceRequestState.Canceled)) {
            text = text + " : " + ml_editText.getContext().getResources().getString(R.string.canceled);
            ml_editText.getTextView().setTextColor(context.getResources().getColor(R.color.colorPrimary));
        } else if (state.equals(AttendanceRequestState.Accepted)) {
            text = text + " : " + ml_editText.getContext().getResources().getString(R.string.accepted);
            ml_editText.getTextView().setTextColor(context.getResources().getColor(R.color.ML_WaveTextMin));
        }

        ml_editText.setText(text);
    }
    //______________________________________________________________________________________________ setState


    //______________________________________________________________________________________________ setTodayEntrance
    @BindingAdapter(value = "setTodayEntrance")
    public static void setTodayEntrance(TextView textView, MD_TodayArrivalAndDeparture md_todayArrivalAndDeparture) {

        String tag = textView.getTag().toString();
        Date arrival_d = md_todayArrivalAndDeparture.getArrival();
        Date departure_d = md_todayArrivalAndDeparture.getDeparture();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.US);

        String arrival = null;
        if (arrival_d != null)
            arrival = simpleDateFormat.format(arrival_d);


        String departure = null;
        if (departure_d != null)
            departure = simpleDateFormat.format(departure_d);


        switch (tag) {
            case "arrival_h1":
                if (arrival == null || arrival.length() < 5)
                    textView.setText("-");
                else
                    textView.setText(arrival.substring(0, 1));
                break;

            case "arrival_h2":
                if (arrival == null || arrival.length() < 5)
                    textView.setText("-");
                else
                    textView.setText(arrival.substring(1, 2));
                break;

            case "arrival_m1":
                if (arrival == null || arrival.length() < 5)
                    textView.setText("-");
                else
                    textView.setText(arrival.substring(3, 4));
                break;

            case "arrival_m2":
                if (arrival == null || arrival.length() < 5)
                    textView.setText("-");
                else
                    textView.setText(arrival.substring(4, 5));
                break;

            case "exit_h1":
                if (departure == null || departure.length() < 5)
                    textView.setText("-");
                else
                    textView.setText(departure.substring(0, 1));
                break;

            case "exit_h2":
                if (departure == null || departure.length() < 5)
                    textView.setText("-");
                else
                    textView.setText(departure.substring(1, 2));
                break;

            case "exit_m1":
                if (departure == null || departure.length() < 5)
                    textView.setText("-");
                else
                    textView.setText(departure.substring(3, 4));
                break;

            case "exit_m2":
                if (departure == null || departure.length() < 5)
                    textView.setText("-");
                else
                    textView.setText(departure.substring(4, 5));
                break;

        }

    }
    //______________________________________________________________________________________________ setTodayEntrance


    //______________________________________________________________________________________________ setDateTime
    @BindingAdapter(value = {"setDate"})
    public static void setDate(ML_EditText ml_editText, Date date) {

        if (date == null) {
            ml_editText.setText("");
            ml_editText.setVisibility(View.GONE);
            return;
        }

        String tag = ml_editText.getTag().toString();

        String text = "";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.US);

        switch (tag) {

            case "date" :
                text = ml_editText.getContext().getResources().getString(R.string.date);
                text = text + " : " + AutomationApp
                        .getAutomationApp(ml_editText.getContext())
                        .getUtilityComponent()
                        .getApplicationUtility()
                        .gregorianToSolarDate(date)
                        .getFullStringSolarDate();
                break;

            case "createDate":
                text = ml_editText.getContext().getResources().getString(R.string.createDate);
                text = text + " : " + AutomationApp
                        .getAutomationApp(ml_editText.getContext())
                        .getUtilityComponent()
                        .getApplicationUtility()
                        .gregorianToSolarDate(date)
                        .getFullStringSolarDate();
                text = text + " ساعت " + simpleDateFormat.format(date);
                break;

            case "from":
                text = ml_editText.getContext().getResources().getString(R.string.startDate);
                text = text + " : " + AutomationApp
                        .getAutomationApp(ml_editText.getContext())
                        .getUtilityComponent()
                        .getApplicationUtility()
                        .gregorianToSolarDate(date)
                        .getFullStringSolarDate();
                text = text + " ساعت " + simpleDateFormat.format(date);
                break;

            case "to":
                text = ml_editText.getContext().getResources().getString(R.string.endDate);
                text = text + " : " + AutomationApp
                        .getAutomationApp(ml_editText.getContext())
                        .getUtilityComponent()
                        .getApplicationUtility()
                        .gregorianToSolarDate(date)
                        .getFullStringSolarDate();
                text = text + " ساعت " + simpleDateFormat.format(date);
                break;

            case "arrival":
                text = ml_editText.getContext().getResources().getString(R.string.arrivalDate);
                text = text + " : " + AutomationApp
                        .getAutomationApp(ml_editText.getContext())
                        .getUtilityComponent()
                        .getApplicationUtility()
                        .gregorianToSolarDate(date)
                        .getFullStringSolarDate();
                text = text + " ساعت " + simpleDateFormat.format(date);
                break;
            case "departure":
                text = ml_editText.getContext().getResources().getString(R.string.departureDate);
                text = text + " : " + AutomationApp
                        .getAutomationApp(ml_editText.getContext())
                        .getUtilityComponent()
                        .getApplicationUtility()
                        .gregorianToSolarDate(date)
                        .getFullStringSolarDate();
                text = text + " ساعت " + simpleDateFormat.format(date);
                break;
        }



        ml_editText.setText(text);

    }
    //______________________________________________________________________________________________ setDateTime




    //______________________________________________________________________________________________ setTextValue
    @BindingAdapter(value = {"setTextValue"})
    public static void setTextValue(ML_EditText ml_editText, String value) {

        if (value == null) {
            ml_editText.setText("");
            ml_editText.setVisibility(View.GONE);
            return;
        }

        String tag = ml_editText.getTag().toString();

        String text = "";


        switch (tag) {

            case "description" :
                text = ml_editText.getContext().getResources().getString(R.string.description);
                text = text + " : " + value;
                break;
        }


        ml_editText.setText(text);

    }
    //______________________________________________________________________________________________ setTextValue




    //______________________________________________________________________________________________ setTime
    @BindingAdapter(value = {"setTime"})
    public static void setTime(ML_EditText ml_editText, Double value) {

        String tag = ml_editText.getTag().toString();

        String text = "";

        Context context = ml_editText.getContext();

        switch (tag) {

            case "mainWork":
                text = context.getResources().getString(R.string.mainWork);
                break;

            case "leave":
                text = context.getResources().getString(R.string.leave);
                break;

            case "mission":
                text = context.getResources().getString(R.string.mission);
                break;

            case "absenceOfWork":
                text = context.getResources().getString(R.string.absenceOfWork);
                break;

            case "lackOfWork":
                text = context.getResources().getString(R.string.lackOfWork);
                break;

            case "overWorkPayTime":
                text = context.getResources().getString(R.string.overWorkPayTime);
                break;

            case "holidayPayTime":
                text = context.getResources().getString(R.string.holidayPayTime);
                break;

            case "nightPayTime":
                text = context.getResources().getString(R.string.nightPayTime);
                break;
        }

        text = text + " : " + calcTime(value);
        ml_editText.setText(text);

    }
    //______________________________________________________________________________________________ setTime


    //______________________________________________________________________________________________ calcTime
    private static String calcTime(double value) {

        value = value * 60;
        long time = Math.round(value);

        String hours = String.format("%02d", time / 60);
        String minute = String.format("%02d", time % 60);
        return hours + ":" + minute;

    }
    //______________________________________________________________________________________________ calcTime


}
