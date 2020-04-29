package com.pcassem.yunzhuangpei.entity;


import java.util.List;

/**
 * Created by zhangqi on 2017/12/25.
 */
public class SearchListEntity {

    /**
     * code : 0
     * keywords : ["吊装","叠合楼板","楼板"]
     * result : [{"id":51,"title":"外墙板外挂体系（楼板）图纸介绍","isFile":1},{"id":58,"title":"楼板面控制线放设不全","isFile":1},{"id":65,"title":"吊装工具介绍","isFile":1},{"id":168,"title":"吊装工程-叠合梁吊装工艺流程","isFile":1},{"id":170,"title":"吊装工程-内墙板吊装工艺流程","isFile":1},{"id":171,"title":"吊装工程-隔墙板吊装工艺流程","isFile":1},{"id":179,"title":"吊装工程-叠合楼板吊装工艺流程","isFile":1},{"id":360,"title":"PC构件工程-墙板吊装顺序错乱","isFile":1},{"id":380,"title":"PC构件工程-叠合楼板安装后板底不平","isFile":1},{"id":381,"title":"PC构件工程-叠合楼板安装不平直露板缝","isFile":1},{"id":382,"title":"PC构件工程-叠合楼板安装板面开裂","isFile":1},{"id":383,"title":"PC构件工程-隔墙叠合楼板安装节点遗漏","isFile":1},{"id":385,"title":"PC构件工程-阳台、空调板安吊装定性差","isFile":1},{"id":395,"title":"混凝土工程-叠合楼板板面超高","isFile":1},{"id":400,"title":"PC构件工程-吊装操作人员吊点设置不对","isFile":1},{"id":417,"title":"叠合楼板板底水平拼缝施工要点","isFile":1},{"id":431,"title":"室内装修工程-预制楼板与预制墙梁出现裂缝","isFile":1},{"id":433,"title":"PC构件工程-叠合楼板安装板面开裂","isFile":1},{"id":434,"title":"PC构件工程-叠合楼板安装后不平直露板缝","isFile":1}]
     */

    private int code;
    private List<String> keywords;
    private List<KnowledgeEntity> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public List<KnowledgeEntity> getResult() {
        return result;
    }

    public void setResult(List<KnowledgeEntity> result) {
        this.result = result;
    }

}
