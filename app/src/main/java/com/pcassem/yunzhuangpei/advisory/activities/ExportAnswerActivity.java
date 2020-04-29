package com.pcassem.yunzhuangpei.advisory.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.pcassem.yunzhuangpei.R;
import com.pcassem.yunzhuangpei.advisory.adapter.ImagePickerAdapter;
import com.pcassem.yunzhuangpei.advisory.bean.CommonBean;
import com.pcassem.yunzhuangpei.advisory.presenter.ConsultPresenter;
import com.pcassem.yunzhuangpei.advisory.view.ConsultView;
import com.pcassem.yunzhuangpei.entity.ConsultInitEntity;
import com.pcassem.yunzhuangpei.entity.ResultListEntity;
import com.pcassem.yunzhuangpei.personal.activities.MyProjectActivity;
import com.pcassem.yunzhuangpei.utils.BitmapUtils;
import com.pcassem.yunzhuangpei.utils.GlideImageLoader;
import com.pcassem.yunzhuangpei.view.CommomDialog;
import com.pcassem.yunzhuangpei.view.SelectDialog;

import java.util.ArrayList;
import java.util.List;

import static com.pcassem.yunzhuangpei.advisory.activities.BookActivity.SELECT_PROJECT_CODE;


public class ExportAnswerActivity extends AppCompatActivity implements View.OnClickListener, ConsultView, ImagePickerAdapter.OnRecyclerViewItemClickListener {

    private static final String TAG = "ExportAnswerActivity";
    public static final int SELECT_PROJECT = 200;
    private ImageView backIv;
    private TextView description_style;
    private TextView image_style;


    private ConsultPresenter presenter;
    private ConsultInitEntity initConsult;
    private List<String> classifyList;
    private List<String> partList;

    private ImageView selectProject;
    private TextView userProjectName;
    private TextView userProjectAddress;
    private Spinner classifySpinner;
    private Spinner partSpinner;
    private EditText descriptionEt;
    private Button submit;

    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;

    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList;
    private int maxImgCount = 5;


