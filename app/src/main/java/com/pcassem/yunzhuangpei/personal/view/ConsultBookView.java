package com.pcassem.yunzhuangpei.personal.view;

import com.pcassem.yunzhuangpei.entity.ConsultBookEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;

/**
 * Created by zhangqi on 2018/1/23.
 */

public interface ConsultBookView {

    void onSuccess(ResultListEntity<ConsultBookEntity> status);

    void onError();
}
