package cn.jjmd.ndkdemo;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Base64;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlSerializer;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Administrator on 2015/8/4.
 */
public class Utils {




    // 获取ApiKey
    public static String getMetaValue(Context context, String metaKey) {
        Bundle metaData = null;
        String apiKey = null;
        if (context == null || metaKey == null) {
            return null;
        }
        try {
            ApplicationInfo ai = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            if (null != ai) {
                metaData = ai.metaData;
            }
            if (null != metaData) {
                apiKey = metaData.getString(metaKey);
            }
        } catch (PackageManager.NameNotFoundException e) {

        }
        return apiKey;
    }


    /**
     * 获取6为验证码
     *
     * @return
     */
    public static String getCheckCode() {
        String code = (new Random().nextInt(900000) + 100000) + "";
        return code;
    }

    /**
     * 分享内容
     *
     * @param context
     * @param msg
     */
    public static void share(Context context, String msg) {
        try {
            // TODO Auto-generated method stub
            Intent intent1 = new Intent(Intent.ACTION_SEND);
            intent1.setType("text/plain");
            intent1.putExtra(Intent.EXTRA_SUBJECT, "Share");
            if(isNull(msg)){
                msg="来自帮帮学的分享http://down.palmapp.cn/down/33";
            }
            intent1.putExtra(Intent.EXTRA_TEXT,  msg);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(Intent.createChooser(intent1, ""));

        }catch (Exception e){
            e.printStackTrace();
        }

    }




