package ir.ngra.automation.views.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.ButterKnife;
import ir.ngra.automation.R;
import ir.ngra.automation.databinding.AdapterHomeActionMenuBinding;
import ir.ngra.automation.models.MD_HomeActionMenu;


public class AP_HomeActionMenu extends RecyclerView.Adapter<AP_HomeActionMenu.customHolder> {

    private LayoutInflater layoutInflater;
    private List<MD_HomeActionMenu> md_homeActionMenus;
    private menuActionClick actionClick;



    public interface menuActionClick {
        void itemClick(int action, Bundle bundle);
    }


    //______________________________________________________________________________________________ AP_HomeActionMenu
    public AP_HomeActionMenu(List<MD_HomeActionMenu> md_homeActionMenus, menuActionClick actionClick) {
        this.md_homeActionMenus = md_homeActionMenus;
        this.actionClick = actionClick;
    }
    //______________________________________________________________________________________________ AP_HomeActionMenu




    //______________________________________________________________________________________________ onCreateViewHolder
    @NonNull
    @Override
    public customHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());

        return new customHolder(DataBindingUtil.inflate(layoutInflater, R.layout.adapter_home_action_menu, parent, false));
    }
    //______________________________________________________________________________________________ onCreateViewHolder


    //______________________________________________________________________________________________ onBindViewHolder
    @Override
    public void onBindViewHolder(@NonNull customHolder holder, int position) {
        holder.bind(md_homeActionMenus.get(position), position);
    }
    //______________________________________________________________________________________________ onBindViewHolder


    //______________________________________________________________________________________________ getItemCount
    @Override
    public int getItemCount() {
        return md_homeActionMenus.size();
    }
    //______________________________________________________________________________________________ getItemCount


    //______________________________________________________________________________________________ customHolder
    public class customHolder extends RecyclerView.ViewHolder {

        AdapterHomeActionMenuBinding binding;
        View view;

        public customHolder(AdapterHomeActionMenuBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            view = binding.getRoot();
            ButterKnife.bind(this, view);
        }

        public void bind(MD_HomeActionMenu item, int position) {
            binding.setMenu(item);
            view.setOnClickListener(v -> actionClick.itemClick(item.getAction(), item.getBundle()));
            binding.executePendingBindings();
        }
    }
    //______________________________________________________________________________________________ customHolder


}
