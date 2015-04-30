package com.twopillar.jiba.activity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.ant.liao.GifView;
import com.twopillar.jiba.R;

public class MyInfoActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myinfo);
		// 从xml中得到GifView的句柄，当然，事先还要引入GifView的包import com.ant.liao.GifView;
		GifView myGifView = (GifView) findViewById(R.id.img_gif);  
		// 设置Gif图片源，首先要将loading.gif导入到drawable文件内
		myGifView.setGifImage(R.drawable.test);
		Bitmap bgimg0 = getImageFromAssetsFile("Cat_Blink/cat_blink0000.png");
		Drawable drawable =new BitmapDrawable(bgimg0);
		myGifView.setBackgroundDrawable(drawable);
	}

	/**
     * 从Assets中读取图片
     */
    private Bitmap getImageFromAssetsFile(String fileName)
    {
        Bitmap image = null;
        AssetManager am = getResources().getAssets();
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
