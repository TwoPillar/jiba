/*
 * 系统: jiba
 * 文件名: ImageManager.java
 * 版权: U1CITY Corporation 2015
 * 描述: 
 * 创建人: Administrator
 * 创建时间: 2015-6-10 上午9:41:23
 */
package com.twopillar.jiba.common;

import android.content.Context;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * @author GuoJian'an
 * @date 2015-6-10
 *
 */
public class ImageManager
{   
    private static ImageLoader imageLoader;
    
    private ImageManager(){};
    
    public static synchronized ImageLoader getInstance(Context context) {
        if(imageLoader == null) {
            imageLoader = new ImageLoader(Volley.newRequestQueue(context), new BitmapCache());  
        }
        return imageLoader;
    }
    
}
