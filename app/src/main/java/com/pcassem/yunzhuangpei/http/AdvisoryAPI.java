package com.pcassem.yunzhuangpei.http;

import com.pcassem.yunzhuangpei.advisory.bean.BookBean;
import com.pcassem.yunzhuangpei.advisory.bean.CommonBean;
import com.pcassem.yunzhuangpei.advisory.bean.OnlineBean;
import com.pcassem.yunzhuangpei.entity.ConsultInitEntity;
import com.pcassem.yunzhuangpei.entity.ExportDetailsEntity;
import com.pcassem.yunzhuangpei.entity.ExportEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;

import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhangqi on 2017/12/21.
 */

public interface AdvisoryAPI {

    //推荐专家列表
    @GET("/app/specialist/commendList")
    Observable<ResultListEntity<ExportEntity>> getCommendExportList();

    //全部专家列表
    @GET("/app/specialist/list")
    Observable<ResultListEntity<ExportEntity>> getAllExportList();

    //专家详情
    @GET("/app/specialist/info")
    Observable<ResultListEntity<ExportDetailsEntity>> getExportDetails(@Query("specialistID") int specialistID);

    //新增图文咨询
    @Headers({"Content-type:application/json"})
    @POST("/app/consult/common/add")
    Observable<ResultListEntity<String>>  addCommonConsult(@Body CommonBean common);

    //新增极速咨询
    @Headers({"Content-type:application/json"})
    @POST("/app/consult/quick/add")
    Observable<ResultListEntity<String>>  addQuickConsult(@Body CommonBean quick);

    //新增实时咨询
    @Headers({"Content-type:application/json"})
    @POST("/app/consult/online/add")
    Observable<ResultListEntity<String>>  addOnlineConsult(@Body OnlineBean online);

    //新增极速咨询
    @Headers({"Content-type:application/json"})
    @POST("/app/consult/book/add")
    Observable<ResultListEntity<String>>  addBookConsult(@Body BookBean book);

    //新增图文和极速咨询的信息加载
    @GET("/app/consult/common/add/load")
    Observable<ResultListEntity<ConsultInitEntity>>  getInitConsult(@Query("userID") int userID);

    //上传图片
    @Multipart
    @POST("/app/consult/uploadImage")
    Observable<ResultListEntity<String>> uploadImage(@Part MultipartBody.Part file);

}
