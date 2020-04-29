package com.pcassem.yunzhuangpei.entity;

/**
 * Created by zhangqi on 2017/12/16.
 */

public class CourseDetailsEntity {

        /**
         * id : 1
         * title : 培训课程测试数据1
         * icon : http://p0mi0whax.bkt.clouddn.com/demo/icon/course.jpg
         * date : 1513008000000
         * address : 长沙市
         * sponsor : 远大
         * fee : 1000
         * totalCount : 100
         * signCount : 20
         * content : dsfasfasdf风洒点发送到发
         * signStatus : 3
         */

        private int id;
        private String title;
        private String icon;
        private long date;
        private String address;
        private String sponsor;
        private int fee;
        private int totalCount;
        private int signCount;
        private String content;
        private int signStatus;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public long getDate() {
            return date;
        }

        public void setDate(long date) {
            this.date = date;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getSponsor() {
            return sponsor;
        }

        public void setSponsor(String sponsor) {
            this.sponsor = sponsor;
        }

        public int getFee() {
            return fee;
        }

        public void setFee(int fee) {
            this.fee = fee;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getSignCount() {
            return signCount;
        }

        public void setSignCount(int signCount) {
            this.signCount = signCount;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getSignStatus() {
            return signStatus;
        }

        public void setSignStatus(int signStatus) {
            this.signStatus = signStatus;
        }
}
