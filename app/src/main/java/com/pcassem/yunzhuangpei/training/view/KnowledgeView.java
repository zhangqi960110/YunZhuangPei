package com.pcassem.yunzhuangpei.training.view;

import com.pcassem.yunzhuangpei.entity.KnowledgeEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;


public interface KnowledgeView {
    void onSuccess(ResultListEntity<KnowledgeEntity> newsListEntity);

    void onError();
}
