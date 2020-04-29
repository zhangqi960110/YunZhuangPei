package com.pcassem.yunzhuangpei.personal.view;

import com.pcassem.yunzhuangpei.entity.ExportEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;

/**
 * Created by zhangqi on 2017/12/21.
 */

public interface RegisterView {
    void onSuccess(ResultListEntity<String> registerStatus);

    void onError();
}
