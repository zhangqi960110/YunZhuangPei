package com.pcassem.yunzhuangpei.training.view;

import com.pcassem.yunzhuangpei.entity.KnowledgeDetailsDocEntity;
import com.pcassem.yunzhuangpei.entity.KnowledgeDetailsNonDocEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;

/**
 * Created by zhangqi on 2017/12/16.
 */

public interface KnowledgeDetailsDocView {
    void onSuccess(ResultListEntity<KnowledgeDetailsDocEntity> knowledgeDetailsNonDocEntity);

    void onError();
}
