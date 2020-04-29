package com.pcassem.yunzhuangpei.training.view;

import com.pcassem.yunzhuangpei.entity.CourseEntity;
import com.pcassem.yunzhuangpei.entity.KnowledgeEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;


public interface CourseView {
    void onSuccess(ResultListEntity<CourseEntity> courseListEntity);

    void onError();
}
