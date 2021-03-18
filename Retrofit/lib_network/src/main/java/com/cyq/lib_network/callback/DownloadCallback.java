package com.cyq.lib_network.callback;

import android.content.Context;
import android.util.Log;

import com.cyq.lib_network.HttpError;
import com.cyq.lib_network.RetrofitManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author chenyq113@midea.com
 * @describe xxx
 * @time 2021/3/18 9:48
 */
public abstract class DownloadCallback<T> extends DownloadProgressCallback<ResponseBody> {

    private String mFileName;

    public DownloadCallback(String fileName) {
        mFileName = fileName;
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        super.onResponse(call, response);
        if (response.isSuccessful()) {
            saveInputStreamToDisk(response.body());
        } else {
            HttpError httpError = new HttpError(-1, "请求失败！");
            onError(call, httpError);
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        HttpError httpError = new HttpError(-1, "请求失败2！");
        onError(call, httpError);
    }

    protected abstract void onSuccess(File file);

    protected abstract void onError(Call<ResponseBody> call, HttpError error);

    private void saveInputStreamToDisk(ResponseBody body) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        Context context = RetrofitManager.getInstance().getContext();
        if (context == null) {
            throw new NullPointerException("context is empty,you should do it before RetrofitManager.getInstance().initClient()!");
        }
        try {  //创建要存储的文件
            File targetFile = new File(context.getExternalFilesDir(null)
                    + File.separator + mFileName);
            if (!targetFile.exists()) {
                targetFile.createNewFile();
            }
            float fileSize = body.contentLength();
            float currentLen = 0F;
            inputStream = body.byteStream();
            outputStream = new FileOutputStream(targetFile);
            int len;
            byte[] buff = new byte[1024];
            while ((len = inputStream.read(buff)) != -1) {
                outputStream.write(buff, 0, len);
                currentLen += len;
                final float currentProgress = currentLen / fileSize;
                onProgress(currentProgress);
            }

            outputStream.flush();
            onSuccess(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
