package com.g2452.demo.bean;

import java.util.List;

/**
 * 作者：G on 2020/4/22
 * 时间：11:00
 * 概述：
 */
public class HomeBean {

    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2020-04-22 10:00","title":"\u201c神沙\u201d通过环评落户宁夏","description":"人民网环保","picUrl":"","url":"http://env.people.com.cn/n1/2020/0422/c1010-31683125.html"},{"ctime":"2020-04-22 10:00","title":"低碳生活换积分，你还在坚持吗？（说道・我们的绿色生活⑤）","description":"人民网环保","picUrl":"","url":"http://env.people.com.cn/n1/2020/0422/c1010-31683124.html"},{"ctime":"2020-04-22 10:00","title":"贯彻总体国家安全观 开创生态环境领域国家安全工作新局面","description":"人民网环保","picUrl":"","url":"http://env.people.com.cn/n1/2020/0422/c1010-31683122.html"},{"ctime":"2020-04-22 10:00","title":"共识度化为行动力（快评）","description":"人民网环保","picUrl":"http://paper.people.com.cn/rmrb/res/1/20200421/1587408972854_1.jpg","url":"http://env.people.com.cn/n1/2020/0422/c1010-31683121.html"},{"ctime":"2020-04-22 10:00","title":"珍爱地球 人与自然和谐共生（统筹抓好改革发展稳定各项工作）","description":"人民网环保","picUrl":"http://paper.people.com.cn/rmrb/res/1/20200421/1587409031545_1.jpg","url":"http://env.people.com.cn/n1/2020/0422/c1010-31683120.html"},{"ctime":"2020-04-22 10:00","title":"居延海 漾清波（大江大河・黑河(下)）","description":"人民网环保","picUrl":"http://paper.people.com.cn/rmrbhwb/res/2020-04/21/08/rmrbhwb2020042108p36_b.jpg","url":"http://env.people.com.cn/n1/2020/0422/c1010-31683123.html"},{"ctime":"2020-04-22 10:00","title":"让绿色成为最动人的色彩(望海楼)","description":"人民网环保","picUrl":"http://paper.people.com.cn/rmrb/res/1/20200422/1587497634259_1.jpg","url":"http://env.people.com.cn/n1/2020/0422/c1010-31683182.html"},{"ctime":"2020-04-22 10:00","title":"世界地球日：珍爱地球 人与自然和谐共生","description":"人民网环保","picUrl":"http://paper.people.com.cn/rmrb/res/1/20200422/1587497651789_1.jpg","url":"http://env.people.com.cn/n1/2020/0422/c1010-31683431.html"},{"ctime":"2020-04-21 09:00","title":"公勺公筷引领文明新风尚","description":"人民网环保","picUrl":"","url":"http://env.people.com.cn/n1/2020/0421/c1010-31681446.html"},{"ctime":"2020-04-21 09:00","title":"戈壁植绿 中水助力","description":"人民网环保","picUrl":"","url":"http://env.people.com.cn/n1/2020/0421/c1010-31681459.html"}]
     */

    private int code;
    private String msg;
    private List<NewslistBean> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistBean {
        /**
         * ctime : 2020-04-22 10:00
         * title : “神沙”通过环评落户宁夏
         * description : 人民网环保
         * picUrl :
         * url : http://env.people.com.cn/n1/2020/0422/c1010-31683125.html
         */

        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
