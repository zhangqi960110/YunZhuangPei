package com.pcassem.yunzhuangpei.advisory.model;

import com.pcassem.yunzhuangpei.advisory.bean.BookBean;
import com.pcassem.yunzhuangpei.advisory.bean.CommonBean;
import com.pcassem.yunzhuangpei.advisory.bean.OnlineBean;
import com.pcassem.yunzhuangpei.entity.ConsultInitEntity;
import com.pcassem.yunzhuangpei.entity.ExportDetailsEntity;
import com.pcassem.yunzhuangpei.entity.ExportEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.http.AdvisoryAPI;
import com.pcassem.yunzhuangpei.http.RetrofitHelper;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by zhangqi on 2017/12/21.
 */

public class ExportData {
    private AdvisoryAPI mAdvisoryAPI;

    public ExportData() {
        this.mAdvisoryAPI = RetrofitHelper.getInstance().getAdvisoryAPI();
    }

    //推荐专家列表
    public Observable<ResultListEntity<ExportEntity>> getCommendExportList() {
        return mAdvisoryAPI.getCommendExportList();
    }

   //全部专家列表
    public Observable<ResultListEntity<ExportEntity>> getAllExportList(){
        return mAdvisoryAPI.getAllExportList();
    }

    //专家详情
    public Observable<ResultListEntity<ExportDetailsEntity>> getExportDetails(int specialistID){
        return mAdvisoryAPI.getExportDetails(specialistID);
    }

    //新增图文咨询
    public Observable<ResultListEntity<String>> addCommonConsult(CommonBean common){
        return mAdvisoryAPI.addCommonConsult(common);
    }

    //新增极速咨询
    public Observable<ResultListEntity<String>> addQuickConsult(CommonBean quick){
        return mAdvisoryAPI.addQuickConsult(quick);
    }

    //新增实时咨询
    public Observable<ResultListEntity<String>> addOnlineConsult(OnlineBean online){
        return mAdvisoryAPI.addOnlineConsult(online);
    }

    //新增现场预约
    public Observable<ResultListEntity<String>> addBookConsult(BookBean book){
        return mAdvisoryAPI.addBookConsult(book);
    }

    //新增图文和极速咨询的信息加载
    public Observable<ResultListEntity<ConsultInitEntity>> getInitConsult(int userID){
        return mAdvisoryAPI.getInitConsult(userID);
    }

    //上传图片
    public Observable<ResultListEntity<String>> uploadImage(String path){
        File file = new File(path);

        RequestBody photos = RequestBody.create(MediaType.parse("image/jpg"), file);

        MultipartBody.Part body = MultipartBody.Part.createFormData("imgFile",file.getName(),photos);

        return mAdvisoryAPI.uploadImage(body);
    }

}
