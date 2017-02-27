package com.ximsfei.skindemo.bean;

/**
 * Created by pengfengwang on 2017/1/15.
 */

public class ImageItem {
    public ImageItem() {
    }

    public ImageItem(String title, String subtitle, int image) {
        this.title = title;
        this.subtitle = subtitle;
        this.image = image;
    }

    public String title;
    public String subtitle;
    public int image;
}
