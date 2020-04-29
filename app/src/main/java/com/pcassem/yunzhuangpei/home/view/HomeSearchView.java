package com.pcassem.yunzhuangpei.home.view;

import com.pcassem.yunzhuangpei.entity.KnowledgeEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.entity.SearchListEntity;

/**
 * Created by zhangqi on 2017/12/25.
 */

public interface HomeSearchView {

    void onSuccess(SearchListEntity homeSearchList);

    void onError();
}
