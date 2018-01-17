package wangxuewei.bwie.com.dingdong.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

import wangxuewei.bwie.com.dingdong.R;

/**
 * Created by jim on 2017/12/12.
 */

public class ImageLoaderUtils {
    /**
     * ImageLoader的配置
     *
     * @param context
     */
    public static void initConfig(Context context) {
        //配置
//        File cacheFile=context.getExternalCacheDir();
        File cacheFile = new File(Environment.getExternalStorageDirectory() + "/" + "imgages");

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .memoryCacheExtraOptions(480, 800)//缓存图片最大的长和宽
                .threadPoolSize(2)//线程池的数量
                .threadPriority(4)
                .memoryCacheSize(2 * 1024 * 1024)//设置内存缓存区大小
                .diskCacheSize(20 * 1024 * 1024)//设置sd卡缓存区大小
                .diskCache(new UnlimitedDiscCache(cacheFile))//自定义缓存目录
                .writeDebugLogs()//打印日志内容
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())//给缓存的文件名进行md5加密处理
                .build();

        ImageLoader.getInstance().init(config);

    }

    /**
     * 获取图片设置类
     *
     * @return
     */
    public static DisplayImageOptions getImageOptions() {

        DisplayImageOptions optionsoptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)//使用内存缓存
                .cacheOnDisk(true)//使用磁盘缓存
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片格式
                .showImageOnLoading(R.mipmap.ic_launcher)//设置正在下载的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher)//url为空或请求的资源不存在时
                .showImageOnFail(R.mipmap.ic_launcher)//下载失败时显示的图片
//                .displayer(new RoundedBitmapDisplayer(30))//设置圆角，参数代表度数
                .build();

        return optionsoptions;

    }


}
