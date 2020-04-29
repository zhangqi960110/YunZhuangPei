package com.pcassem.yunzhuangpei.training.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.home.adapter.LatestNewsAdapter;

import java.util.ArrayList;

public class ProjectCasesFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private LatestNewsAdapter mLatestNewsAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    public static ProjectCasesFragment newInstance() {

        ProjectCasesFragment projectCasesFragment = new ProjectCasesFragment();
        return projectCasesFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project_cases,container,false);
        initView(view);

        return view;
    }

    private void initView(View view){
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
    }

    private ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        String temp = " item";
        for (int i = 0; i < 10; i++) {
            data.add(i + temp);
        }
        return data;
    }
}
