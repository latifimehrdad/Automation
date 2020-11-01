package ir.ngra.automation.views.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.ngra.automation.R;
import ir.ngra.automation.databinding.ReportsBinding;
import ir.ngra.automation.models.MD_HomeActionMenu;
import ir.ngra.automation.viewmodels.VM_Reports;
import ir.ngra.automation.views.activity.MainActivity;
import ir.ngra.automation.views.adapter.AP_HomeActionMenu;

public class Reports extends Primary implements Primary.fragmentActions, AP_HomeActionMenu.menuActionClick {

    private VM_Reports vm_reports;

    @BindView(R.id.recyclerViewMenu)
    RecyclerView recyclerViewMenu;

    //______________________________________________________________________________________________ onCreateView
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (getView() == null) {
            vm_reports = new VM_Reports(getContext());
            ReportsBinding binding = DataBindingUtil.inflate(inflater, R.layout.reports, container, false);
            binding.setReports(vm_reports);
            setView(binding.getRoot());
            ButterKnife.bind(this, getView());
            createHomeActionMenu();
        }
        return getView();
    }
    //______________________________________________________________________________________________ onCreateView


    //______________________________________________________________________________________________ onCreateView
    @Override
    public void onStart() {
        super.onStart();
        setPublishSubjectFromObservable(Reports.this, vm_reports);
        MainActivity.showTitle(getContext(), getResources().getString(R.string.reports), getResources().getDrawable(R.drawable.ic_user_report));
    }
    //______________________________________________________________________________________________ onCreateView


    //______________________________________________________________________________________________ getActionFromObservable
    @Override
    public void getActionFromObservable(Byte action) {


    }
    //______________________________________________________________________________________________ getActionFromObservable


    //______________________________________________________________________________________________ actionWhenFailureRequest
    @Override
    public void actionWhenFailureRequest() {

    }
    //______________________________________________________________________________________________ actionWhenFailureRequest


    //______________________________________________________________________________________________ OnBackPress
    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void OnBackPress() {
        removeCallBackAndBack();
    }
    //______________________________________________________________________________________________ OnBackPress


    //______________________________________________________________________________________________ createHomeActionMenu
    private void createHomeActionMenu() {
        List<MD_HomeActionMenu> menus = new ArrayList<>();
        menus.add(new MD_HomeActionMenu(getResources().getString(R.string.functionalityReport), getResources().getDrawable(R.drawable.ic_functionality_report), R.id.action_reports_to_functionalityReport));

        AP_HomeActionMenu ap_homeActionMenu = new AP_HomeActionMenu(menus, Reports.this);
        recyclerViewMenu.setAdapter(ap_homeActionMenu);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        recyclerViewMenu.setLayoutManager(layoutManager);

    }
    //______________________________________________________________________________________________ createHomeActionMenu


    //______________________________________________________________________________________________ itemClick
    @Override
    public void itemClick(int action) {
        gotoFragment(action, null);
    }
    //______________________________________________________________________________________________ itemClick

}
