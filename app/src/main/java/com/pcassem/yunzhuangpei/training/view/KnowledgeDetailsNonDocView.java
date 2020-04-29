package com.pcassem.yunzhuangpei.training.view;

import com.pcassem.yunzhuangpei.entity.KnowledgeDetailsNonDocEntity;
import com.pcassem.yunzhuangpei.entity.NewsDetailsEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;

/**
 * Created by zhangqi on 2017/12/15.
 */

public interface KnowledgeDetailsNonDocView {
    void onSuccess(ResultListEntity<KnowledgeDetailsNonDocEntity> knowledgeDetailsNonDocEntity);

    void onError();
}
