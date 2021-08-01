package com.cyq.retrofit.reflex.utils;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class FileUtil {
    public static String copyFilesFromAssets(Context context, String assetsPath, String savePath) {
        try {
            InputStream is = context.getAssets().open(assetsPath);
            if (!savePath.endsWith("/")){
                savePath += "/";
            }
            FileOutputStream fos = new FileOutputStream(new File(savePath + assetsPath));
            byte[] buffer = new byte[1024];
            int byteCount = 0;
            while ((byteCount = is.read(buffer)) != -1) {// 循环从输入流读取
                // buffer字节
                fos.write(buffer, 0, byteCount);// 将读取的输入流写入到输出流
            }
            fos.flush();// 刷新缓冲区
            is.close();
            fos.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
           return null;
        }
        return savePath + assetsPath;
    }
}
