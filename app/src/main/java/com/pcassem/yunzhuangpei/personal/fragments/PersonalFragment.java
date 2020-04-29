package com.pcassem.yunzhuangpei.personal.fragments;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.advisory.activities.ExportDetailsActivity;
import com.pcassem.yunzhuangpei.home.activities.HomeActivity;
import com.pcassem.yunzhuangpei.personal.activities.HelpActivity;
import com.pcassem.yunzhuangpei.personal.activities.LoginActivity;
import com.pcassem.yunzhuangpei.personal.activities.MyAdvisoryActivity;
import com.pcassem.yunzhuangpei.personal.activities.MyCollectionActivity;
import com.pcassem.yunzhuangpei.personal.activities.MyDownloadActivity;
import com.pcassem.yunzhuangpei.personal.activities.MyProjectActivity;
import com.pcassem.yunzhuangpei.personal.activities.MyTrainingActivity;
import com.pcassem.yunzhuangpei.personal.activities.PersonalInformationActivity;
import com.pcassem.yunzhuangpei.personal.activities.SystemSettingsActivity;
import com.pcassem.yunzhuangpei.view.CommomDialog;
import com.pcassem.yunzhuangpei.view.ItemPersonalList;

import io.rong.callkit.RongCallKit;


public class PersonalFragment extends Fragment implements View.OnClickListener {

    public static final String TAG = "PersonalFragment";

    private TextView mJumpLoginActivity;
    private TextView mJumpPersonalInfomationActivity;
    private ImageView mPersonalRightJump;
    private ItemPersonalList mJumpMyProjectActivity;
    private ItemPersonalList mJumpMydownloadActivity;
    private ItemPersonalList mJumpMyadvisoryActivity;
    private ItemPersonalList mJumpMyCollectionActivity;
    private ItemPersonalList mJumpMyTrainingActivity;
    private ItemPersonalList mJumpHelpInfoActivity;
    private ItemPersonalList mJumpSystemSettingsActivity;


    public static PersonalFragment newInstance() {
        PersonalFragment personalFragment = new PersonalFragment();
        return personalFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        initView(view);
        initTouchEvent();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        String username = getActivity().getSharedPreferences("userinfo", Context.MODE_PRIVATE).getString("username", "");
        if (username != "" && username != null) {
            mJumpLoginActivity.setText(username);
            mJumpLoginActivity.setEnabled(false);
            mJumpPersonalInfomationActivity.setVisibility(View.VISIBLE);
            mPersonalRightJump.setVisibility(View.VISIBLE);
        } else {
            mJumpLoginActivity.setText("未登录");
            mJumpLoginActivity.setEnabled(true);
            mJumpPersonalInfomationActivity.setVisibility(View.GONE);
            mPersonalRightJump.setVisibility(View.GONE);
        }
    }

    private void initView(View view) {
        mJumpLoginActivity = (TextView) view.findViewById(R.id.personal_jump_login_activity);
        mPersonalRightJump = (ImageView) view.findViewById(R.id.personal_right_jump);
        mJumpPersonalInfomationActivity = (TextView) view.findViewById(R.id.personal_jump_personal_infomation_activity);
        mJumpMyProjectActivity = (ItemPersonalList) view.findViewById(R.id.personal_jump_my_project_activity);
        mJumpMydownloadActivity = (ItemPersonalList) view.findViewById(R.id.personal_jump_my_download_activity);
        mJumpMyadvisoryActivity = (ItemPersonalList) view.findViewById(R.id.personal_jump_my_advisory_activity);
        mJumpMyCollectionActivity = (ItemPersonalList) view.findViewById(R.id.personal_jump_my_collection_activity);
        mJumpMyTrainingActivity = (ItemPersonalList) view.findViewById(R.id.personal_jump_my_training_activity);
        mJumpHelpInfoActivity = (ItemPersonalList) view.findViewById(R.id.personal_jump_help_info_activity);
        mJumpSystemSettingsActivity = (ItemPersonalList) view.findViewById(R.id.personal_jump_system_settings);
    }

    private void initTouchEvent() {
        mJumpLoginActivity.setOnClickListener(this);
        mJumpPersonalInfomationActivity.setOnClickListener(this);
        mJumpMyProjectActivity.setOnClickListener(this);
        mJumpMydownloadActivity.setOnClickListener(this);
        mJumpMyadvisoryActivity.setOnClickListener(this);
        mJumpMyCollectionActivity.setOnClickListener(this);
        mJumpMyTrainingActivity.setOnClickListener(this);
        mJumpHelpInfoActivity.setOnClickListener(this);
        mJumpSystemSettingsActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.personal_jump_login_activity:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.personal_jump_personal_infomation_activity:
//                Intent intent1 = new Intent(getActivity(), PersonalInformationActivity.class);
//                startActivity(intent1);
                Toast.makeText(getActivity(), "测试版-当前功能不可用", Toast.LENGTH_SHORT).show();
                break;
            case R.id.personal_jump_my_project_activity:
                if (isLogin() == -1) {
                    myDialog();
                } else {
                    Intent intent2 = new Intent(getActivity(), MyProjectActivity.class);
                    startActivity(intent2);
                }
                break;
            case R.id.personal_jump_my_download_activity:
//                if (isLogin() == -1) {
//                    myDialog();
//                } else {
//                    Intent intent3 = new Intent(getActivity(), MyDownloadActivity.class);
//                    startActivity(intent3);
//                }
                Toast.makeText(getActivity(), "测试版-当前功能不可用", Toast.LENGTH_SHORT).show();
                break;
            case R.id.personal_jump_my_advisory_activity:
                if (isLogin() == -1) {
                    myDialog();
                } else {
                    Intent intent4 = new Intent(getActivity(), MyAdvisoryActivity.class);
                    startActivity(intent4);
                }
                break;
            case R.id.personal_jump_my_collection_activity:
//                if (isLogin() == -1) {
//                    myDialog();
//                } else {
//                    Intent intent5 = new Intent(getActivity(), MyCollectionActivity.class);
//                    startActivity(intent5);
//                }
                Toast.makeText(getActivity(), "测试版-当前功能不可用", Toast.LENGTH_SHORT).show();
                break;
            case R.id.personal_jump_my_training_activity:
//                if (isLogin() == -1) {
//                    myDialog();
//                } else {
//                    Intent intent6 = new Intent(getActivity(), MyTrainingActivity.class);
//                    startActivity(intent6);
//                }
                Toast.makeText(getActivity(), "测试版-当前功能不可用", Toast.LENGTH_SHORT).show();
                break;
            case R.id.personal_jump_help_info_activity:
//                Intent intent7 = new Intent(getActivity(), HelpActivity.class);
//                startActivity(intent7);
                Toast.makeText(getActivity(), "测试版-当前功能不可用", Toast.LENGTH_SHORT).show();
                break;
            case R.id.personal_jump_system_settings:
                Intent intent8 = new Intent(getActivity(), SystemSettingsActivity.class);
                startActivity(intent8);
                break;
        }
    }

    private int isLogin() {
        String username = getActivity().getSharedPreferences("userinfo", Context.MODE_PRIVATE).getString("username", "");
        if (username == "") {
            return -1;
        } else {
            return getActivity().getSharedPreferences("userinfo", Context.MODE_PRIVATE).getInt("userID", -1);
        }

    }

    private void myDialog() {
        new CommomDialog(getActivity(), R.style.commom_dialog, "尚未登录，是否先登录？", new CommomDialog.OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, boolean confirm) {
                if (confirm) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    dialog.dismiss();
                }

            }
        }).setTitle("提示信息").show();
    }


}
