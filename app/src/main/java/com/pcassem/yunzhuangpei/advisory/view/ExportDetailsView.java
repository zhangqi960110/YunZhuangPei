package com.pcassem.yunzhuangpei.advisory.view;

import com.pcassem.yunzhuangpei.entity.ExportDetailsEntity;
import com.pcassem.yunzhuangpei.entity.ExportEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;

/**
 * Created by zhangqi on 2017/12/21.
 */

public interface ExportDetailsView {
    void onSuccess(ResultListEntity<ExportDetailsEntity> exportDetails);

    void onError();
}
