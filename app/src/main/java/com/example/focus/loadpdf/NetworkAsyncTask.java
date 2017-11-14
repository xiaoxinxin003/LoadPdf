package com.example.focus.loadpdf;

import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by focus on 2017/11/14.
 */

public class NetworkAsyncTask {

    private static final String TAG = "my-log";

    public static void AsyncRequest(final String url, final NetCallBack callBack){
        Thread thread = new Thread() {
            @Override
            public void run() {
                reqeust(url, callBack);
            }
        };
        thread.start();
    }

    private static void reqeust(String url, NetCallBack callBack) {
        Log.d(TAG, "request pdf on thread :"+ Thread.currentThread().getName());
        try{
        // 新建一个URL对象
        URL requestUrl = new URL(url);
        // 打开一个HttpURLConnection连接
        HttpURLConnection urlConn = (HttpURLConnection) requestUrl.openConnection();
        // 设置连接主机超时时间
                urlConn.setConnectTimeout(5 * 1000);
        //设置从主机读取数据超时
                urlConn.setReadTimeout(5 * 1000);
        // 设置是否使用缓存  默认是true
                urlConn.setUseCaches(true);
        // 设置为Post请求
                urlConn.setRequestMethod("GET");
        //urlConn设置请求头信息
        //设置请求中的媒体类型信息。
                urlConn.setRequestProperty("Content-Type", "application/json");
        //设置客户端与服务连接类型
                urlConn.addRequestProperty("Connection", "Keep-Alive");
        // 开始连接
                urlConn.connect();
        // 判断请求是否成功
                if (urlConn.getResponseCode() == 200) {
            // 获取返回的数据
                 callBack.onSuccess(urlConn.getInputStream());
        } else {
            Log.e(TAG, "Get方式请求失败");
        }
        // 关闭连接
//                urlConn.disconnect();
        } catch (Exception e) {
            callBack.onError(e);
            Log.e(TAG, e.toString());
        }
    }

}
