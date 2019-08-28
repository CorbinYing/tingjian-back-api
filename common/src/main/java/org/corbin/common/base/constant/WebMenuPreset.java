package org.corbin.common.base.constant;

public enum WebMenuPreset {
    recommend(1,"今日热点"),
    newsong(2,"新歌速递"),
    hotsong(3,"热门神曲"),
    singersong(4,"爱豆之家"),
    heartSong(5,"心动模式"),
    collectsong(6,"个人私藏"),
    accountcenter(7,"账号中心");


    private String menuName;
    private Integer menuCode;

    WebMenuPreset(Integer menuCode,String menuName){
        this.menuCode=menuCode;
        this.menuName=menuName;
    }

}
