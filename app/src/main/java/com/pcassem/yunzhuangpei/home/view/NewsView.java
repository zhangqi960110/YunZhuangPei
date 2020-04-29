package com.pcassem.yunzhuangpei.home.view;

import com.pcassem.yunzhuangpei.entity.NewsEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;

/**
 * Created by zhangqi on 2017/12/9.
 */

public interface NewsView {

    void onSuccess(ResultListEntity<NewsEntity> newsListEntity);

    void onError();
}
