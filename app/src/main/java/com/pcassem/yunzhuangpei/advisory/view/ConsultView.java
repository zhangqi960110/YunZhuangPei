package com.pcassem.yunzhuangpei.advisory.view;


import com.pcassem.yunzhuangpei.entity.ConsultInitEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;

import okhttp3.ResponseBody;

/**
 * Created by zhangqi on 2018/1/24.
 */

public interface ConsultView {

    void onAddCommonConsultSuccess(ResultListEntity<String> result);

    void onAddCommonConsultError();

    void onGetConsultInitSuccess(ResultListEntity<ConsultInitEntity> result);

    void onGetConsultInitError();


    void onUploadImageSuccess(ResultListEntity<String> responseBody);

    void onUploadImageError();
}
