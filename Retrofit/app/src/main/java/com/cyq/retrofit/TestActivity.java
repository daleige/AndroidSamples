package com.cyq.retrofit;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cyq.retrofit.utils.FileUtil;
import com.cyq.retrofit.utils.SystemUtils;
import com.smartunion.iot.IBootstrapListener;
import com.smartunion.iot.entity.VendorInfo;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * @author chenyq113@midea.com
 * @describe xxx
 * @time 2021/1/29 23:15
 */
public class TestActivity extends AppCompatActivity {
    private String pluginApkName = "sdk.ov.midea.com.mideaovsdk_2_85.apk";  ///< 插件apk名称
    private String apkPath;         ///< apk存储路径
    private String apkDexPath;      ///< apk解压dex的目录、和apk存放路径为一个路径
    private DexClassLoader dexClassLoader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

//        apkDexPath = getExternalFilesDir(null)+
//                File.separator + "ColorOS" + File.separator + "IoT" + File.separator + "testsdk";
//        apkPath = apkDexPath + File.separator + "sdk.ov.midea.com.mideaovsdk_2_85.apk";
//        Log.d("test", "apxPaht:" + apkPath);
//        Log.d("test", "apkDexPath:" + apkDexPath);
//        dexClassLoader = new DexClassLoader(apkPath, apkDexPath, null, this.getClassLoader());

        ///< 获取apk准备存储的应用本地缓存路径
        this.apkDexPath = SystemUtils.getCacheDirectory(this, Environment.DIRECTORY_DOWNLOADS).getPath();
        ///< 拷贝assets下的plugin-debug.apk到apkPath目录并获取实际路径
        this.apkPath = FileUtil.copyFilesFromAssets(this, pluginApkName, apkDexPath);
        ///< 加载apk并获取DexClassLoader对象
        Log.d("test", "apxPaht:" + apkPath);
        Log.d("test", "apkDexPath:" + apkDexPath);
        this.dexClassLoader = new DexClassLoader(apkPath, apkDexPath, null, this.getClassLoader());

        reflexClass(dexClassLoader);
    }

    private void reflexClass(DexClassLoader dexClassLoader) {
        try {
            ///< 加载插件的类(插件的包名.类名)
            Class<?> mClass = dexClassLoader.loadClass("sdk.ov.midea.com.mideaovsdk.IotPluginImp");
            ///< 获取类的实例
            Object beanObject = mClass.newInstance();
            ///< 然后通过反射获取对应的方法
            Method method = mClass.getMethod("onCreate", VendorInfo.class, IBootstrapListener.class, Context.class);
            method.setAccessible(true);
            ///< 然后执行对应方法进行相关设置和获取
            method.invoke(beanObject, null,null,null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
