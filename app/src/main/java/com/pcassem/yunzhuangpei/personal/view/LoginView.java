package com.pcassem.yunzhuangpei.personal.view;

import com.pcassem.yunzhuangpei.entity.ExportEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.entity.UserEntity;

/**
 * Created by zhangqi on 2017/12/21.
 */

public interface LoginView {
    void onSuccess(ResultListEntity<UserEntity> userInfo);

    void onError();
}
