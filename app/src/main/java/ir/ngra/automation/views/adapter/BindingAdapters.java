package ir.ngra.automation.views.adapter;


import android.content.res.Resources;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import ir.mlcode.latifiarchitecturelibrary.utility.wave.LatifiWaveProgressView;
import ir.ngra.automation.R;
import ir.ngra.automation.models.MD_DailyItems;

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
        } else if (degree > 29 && degree < 75){
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




    //______________________________________________________________________________________________ setTodayEntrance
    @BindingAdapter(value = "setTodayEntrance")
    public static void setTodayEntrance(TextView textView, MD_DailyItems md_dailyItems) {

        String tag = textView.getTag().toString();
        String arrival = md_dailyItems.getArrival();
        String exit = md_dailyItems.getExit();

        switch (tag) {
            case "arrival_h1" :
                if (arrival == null || arrival.length() < 5)
                    textView.setText("-");
                else
                    textView.setText(arrival.substring(0,1));
                break;

            case "arrival_h2" :
                if (arrival == null || arrival.length() < 5)
                    textView.setText("-");
                else
                    textView.setText(arrival.substring(1,2));
                break;

            case "arrival_m1" :
                if (arrival == null || arrival.length() < 5)
                    textView.setText("-");
                else
                    textView.setText(arrival.substring(3,4));
                break;

            case "arrival_m2" :
                if (arrival == null || arrival.length() < 5)
                    textView.setText("-");
                else
                    textView.setText(arrival.substring(4,5));
                break;

            case "exit_h1" :
                if (exit == null || exit.length() < 5)
                    textView.setText("-");
                else
                    textView.setText(exit.substring(0,1));
                break;

            case "exit_h2" :
                if (exit == null || exit.length() < 5)
                    textView.setText("-");
                else
                    textView.setText(exit.substring(1,2));
                break;

            case "exit_m1" :
                if (exit == null || exit.length() < 5)
                    textView.setText("-");
                else
                    textView.setText(exit.substring(3,4));
                break;

            case "exit_m2" :
                if (exit == null || exit.length() < 5)
                    textView.setText("-");
                else
                    textView.setText(exit.substring(4,5));
                break;

        }

    }
    //______________________________________________________________________________________________ setTodayEntrance



}
