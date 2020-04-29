package com.pcassem.yunzhuangpei.personal.view;

import com.pcassem.yunzhuangpei.entity.ResultListEntity;

/**
 * Created by zhangqi on 2018/1/22.
 */

public interface AddProjectView {

    void onSuccess(ResultListEntity<String> status);

    void onError();
}
