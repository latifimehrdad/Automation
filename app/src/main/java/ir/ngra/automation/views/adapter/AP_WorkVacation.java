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
import ir.ngra.automation.databinding.AdapterWorkVacationBinding;
import ir.ngra.automation.models.MD_WorkVacation;

public class AP_WorkVacation extends RecyclerView.Adapter<AP_WorkVacation.customHolder> {


    private LayoutInflater layoutInflater;
    private List<MD_WorkVacation> md_workVacationList;

    //______________________________________________________________________________________________ AP_WorkVacation
    public AP_WorkVacation(List<MD_WorkVacation> md_workVacationList) {
        this.md_workVacationList = md_workVacationList;
    }
    //______________________________________________________________________________________________ AP_WorkVacation




    //______________________________________________________________________________________________ AP_WorkVacation
    @NonNull
    @Override
    public customHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());

        return new customHolder(DataBindingUtil.inflate(layoutInflater, R.layout.adapter_work_vacation, parent, false));
    }
    //______________________________________________________________________________________________ AP_WorkVacation



    //______________________________________________________________________________________________ AP_WorkVacation
    @Override
    public void onBindViewHolder(@NonNull customHolder holder, int position) {
        holder.bind(md_workVacationList.get(position), position);
    }
    //______________________________________________________________________________________________ AP_WorkVacation



    //______________________________________________________________________________________________ AP_WorkVacation
    @Override
    public int getItemCount() {
        return md_workVacationList.size();
    }
    //______________________________________________________________________________________________ AP_WorkVacation



    //______________________________________________________________________________________________ customHolder
    public class customHolder extends RecyclerView.ViewHolder {

        AdapterWorkVacationBinding binding;

        public customHolder(AdapterWorkVacationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            View view = binding.getRoot();
            ButterKnife.bind(this, view);
        }

        public void bind(MD_WorkVacation item, int position) {
            binding.setItem(item);
            binding.executePendingBindings();
        }
    }
    //______________________________________________________________________________________________ customHolder

}
