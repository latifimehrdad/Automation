package ir.ngra.automation.views.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.ButterKnife;
import ir.ngra.automation.R;
import ir.ngra.automation.databinding.AdapterMissionBinding;
import ir.ngra.automation.models.MD_Mission;

public class AP_Mission extends RecyclerView.Adapter<AP_Mission.customHolder> {


    private LayoutInflater layoutInflater;
    private List<MD_Mission> md_missionList;

    //______________________________________________________________________________________________ AP_WorkVacation
    public AP_Mission(List<MD_Mission> md_missionList) {
        this.md_missionList = md_missionList;
    }
    //______________________________________________________________________________________________ AP_WorkVacation




    //______________________________________________________________________________________________ AP_WorkVacation
    @NonNull
    @Override
    public customHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());

        return new customHolder(DataBindingUtil.inflate(layoutInflater, R.layout.adapter_mission, parent, false));
    }
    //______________________________________________________________________________________________ AP_WorkVacation



    //______________________________________________________________________________________________ AP_WorkVacation
    @Override
    public void onBindViewHolder(@NonNull customHolder holder, int position) {
        holder.bind(md_missionList.get(position), position);
    }
    //______________________________________________________________________________________________ AP_WorkVacation



    //______________________________________________________________________________________________ AP_WorkVacation
    @Override
    public int getItemCount() {
        return md_missionList.size();
    }
    //______________________________________________________________________________________________ AP_WorkVacation



    //______________________________________________________________________________________________ customHolder
    public class customHolder extends RecyclerView.ViewHolder {

        AdapterMissionBinding binding;

        public customHolder(AdapterMissionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            View view = binding.getRoot();
            ButterKnife.bind(this, view);
        }

        public void bind(MD_Mission item, int position) {
            binding.setItem(item);
            binding.executePendingBindings();
        }
    }
    //______________________________________________________________________________________________ customHolder

}