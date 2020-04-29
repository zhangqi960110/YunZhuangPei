package com.pcassem.yunzhuangpei.advisory.view;

import com.pcassem.yunzhuangpei.entity.ConsultInitEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;

/**
 * Created by zhangqi on 2018/1/24.
 */

public interface BookView {

    void onAddBookConsultSuccess(ResultListEntity<String> result);

    void onAddBookConsultError();

    void onGetConsultInitSuccess(ResultListEntity<ConsultInitEntity> result);

    void onGetConsultInitError();

}
