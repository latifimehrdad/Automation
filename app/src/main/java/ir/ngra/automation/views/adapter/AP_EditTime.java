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
import ir.ngra.automation.databinding.AdapterEditTimeBinding;
import ir.ngra.automation.models.MD_EditTime;

public class AP_EditTime extends RecyclerView.Adapter<AP_EditTime.customHolder> {


    private LayoutInflater layoutInflater;
    private List<MD_EditTime> md_editTimeList;

    //______________________________________________________________________________________________ AP_WorkVacation
    public AP_EditTime(List<MD_EditTime> md_editTimeList) {
        this.md_editTimeList = md_editTimeList;
    }
    //______________________________________________________________________________________________ AP_WorkVacation




    //______________________________________________________________________________________________ AP_WorkVacation
    @NonNull
    @Override
    public customHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());

        return new customHolder(DataBindingUtil.inflate(layoutInflater, R.layout.adapter_edit_time, parent, false));
    }
    //______________________________________________________________________________________________ AP_WorkVacation



    //______________________________________________________________________________________________ AP_WorkVacation
    @Override
    public void onBindViewHolder(@NonNull customHolder holder, int position) {
        holder.bind(md_editTimeList.get(position), position);
    }
    //______________________________________________________________________________________________ AP_WorkVacation



    //______________________________________________________________________________________________ AP_WorkVacation
    @Override
    public int getItemCount() {
        return md_editTimeList.size();
    }
    //______________________________________________________________________________________________ AP_WorkVacation



    //______________________________________________________________________________________________ customHolder
    public class customHolder extends RecyclerView.ViewHolder {

        AdapterEditTimeBinding binding;

        public customHolder(AdapterEditTimeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            View view = binding.getRoot();
            ButterKnife.bind(this, view);
        }

        public void bind(MD_EditTime item, int position) {
            binding.setItem(item);
            binding.executePendingBindings();
        }
    }
    //______________________________________________________________________________________________ customHolder

}