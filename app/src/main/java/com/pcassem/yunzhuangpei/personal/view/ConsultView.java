package com.pcassem.yunzhuangpei.personal.view;

import com.pcassem.yunzhuangpei.entity.ConsultEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;

/**
 * Created by zhangqi on 2018/1/23.
 */

public interface ConsultView {

    void onSuccess(ResultListEntity<ConsultEntity> status);

    void onError();
}
