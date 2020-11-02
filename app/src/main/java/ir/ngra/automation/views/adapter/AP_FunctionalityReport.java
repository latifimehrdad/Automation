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
import ir.ngra.automation.databinding.AdapterFunctionalityReportBinding;
import ir.ngra.automation.models.MD_FunctionalityReport;

public class AP_FunctionalityReport extends RecyclerView.Adapter<AP_FunctionalityReport.customHolder> {


    private LayoutInflater layoutInflater;
    private List<MD_FunctionalityReport> md_functionalityReports;

    //______________________________________________________________________________________________ AP_FunctionalityReport
    public AP_FunctionalityReport(List<MD_FunctionalityReport> md_functionalityReports) {
        this.md_functionalityReports = md_functionalityReports;
    }
    //______________________________________________________________________________________________ AP_FunctionalityReport



    //______________________________________________________________________________________________ onCreateViewHolder
    @NonNull
    @Override
    public customHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());

        return new customHolder(DataBindingUtil.inflate(layoutInflater, R.layout.adapter_functionality_report, parent, false));
    }
    //______________________________________________________________________________________________ onCreateViewHolder




    //______________________________________________________________________________________________ onBindViewHolder
    @Override
    public void onBindViewHolder(@NonNull customHolder holder, int position) {
        holder.bind(md_functionalityReports.get(position), position);
    }
    //______________________________________________________________________________________________ onBindViewHolder



    //______________________________________________________________________________________________ getItemCount
    @Override
    public int getItemCount() {
        return md_functionalityReports.size();
    }
    //______________________________________________________________________________________________ getItemCount



    //______________________________________________________________________________________________ customHolder
    public class customHolder extends RecyclerView.ViewHolder {

        AdapterFunctionalityReportBinding binding;

        public customHolder(AdapterFunctionalityReportBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            View view = binding.getRoot();
            ButterKnife.bind(this, view);
        }

        public void bind(MD_FunctionalityReport item, int position) {
            binding.setItem(item);
            binding.executePendingBindings();
        }
    }
    //______________________________________________________________________________________________ customHolder


}
