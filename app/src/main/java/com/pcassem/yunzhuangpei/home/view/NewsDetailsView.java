package com.pcassem.yunzhuangpei.home.view;

import com.pcassem.yunzhuangpei.entity.NewsDetailsEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;

/**
 * Created by zhangqi on 2017/12/10.
 */

public interface NewsDetailsView {
    void onSuccess(ResultListEntity<NewsDetailsEntity> newsListEntity);

    void onError();
}
