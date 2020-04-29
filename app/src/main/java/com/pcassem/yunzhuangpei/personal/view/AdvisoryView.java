package com.pcassem.yunzhuangpei.personal.view;

import com.pcassem.yunzhuangpei.entity.MyAdvisoryEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;

/**
 * Created by zhangqi on 2018/1/22.
 */

public interface AdvisoryView {

    void onSuccess(ResultListEntity<MyAdvisoryEntity> status);

    void onError();
}
