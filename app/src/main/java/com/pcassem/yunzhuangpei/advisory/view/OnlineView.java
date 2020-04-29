package com.pcassem.yunzhuangpei.advisory.view;

import com.pcassem.yunzhuangpei.entity.ResultListEntity;

/**
 * Created by zhangqi on 2018/1/26.
 */

public interface OnlineView {

    void onAddOnlineConsultSuccess(ResultListEntity<String> result);

    void onAddOnlineConsultError();
}
