package com.ifoji.common.bll.net;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaozepro on 15/11/2.
 * 请求验证码的操作
 */
public class CaptchaNet extends AsyncTask {

    public static String cookie;
    ImageView mImageView;
    /**
     * 请求的url
     */
    String mUrl;

    public CaptchaNet(String url , ImageView imageView){
        this.mUrl = url;
        this.mImageView = imageView;
    }

    /**
     * 从指定URL获取图片
     * @param url
     * @return
     */
    public static Bitmap getImageBitmap(String url){
        URL imgUrl = null;
        Bitmap bitmap = null;
        try {
            imgUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)imgUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            Map<String, List<String>> headerFields = conn.getHeaderFields();
            cookie = headerFields.get("Set-Cookie").toString();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        return getImageBitmap(mUrl);
    }

    @Override
    protected void onPostExecute(Object object) {
        mImageView.setImageBitmap((Bitmap)object);
    }

}
