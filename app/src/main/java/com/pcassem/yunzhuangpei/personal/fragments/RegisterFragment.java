package com.pcassem.yunzhuangpei.personal.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.personal.bean.RegisterBean;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.personal.activities.LoginActivity;
import com.pcassem.yunzhuangpei.personal.presenter.LoginPresenter;
import com.pcassem.yunzhuangpei.personal.view.RegisterView;


public class RegisterFragment extends Fragment implements RegisterView, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    public static final String TAG = "RegisterFragment";

    private EditText mPhoneNumber;
    private EditText mValidationNumber;
    private EditText mPassword;
    private EditText mConfirmPassword;
    private EditText mName;
    private Button mRegister;
    private CheckBox checkBox;

    private LoginPresenter mLoginPresenter;

    private FragmentManager manager;
    private FragmentTransaction ft;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register,container,false);
        initView(view);
        initTouchEvent();
        manager = getFragmentManager();

        mLoginPresenter = new LoginPresenter(this);
        mLoginPresenter.onCreate();
        return view;
    }

    private void initView(View v){
        mPhoneNumber = (EditText) v.findViewById(R.id.phone_number);
        mValidationNumber = (EditText) v.findViewById(R.id.validation_number);
        mPassword = (EditText)v.findViewById(R.id.password);
        mConfirmPassword = (EditText)v.findViewById(R.id.confirm_password);
        mName = (EditText)v.findViewById(R.id.name);
        mRegister = (Button) v.findViewById(R.id.register);
        checkBox = (CheckBox) v.findViewById(R.id.check_box);
    }

    private void initTouchEvent(){
        mRegister.setOnClickListener(this);
        checkBox.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.register:
                 register();
                 break;
         }
    }

    private void register(){
        RegisterBean user = new RegisterBean();
        String phoneNumber = mPhoneNumber.getText().toString();
        String validationNumber = mValidationNumber.getText().toString();
        String password = mPassword.getText().toString();
        String name = mName.getText().toString();

        if (isSubmit()){
            user.setPhoneNumber(phoneNumber);
            user.setValidationNumber(validationNumber);
            user.setPassword(password);
            user.setName(name);
            mLoginPresenter.userRegister(user);
        }
    }

    @Override
    public void onSuccess(ResultListEntity<String> registerStatus) {
        if (registerStatus.getCode() == 0){
            ((LoginActivity)getActivity()).findViewById(R.id.constact_group).setEnabled(true);
            ((LoginActivity)getActivity()).findViewById(R.id.constact_all).setEnabled(false);
            LoginFragment myJDEditFragment = new LoginFragment();
            ft = manager.beginTransaction();
            ft.replace(R.id.id_content, myJDEditFragment);
            ft.commit();
        }
        if (registerStatus.getCode() == 1){
            Toast.makeText(getActivity(), "验证码错误", Toast.LENGTH_SHORT).show();
        }
        
        if (registerStatus.getCode() == 2 || registerStatus.getCode() == 3){
            Toast.makeText(getActivity(), "手机号码已经存在", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError() {
        Toast.makeText(getActivity(), "注册失败，请重新注册", Toast.LENGTH_SHORT).show();
    }

    private boolean isSubmit(){
        if (TextUtils.isEmpty(mPhoneNumber.getText())){
            Toast.makeText(getActivity(), "手机号不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(mValidationNumber.getText())){
            Toast.makeText(getActivity(), "验证码不正确", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(mPassword.getText())){
            Toast.makeText(getActivity(), "密码不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(mConfirmPassword.getText())){
            Toast.makeText(getActivity(), "确认密码错误", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (mPassword.getText().toString().trim().length() < 6){
            Toast.makeText(getActivity(), "密码不能少于六个字符", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(mName.getText())){
            Toast.makeText(getActivity(), "昵称不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked){
            mRegister.setBackground(getResources().getDrawable(R.drawable.submit_style));
            mRegister.setEnabled(true);
        } else {
            mRegister.setBackground(getResources().getDrawable(R.drawable.submit_non_style));
            mRegister.setEnabled(false);
        }
    }
}
