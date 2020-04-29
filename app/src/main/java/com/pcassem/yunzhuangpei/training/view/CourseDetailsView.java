package com.pcassem.yunzhuangpei.training.view;

import com.pcassem.yunzhuangpei.entity.CourseDetailsEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;


public interface CourseDetailsView {
    void onSuccess(ResultListEntity<CourseDetailsEntity> courseDetailsListEntity);

    void onError();
}