    private List<String> imageList;
    private String[] imageStrList;
    //提交数据
    private int userID;
    private int projectID;
    private String classifyStr;
    private String partStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export_answer);
        initView();
        initTouchEvent();
        initData();

        initImagePicker();
        initWidget();
    }

    private void initView() {
        description_style = (TextView) findViewById(R.id.description_style);
        String str1 = "问题描述<font color='#999999'><small>(请详细描述项目现状和问题，越详细越好，便于专家更准确地为您分析解答)</small></font>";
        description_style.setText(Html.fromHtml(str1));
        image_style = (TextView) findViewById(R.id.image_style);
        String str2 = "上传图片<small><font color='#999999'>(反映施工现场问题，辅助专家分析，</font><font color='#ff0000'>不超过5张</font><font color='#999999'>)</small></font>";
        image_style.setText(Html.fromHtml(str2));

        backIv = (ImageView) findViewById(R.id.back_iv);
        selectProject = (ImageView) findViewById(R.id.select_project);
        userProjectName = (TextView) findViewById(R.id.user_project_name);
        userProjectAddress = (TextView) findViewById(R.id.user_project_address);
        classifySpinner = (Spinner) findViewById(R.id.classify_spinner);
        partSpinner = (Spinner) findViewById(R.id.part_spinner);
        descriptionEt = (EditText) findViewById(R.id.description);
        submit = (Button) findViewById(R.id.submit);
    }

    private void initTouchEvent() {
        backIv.setOnClickListener(this);
        submit.setOnClickListener(this);
        selectProject.setOnClickListener(this);
    }

    private void initData() {
        userID = getSharedPreferences("userinfo", Context.MODE_PRIVATE).getInt("userID", -1);

        classifyList = new ArrayList<>();
        partList = new ArrayList<>();
        imageList = new ArrayList<>();
        imageStrList = new String[5];

        presenter = new ConsultPresenter(this);
        presenter.onCreate();
        presenter.getInitConsult(userID);
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setMultiMode(false);
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(false);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
    }

    private void initWidget() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 5));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private void initSpinner() {

        ArrayAdapter<String> classifyAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, classifyList);
        classifyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classifySpinner.setAdapter(classifyAdapter);
        classifySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                classifyStr = classifyList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter<String> partAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, partList);
        partAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        partSpinner.setAdapter(partAdapter);
        partSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                partStr = partList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_iv:
                onBackPressed();
                break;
            case R.id.select_project:
                Intent intent = new Intent(ExportAnswerActivity.this, SelectProjectActivity.class);
                startActivityForResult(intent, SELECT_PROJECT_CODE);
                break;
            case R.id.submit:
                if (isSubmit()) {
                    for (int i = 0; i < 5; i++) {
                        if (i < imageList.size()) {
                            imageStrList[i] = imageList.get(i);
                        } else {
                            imageStrList[i] = "";
                        }
                    }
                    CommonBean common = new CommonBean();
                    common.setUserID(userID);
                    common.setProjectID(projectID);
                    common.setClassify(classifyStr);
                    common.setPart(partStr);
                    common.setDescription(descriptionEt.getText().toString());
                    common.setImage1(imageStrList[0]);
                    common.setImage2(imageStrList[1]);
                    common.setImage3(imageStrList[2]);
                    common.setImage4(imageStrList[3]);
                    common.setImage5(imageStrList[4]);
                    presenter.addCommonConsult(common);
                }
                break;
        }
    }

    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(this, R.style.transparentFrameWindowStyle,
                listener, names);
        if (!this.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case IMAGE_ITEM_ADD:
                List<String> names = new ArrayList<>();
                names.add("拍照");
                names.add("相册");
                showDialog(new SelectDialog.SelectDialogListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0:
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent = new Intent(ExportAnswerActivity.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true);
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent1 = new Intent(ExportAnswerActivity.this, ImageGridActivity.class);
                                startActivityForResult(intent1, REQUEST_CODE_SELECT);
                                break;
                            default:
                                break;
                        }

                    }
                }, names);


                break;
        }
    }

    ArrayList<ImageItem> images = null;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == SELECT_PROJECT_CODE) {
            String project_name = data.getExtras().getString("project_name");
            String project_address = data.getExtras().getString("project_address");
            projectID = data.getExtras().getInt("project_id");
            userProjectName.setText(project_name);
            userProjectAddress.setText(project_address);
        } else if (resultCode == ImagePicker.RESULT_CODE_ITEMS && data != null) {
            images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
            if (images != null) {
                selImageList.addAll(images);
                adapter.setImages(selImageList);
                String urlStr = BitmapUtils.compressImageUpload(selImageList.get(selImageList.size() - 1).path);
                presenter.uploadImage(urlStr);
            }
        }
    }

    @Override
    public void onAddCommonConsultSuccess(ResultListEntity<String> result) {
        if (result.getCode() == 0) {
            Intent intent = new Intent(this, SubmitSuccessActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "提交失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAddCommonConsultError() {
        Toast.makeText(this, "提交失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetConsultInitSuccess(ResultListEntity<ConsultInitEntity> result) {
        if (result.getCode() == 0) {
            initConsult = result.getResult().get(0);
            userProjectName.setText(initConsult.getUserProjectName());
            userProjectAddress.setText(initConsult.getUserProjectAddress());
            classifyList.addAll(initConsult.getClassifyList());
            partList.addAll(initConsult.getPartList());
            initSpinner();

            projectID = initConsult.getUserProjectID();
            classifyStr = classifyList.get(0);
            partStr = partList.get(0);
        } else {
            myDialog();
            ExportAnswerActivity.this.finish();
        }
    }

    @Override
    public void onGetConsultInitError() {
        myDialog();
    }

    @Override
    public void onUploadImageSuccess(ResultListEntity<String> result) {
        BitmapUtils.deleteCacheFile();
        if (result.getCode() == 0) {
            imageList.add(result.getResult().get(0));
        }
    }

    @Override
    public void onUploadImageError() {
        Toast.makeText(this, "上传失败", Toast.LENGTH_SHORT).show();
    }

    private boolean isSubmit() {
        if (TextUtils.isEmpty(userProjectName.getText())) {
            Toast.makeText(this, "请选择项目", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(descriptionEt.getText())) {
            Toast.makeText(this, "请添加问题描述", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void myDialog() {
        new CommomDialog(ExportAnswerActivity.this, R.style.commom_dialog, "没有项目，请新建项目", new CommomDialog.OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, boolean confirm) {
                if (confirm) {
                    Intent intent = new Intent(ExportAnswerActivity.this, MyProjectActivity.class);
                    startActivity(intent);
                    dialog.dismiss();
                    ExportAnswerActivity.this.finish();
                } else {
                    ExportAnswerActivity.this.finish();
                }

            }
        }).setTitle("提示信息").show();
    }
}
