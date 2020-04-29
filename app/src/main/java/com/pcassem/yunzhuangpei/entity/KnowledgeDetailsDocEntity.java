package com.pcassem.yunzhuangpei.entity;


public class KnowledgeDetailsDocEntity {

        /**
         * id : 17
         * title : test
         * date : 1513180800000
         * readCount : 0
         * fileType : 1
         * fileName : 1513259489467_学生宿舍网络提质与扩建工程项目招标文件(包1)_20171129.doc
         * fileURL : http://p0mi0whax.bkt.clouddn.com/1513259489467_学生宿舍网络提质与扩建工程项目招标文件(包1)_20171129.doc
         * likeCount : 0
         * commentCount : 0
         */

        private int id;
        private String title;
        private long date;
        private int readCount;
        private int fileType;
        private String fileName;
        private String fileURL;
        private int likeCount;
        private int commentCount;

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

        public long getDate() {
            return date;
        }

        public void setDate(long date) {
            this.date = date;
        }

        public int getReadCount() {
            return readCount;
        }

        public void setReadCount(int readCount) {
            this.readCount = readCount;
        }

        public int getFileType() {
            return fileType;
        }

        public void setFileType(int fileType) {
            this.fileType = fileType;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getFileURL() {
            return fileURL;
        }

        public void setFileURL(String fileURL) {
            this.fileURL = fileURL;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }
}
