package ir.ngra.automation.views.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.mlcode.latifiarchitecturelibrary.customs.ML_Button;
import ir.ngra.automation.R;
import ir.ngra.automation.databinding.HomeBinding;

import ir.ngra.automation.models.MD_HomeActionMenu;
import ir.ngra.automation.utility.AttendanceType;
import ir.ngra.automation.viewmodels.VM_Home;
import ir.ngra.automation.views.activity.MainActivity;
import ir.ngra.automation.views.adapter.AP_HomeActionMenu;

public class Home extends Primary implements Primary.fragmentActions, AP_HomeActionMenu.menuActionClick {

    private VM_Home vm_home;
    private boolean doubleExitApplication = false;


    @BindView(R.id.linearLayoutActionMenu)
    LinearLayout linearLayoutActionMenu;

    @BindView(R.id.textViewMessageCount)
    TextView textViewMessageCount;

    @BindView(R.id.constraintLayoutAction)
    ConstraintLayout constraintLayoutAction;

    @BindView(R.id.recyclerViewMenu)
    RecyclerView recyclerViewMenu;

    @BindView(R.id.ml_ButtonEditArrival)
    ML_Button ml_ButtonEditArrival;

    @BindView(R.id.ml_ButtonEditDeparture)
    ML_Button ml_ButtonEditDeparture;



    //______________________________________________________________________________________________ onCreateView
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (getView() == null) {
            vm_home = new VM_Home(getActivity());
            HomeBinding binding = DataBindingUtil.inflate(inflater, R.layout.home, container, false);
            binding.setHome(vm_home);
            setView(binding.getRoot());
            ButterKnife.bind(this, getView());
            setOnClicks();
            createHomeActionMenu();
        }
        return getView();
    }
    //______________________________________________________________________________________________ onCreateView


    //______________________________________________________________________________________________ onCreateView
    @Override
    public void onStart() {
        super.onStart();
        setPublishSubjectFromObservable(Home.this, vm_home);
        MainActivity.showTitle(getContext(), getResources().getString(R.string.Home), getResources().getDrawable(R.drawable.ic_quarantine));
        vm_home.getTodayArrivalAndDeparture();
        setMessageCount(100);
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

        if (constraintLayoutAction.getVisibility() == View.VISIBLE) {
            closeLayoutAction();
            return;
        }

        if (doubleExitApplication)
            System.exit(1);
        else {
            linearLayoutActionMenu.setAlpha(0.1f);
            doubleExitApplication = true;
            showToast(
                    getResources().getString(R.string.doubleExit),
                    getResources().getColor(R.color.colorPrimary),
                    getResources().getDrawable(R.drawable.ic_exit),
                    getResources().getColor(R.color.colorPrimary));
            Handler handler = new Handler();
            handler.postDelayed(() -> {
                doubleExitApplication = false;
                linearLayoutActionMenu.setAlpha(1);
            }, 4000);
        }
    }
    //______________________________________________________________________________________________ OnBackPress


    //______________________________________________________________________________________________ setOnClicks
    private void setOnClicks() {

        linearLayoutActionMenu.setOnClickListener(v -> openLayoutAction());

        constraintLayoutAction.setOnClickListener(v -> closeLayoutAction());

        ml_ButtonEditArrival.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putByte(getResources().getString(R.string.ML_EditTime), AttendanceType.Arrival);
            gotoFragment(R.id.action_home_to_newEditTime, bundle);
        });


        ml_ButtonEditDeparture.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putByte(getResources().getString(R.string.ML_EditTime), AttendanceType.Departure);
            gotoFragment(R.id.action_home_to_newEditTime, bundle);
        });

    }
    //______________________________________________________________________________________________ setOnClicks


    //______________________________________________________________________________________________ setMessageCount
    private void setMessageCount(int count) {
        if (count == 0) {
            textViewMessageCount.setText(String.valueOf(count));
            textViewMessageCount.setVisibility(View.GONE);
        } else {
            if (count > 99)
                textViewMessageCount.setText("+99");
            else
                textViewMessageCount.setText(String.valueOf(count));

            textViewMessageCount.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.bounce));
            textViewMessageCount.setVisibility(View.VISIBLE);
        }
    }
    //______________________________________________________________________________________________ setMessageCount



    //______________________________________________________________________________________________ closeLayoutAction
    private void closeLayoutAction() {
        constraintLayoutAction.setAnimation(null);
        constraintLayoutAction.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.slide_out_bottom));
        constraintLayoutAction.setVisibility(View.GONE);
    }
    //______________________________________________________________________________________________ closeLayoutAction





    //______________________________________________________________________________________________ openLayoutAction
    private void openLayoutAction() {
        constraintLayoutAction.setAnimation(null);
        constraintLayoutAction.setVisibility(View.GONE);
        constraintLayoutAction.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_bottom));
        constraintLayoutAction.setVisibility(View.VISIBLE);
//            gotoFragment(R.id.action_home_to_workVacation, null);
    }
    //______________________________________________________________________________________________ openLayoutAction



    //______________________________________________________________________________________________ createHomeActionMenu
    private void createHomeActionMenu() {
        List<MD_HomeActionMenu> menus = new ArrayList<>();
        menus.add(new MD_HomeActionMenu(getResources().getString(R.string.workVacations),getResources().getDrawable(R.drawable.ic_camping),R.id.action_home_to_workVacation, null));
        menus.add(new MD_HomeActionMenu(getResources().getString(R.string.missions),getResources().getDrawable(R.drawable.ic_businessman),R.id.action_home_to_mission, null));
        Bundle bundle = new Bundle();
        bundle.putByte(getResources().getString(R.string.ML_EditTime), AttendanceType.ArrivalAndDeparture);
        menus.add(new MD_HomeActionMenu(getResources().getString(R.string.changeAttendanceTime),getResources().getDrawable(R.drawable.ic_edit_time),R.id.action_home_to_newEditTime, bundle));
        menus.add(new MD_HomeActionMenu(getResources().getString(R.string.legalReceipt),getResources().getDrawable(R.drawable.ic_salary),R.id.action_home_to_workVacation, null));
        menus.add(new MD_HomeActionMenu(getResources().getString(R.string.reports),getResources().getDrawable(R.drawable.ic_user_report),R.id.action_home_to_reports, null));

        AP_HomeActionMenu ap_homeActionMenu = new AP_HomeActionMenu(menus, Home.this);
        recyclerViewMenu.setAdapter(ap_homeActionMenu);
        recyclerViewMenu.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));

    }
    //______________________________________________________________________________________________ createHomeActionMenu



    //______________________________________________________________________________________________ itemClick
    @Override
    public void itemClick(int action, Bundle bundle) {
        constraintLayoutAction.setVisibility(View.GONE);
        gotoFragment(action, bundle);
    }
    //______________________________________________________________________________________________ itemClick

}
