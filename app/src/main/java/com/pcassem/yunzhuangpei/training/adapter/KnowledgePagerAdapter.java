package com.pcassem.yunzhuangpei.training.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.pcassem.yunzhuangpei.training.fragments.KnowledgeFragment;
import com.pcassem.yunzhuangpei.training.presenter.KnowledgeDetailsPresenter;

public class KnowledgePagerAdapter extends FragmentPagerAdapter {

    String[] data;

    public KnowledgePagerAdapter(FragmentManager fm, String[] data) {
        super(fm);
        this.data = data;
    }

    @Override
    public Fragment getItem(int position) {
        return KnowledgeFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        KnowledgeFragment fragment = (KnowledgeFragment) super.instantiateItem(container, position);
        fragment.updateKnowledge(data[0],data[1],data[2],data[3],data[4]);
        return fragment;
    }

    @Override
    public int getItemPosition(Object object) {
        if (object instanceof KnowledgeFragment) {
            ((KnowledgeFragment)object).updateKnowledge(data[0],data[1],data[2],data[3],data[4]);
        }
        return super.getItemPosition(object);
    }

    public void setData(String[] data) {
        this.data = data;
        notifyDataSetChanged();
    }

}