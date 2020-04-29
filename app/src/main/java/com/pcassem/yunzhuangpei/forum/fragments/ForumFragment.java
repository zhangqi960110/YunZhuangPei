package com.pcassem.yunzhuangpei.forum.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pcassem.yunzhuangpei.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForumFragment extends Fragment {


    public static ForumFragment newInstance(){
        ForumFragment forumFragment = new ForumFragment();
        return forumFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forum, container, false);
        return view;
    }

}