    public static String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
         if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory(); // 获取根目录
        }
        if (sdDir != null) {
            return sdDir.toString();
        } else {
            return null;
        }
    }

    /**
     * 获取一个文件夹里文件的总大小
     *
     * @return
     */
    public static float getDirSize(File file) {
        float size = 0;
        if (file.exists()) {
            // 如果是目录则递归计算其内容的总大小
            if (file.isDirectory()) {
                File[] children = file.listFiles();
                for (File f : children)
                    size += getDirSize(f);
            } else {// 如果是文件则直接返回其大小,以“兆”为单位
                size = (float) file.length() / 1024 / 1024;
            }
        }
        return size;
    }
    private static String getAvailMemory(Context context) {// 获取android当前可用内存大小

        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        // mi.availMem; 当前系统的可用内存

        return Formatter.formatFileSize(context, mi.availMem);// 将获取的内存大小规格化
    }

    private String getTotalMemory(Context context) {
        String str1 = "/proc/meminfo";// 系统内存信息文件
        String str2;
        String[] arrayOfString;
        long initial_memory = 0;

        try {
            FileReader localFileReader = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(
                    localFileReader, 8192);
            str2 = localBufferedReader.readLine();// 读取meminfo第一行，系统总内存大小

            arrayOfString = str2.split("\\s+");
            for (String num : arrayOfString) {
                Log.i(str2, num + "\t");
            }

            initial_memory = Integer.valueOf(arrayOfString[1]).intValue() * 1024;// 获得系统总内存，单位是KB，乘以1024转换为Byte
            localBufferedReader.close();

        } catch (IOException e) {
        }
        return Formatter.formatFileSize(context, initial_memory);// Byte转换为KB或者MB，内存大小规格化
    }
    public static boolean isNoClipPicture(Context context) {
        try {
            String avable = getAvailMemory(context);
            // MyTool.show(context, avable);
            Log.e("tag", avable);
            // String total=getTotalMemory(context);
            if (!isNull(avable)) {
                if (avable.contains("G"))
                    return true;
                else if (avable.contains("M"))
                    avable = avable.replaceAll("MB", "");

                int avableMB = Integer.parseInt(avable);
                if (avableMB >= 350) {
                    return true;
                } else {
                    return false;
                }

            } else {
                return false;
            }

        } catch (Exception exception) {
            return false;
        }
    }




    /**
     * 删除一个目录下的所有文件
     *
     * @param file 删除目录
     */
    public static void deleteDir(File file) {
        if (file.isDirectory()) {
            for (File _file : file.listFiles()) {
                if (_file.isDirectory()) {
                    deleteDir(_file);
                } else {
                    _file.delete();
                }
            }
        } else {
            file.delete();
        }
    }


    public static String initUserIcon() {
        String path = ImageCache();
        File icon = new File(path, "user_icon.jpg");
        if (!icon.exists()) {
            try {
                icon.createNewFile();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return icon.toString();
    }

    public static String ImageCache() {
        String path = getSDPath() + "/img";
        File file = new File(path);
        if (!file.exists())
            file.mkdir();

        return file.toString();
    }



    public static EditText getEditText(Activity activity, int rid) {
        return (EditText) activity.findViewById(rid);
    }

    public static EditText getEditText(View view, int rid) {
        return (EditText) view.findViewById(rid);
    }

    public static TextView getTextView(Activity activity, int rid) {
        return (TextView) activity.findViewById(rid);
    }

    public static TextView getTextView(View view, int rid) {
        return (TextView) view.findViewById(rid);
    }

    public static ImageView getImageView(Activity activity, int rid) {
        return (ImageView) activity.findViewById(rid);
    }

    public static ImageView getImageView(View view, int rid) {
        return (ImageView) view.findViewById(rid);
    }

    public static View getView(Activity activity, int rid) {
        return activity.findViewById(rid);
    }

    public static View getView(View view, int rid) {
        return view.findViewById(rid);
    }


    public static boolean isMobileNO(String mobiles) {

        String telRegex = "[1][123456789]\\d{9}";
        if (isNull(mobiles))
            return false;
        else
            return mobiles.matches(telRegex);
    }


    /**
     * @param min
     * @param max
     * @return
     */
    public static String getYZM(final int min, final int max) {
        Random random = new Random();
        int randomnum = 0;
        int tmp = Math.abs(random.nextInt());
        randomnum = tmp % (max - min + 1) + min;
        return randomnum + "";
    }

    public static boolean isNull(String val) {
        // Log.d("val", val);
        return TextUtils.isEmpty(val) || "null".equals(val) || null == val;
    }

    public static String getNowTime() {

        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return simple.format(new Date());
    }

    public static Date getToday() {
        String temp_str = "";
        Date dt = new Date();
        // ����aa��ʾ�����硱�����硱 HH��ʾ24Сʱ�� �������hh��ʾ12Сʱ��
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        temp_str = sdf.format(dt);
        try {
            dt = sdf.parse(temp_str);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dt;

    }

    public static String getNowday() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(new Date());
    }
    public static String formatTime(String time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return formatter.format(formatter.parse(time));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }
    public static String formatTime_YYYYMMDD(String time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formatter.format(formatter.parse(time));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }
    public static int getHour() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int hour = c.get(Calendar.HOUR_OF_DAY);
        return hour;
    }
    public static int getYear() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int year = c.get(Calendar.YEAR);
        return year;
    }
    public static int getMonth() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int year = c.get(Calendar.MONTH);
        return year;
    }
    public static int getDayOfMonth() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int year = c.get(Calendar.DAY_OF_MONTH);
        return year;
    }
    public static int getDayOfYear() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int year = c.get(Calendar.DAY_OF_YEAR);
        return year;
    }
    public static int getDayOfWeek() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int year = c.get(Calendar.DAY_OF_WEEK);
        return year;
    }
    public static int getMinutes() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int minute = c.get(Calendar.MINUTE);
        return minute;
    }

    public static String getSecond() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int second = c.get(Calendar.SECOND);
        return Integer.toString(second);
    }




    public static int getAppVersionCode(Context context) {
        int versionCode = -1;
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            versionCode = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    public static String getAppVersionName(Context context) {
        String versionName = null;
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            versionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }


    /**
     * 获取圆角位图的方法
     *
     * @param bitmap 需要转化成圆角的位图
     * @param pixels 圆角的度数，数值越大，圆角越大
     * @return 处理后的圆角位图
     */
    public static Bitmap getRoundBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    public static void showToast(Context context, String str) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }

    public static String getMD5String(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(str.getBytes());
            byte[] bytes = digest.digest();//加密
            //            StringBuffer sb = new StringBuffer();
            //            for(int i = 0; i < bytes.length; i ++){
            //                sb.append(bytes[i]);
            //            }
            return new String(bytes);
            //            sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONArray jsonArrayRemove(JSONArray array, int position) {
        JSONArray newArray = new JSONArray();
        try {
            for (int i = 0; i < array.length(); i++) {
                if (position != i) {
                    newArray.put(array.getJSONObject(i));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newArray;
    }

    public static String imgsToBase64(String filePath) {
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String lastName = filePath.substring(filePath.lastIndexOf(".") + 1);
        if (lastName.equals(Bitmap.CompressFormat.JPEG)) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        } else {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        }
        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
    }

    public static String imgsToBase64(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
    }

    public static void sysout(Object object) {
        System.out.println(object);
    }


    public static String initDownload() {
        String path = getSDPath() + "/download";
        File file = new File(path);
        if (!file.exists())
            file.mkdirs();
        return file.toString();
    }

    public static String bitmapToBase64(Bitmap bitmap) {

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            baos.close();
            byte[] buffer = baos.toByteArray();
            String photo = Base64.encodeToString(buffer, 0, buffer.length,
                    Base64.DEFAULT);
            return photo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }





     public static String initFile(String url) {

        File icon = new File(initDownload(), url.substring(
                url.lastIndexOf("/"), url.length()));
        if (!icon.exists()) {
            try {
                 icon.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return icon.toString();
    }

    // ��ʼ�������ļ�
    public static String getPath(String url) {

        File icon = new File(initDownload(), url.substring(
                url.lastIndexOf("/"), url.length()));
        if (!icon.exists()) {
            try {
                System.out.println(icon.toString());
                icon.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return icon.toString();
    }
    public static  void   shake(Context context){
        Vibrator	 vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        long [] pattern = {100,400,100,400};   // 停止 开启 停止 开启
        vibrator.vibrate(pattern,-1);
    }
    public static String getVersion(Context context) {
        PackageManager pack = context.getPackageManager();
        try {
            PackageInfo info = pack.getPackageInfo(context.getPackageName(), 0);
            return info.versionCode + "";

        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "";
    }




    public static boolean isTest() {
        return true;
    }

    public static boolean saveBitmap2file(Bitmap bmp, String filename) {
        Bitmap.CompressFormat format = Bitmap.CompressFormat.JPEG;
        int quality = 100;
        OutputStream stream = null;
        try {
            stream = new FileOutputStream(filename);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return bmp.compress(format, quality, stream);
    }
}
