package com.pcassem.yunzhuangpei.home.view;

import com.pcassem.yunzhuangpei.entity.CommentEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;

/**
 * Created by zhangqi on 2017/12/10.
 */

public interface CommentView {
    void onSuccess(ResultListEntity<CommentEntity> newsListEntity);

    void onError();
}
