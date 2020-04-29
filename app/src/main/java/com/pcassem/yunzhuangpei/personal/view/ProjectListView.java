package com.pcassem.yunzhuangpei.personal.view;

import com.pcassem.yunzhuangpei.entity.MyProjectEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;


public interface ProjectListView {

    void onGetProjectSuccess(ResultListEntity<MyProjectEntity> userInfo);

    void onGetProjectError();

    void onAddProjectSuccess(ResultListEntity<String> status);

    void onAddProjectError();

    void onEditProjectSuccess(ResultListEntity<String> status);

    void onEditProjectError();

    void onDeleteProjectSuccess(ResultListEntity<String> status);

    void onDeleteProjectError();
}
