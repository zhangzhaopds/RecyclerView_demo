package com.tongxingpay.zhangzhao.recyclerview_demo;

import android.content.Context;

/**
 * Created by zhangzhao on 2016/9/29.
 */

public class Actor {
    String actorName;
    String picName;
    public Actor(String name, String pic) {
        this.actorName = name;
        this.picName = pic;
    }

    // 根据图片名找到系统资源
    public int getImageResourceId(Context context) {
        try {
            return context.getResources().getIdentifier(this.picName, "drawable", context.getPackageName());

        } catch (Exception e) {
            return -1;
        }
    }
}
