package com.pcassem.yunzhuangpei.training.fragments;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.pcassem.yunzhuangpei.R;

public class SignUpDialogFragment extends DialogFragment implements View.OnClickListener{

    private TextView mCancel;
    private Button mSubmit;

    public static SignUpDialogFragment newInstance() {
        SignUpDialogFragment fragment = new SignUpDialogFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE,R.style.dialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final Window window = getDialog().getWindow();
        View view = inflater.inflate(R.layout.fragment_sign_up_dialog,  ((ViewGroup) window.findViewById(android.R.id.content)), false);//需要用android.R.id.content这个view
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setLayout(-1, -2);

        initView(view);
        initTouchEvent();

        return view;
    }

    private void initView(View view){
        mCancel = (TextView) view.findViewById(R.id.cancel);
        mSubmit = (Button) view.findViewById(R.id.submit);
    }

    private void initTouchEvent(){
        mCancel.setOnClickListener(this);
        mSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel:
                dismiss();
                break;
            case R.id.submit:
                dismiss();
                break;
        }
    }
}
