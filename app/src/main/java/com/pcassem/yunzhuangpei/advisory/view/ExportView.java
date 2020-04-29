package com.pcassem.yunzhuangpei.advisory.view;

import com.pcassem.yunzhuangpei.entity.ExportEntity;
import com.pcassem.yunzhuangpei.entity.NewsEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;

/**
 * Created by zhangqi on 2017/12/21.
 */

public interface ExportView {

    void onSuccess(ResultListEntity<ExportEntity> exportList);

    void onError();
}
