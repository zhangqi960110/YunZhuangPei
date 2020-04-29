package com.pcassem.yunzhuangpei.personal.view;

import com.pcassem.yunzhuangpei.entity.ConsultOnlineEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;

/**
 * Created by zhangqi on 2018/1/23.
 */

public interface ConsultOnlineView {

    void onSuccess(ResultListEntity<ConsultOnlineEntity> status);

    void onError();
}
