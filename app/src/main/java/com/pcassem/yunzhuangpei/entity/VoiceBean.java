package com.pcassem.yunzhuangpei.entity;

import java.util.List;

/**
 * Created by zhangqi on 2017/12/21.
 */

public class VoiceBean {
    private List<WsBean> ws;

    public List<WsBean> getWs() {
        return ws;
    }

    public void setWs(List<WsBean> ws) {
        this.ws = ws;
    }

    public static class WsBean {

        private List<CwBean> cw;

        public List<CwBean> getCw() {
            return cw;
        }

        public void setCw(List<CwBean> cw) {
            this.cw = cw;
        }

        public static class CwBean {

            private String w;

            public String getW() {
                return w;
            }

            public void setW(String w) {
                this.w = w;
            }
        }
    }
}
