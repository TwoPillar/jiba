package com.twopillar.jiba.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Environment;

@SuppressLint("NewApi")
public class DownLoadManager {

	/**
	 * 从服务器下载apk
	 * @param path
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public static File getFileFromServer(String path, ProgressDialog pd) throws Exception{
		//如果相等的话表示当前的sdcard挂载在手机上并且是可用的
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			URL url = new URL(path);
			HttpURLConnection conn =  (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			
			//获取到文件的大小 
			int fileSize=conn.getContentLength();
			int newFileSize=FormetFileSize(fileSize);
			pd.setMax(newFileSize);	
			
			InputStream is = conn.getInputStream();
			File file = new File(Environment.getExternalStorageDirectory(), "111.mp4");
			FileOutputStream fos = new FileOutputStream(file);
			BufferedInputStream bis = new BufferedInputStream(is);
			byte[] buffer = new byte[1024];
			int len ;
			int total=0;
			while((len =bis.read(buffer))!=-1){
				fos.write(buffer, 0, len);
				total+= len;
				//获取当前已下载量
				int newTotal=FormetFileSize(total);
				pd.setProgress(newTotal);
				pd.setProgressNumberFormat(newTotal+" kb/"+newFileSize+" kb");  
			}			
			fos.close();
			bis.close();
			is.close();
			return file;
		}
		else{
			return null;
		}
	}
	
	/**
     * 获取文件的大小
     * 
     * @param fileSize 文件的大小
     *            
     * @return
     */
    public static int FormetFileSize(int fileSize) {// 转换文件大小
    	
    	/*DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileSize < 1024) {
            fileSizeString = df.format((double) fileSize) + "B";
        } else if (fileSize < 1048576) {
            fileSizeString = df.format((double) fileSize / 1024) + "K";
        } else if (fileSize < 1073741824) {
            fileSizeString = df.format((double) fileSize / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileSize / 1073741824) + "G";
        }
        return fileSizeString;*/   
    	double fileSizeString = (double) fileSize / 1024;
        return (int)fileSizeString;
    }

}
