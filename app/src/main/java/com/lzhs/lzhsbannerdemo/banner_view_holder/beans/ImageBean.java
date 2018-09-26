package com.lzhs.lzhsbannerdemo.banner_view_holder.beans;

import java.io.Serializable;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/9/25$ 下午5:24$ <br/>
 */
public class ImageBean implements Serializable {
    int pathType;
    int imageRes;
    String ImagePath;


    public ImageBean(int pathType, int imageRes, String imagePath) {
        this.pathType = pathType;
        this.imageRes = imageRes;
        ImagePath = imagePath;
    }

    public int getPathType() {
        return pathType;
    }

    public void setPathType(int pathType) {
        this.pathType = pathType;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }
}
