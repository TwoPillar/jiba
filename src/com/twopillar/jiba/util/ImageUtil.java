package com.twopillar.jiba.util;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageUtil {
	
	/**
     * 从Assets中读取图片
     */
    public static Bitmap getImageFromAssetsFile(String fileName,Resources r)
    {
        Bitmap image = null;
        AssetManager am = r.getAssets();
        try
        {
            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return image;

        
    }
}
