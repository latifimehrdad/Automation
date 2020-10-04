package ir.ngra.automation.views.adapter;


import android.content.res.Resources;

import androidx.databinding.BindingAdapter;

import ir.mlcode.latifiarchitecturelibrary.utility.wave.LatifiWaveProgressView;
import ir.ngra.automation.R;

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



}
