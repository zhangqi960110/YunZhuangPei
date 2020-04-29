package com.pcassem.yunzhuangpei.training.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.pcassem.yunzhuangpei.training.fragments.ProjectCasesFragment;


public class ProjectCasesPagerAdapter extends FragmentPagerAdapter {

    String[] data;

    public ProjectCasesPagerAdapter(FragmentManager fm, String[] data) {
        super(fm);
        this.data = data;
    }

    @Override
    public Fragment getItem(int position) {
        return ProjectCasesFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 2;
    }


    public void setData(String[] data) {
        this.data = data;
        notifyDataSetChanged();
    }
}
