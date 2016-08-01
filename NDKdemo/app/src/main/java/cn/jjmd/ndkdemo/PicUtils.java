package cn.jjmd.ndkdemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/1/6.
 */
public class PicUtils {
    private Activity  activity;

    private static final int REQUESTCODE_RESULT_PIC = 213;
    private static final int REQUESTCODE_CAMERA = 1;
    private static final int REQUESTCODE_IMAGES = 2;

    SingleSelectDialog singleSelectDialog;
    PicInterface  picInterface;
    File temp;
    public PicUtils(Activity activity,PicInterface picInterface){
        this.activity=activity;
        if(singleSelectDialog==null)initSingleDialog();
        this.picInterface=picInterface;
    }
    private void initSingleDialog(){
        String[] asd = {"图库","相机"};
        ArrayList list = new ArrayList<String>();
        for (int i = 0; i < asd.length; i++) {
            int type = REQUESTCODE_IMAGES;
            if (i == 1)
                type = REQUESTCODE_CAMERA;
            list.add(new MyString(asd[i], type));
        }
        singleSelectDialog = new SingleSelectDialog(activity, "选择方式", list,
                new SingleSelectDialog.SingleOnclick() {

                    @Override
                    public void singleClick(Object obj) {
                        // TODO Auto-generated method stub
                        MyString m = (MyString) obj;
                        switch (m.getType()) {
                            case REQUESTCODE_IMAGES:
                                Intent intent = new Intent(Intent.ACTION_PICK, null);
                                intent.setDataAndType(
                                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                        "image/*");
                                activity.startActivityForResult(intent, REQUESTCODE_IMAGES);
                                singleSelectDialog.dismiss();
                                break;

                            case REQUESTCODE_CAMERA:
                                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                temp = new File(Utils.initUserIcon());
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(temp));
                                activity.startActivityForResult(intent, REQUESTCODE_CAMERA);
                                singleSelectDialog.dismiss();
                                break;
                            default:
                                break;
                        }
                    }
                });
    }
    public void  getPicTypeSelectShow(){
        if(singleSelectDialog==null)initSingleDialog();
        singleSelectDialog.show();
    }


    public void  onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case REQUESTCODE_IMAGES://
                if (data != null) {
               //      if(Utils.isNoClipPicture(activity)){
                        Bitmap  bitmap=decodeUriAsBitmap(data.getData());
                        if (Utils.saveBitmap2file(bitmap,Utils.initUserIcon())) {
                            BitmapFactory.Options options = new BitmapFactory.Options();
                            options.inJustDecodeBounds = true;// 如果值设为true，那么将不返回实际的bitmap，也不给其分配内存空间，这样就避免了内存溢出。
                            bitmap = BitmapFactory.decodeFile(Utils.initUserIcon(), options);

                            int realwidth = options.outWidth;
                            int realheight = options.outHeight;
                            options.inSampleSize = (realheight / 960 + realwidth / 640) / 2;
                            options.inJustDecodeBounds = false;
                            bitmap = BitmapFactory.decodeFile(Utils.initUserIcon(), options);
                            picInterface.PicSelect(Utils.initUserIcon(), bitmap);



                 /*       }
                        else
                            picInterface.PicSelectError("未保存成功");

                    }else
                          startPhotoZoom(data.getData());*/
                        }
                }
                break;
            case REQUESTCODE_CAMERA:
                if (data!=null&&data.hasExtra("data")) {
                    Bitmap thumbnail =(Bitmap)data.getExtras().get("data");
                    temp = new File(Utils.initUserIcon());
                    if(Utils.isNoClipPicture(activity)){
                        try {
                            temp = new File(Utils.initUserIcon());
                            if( Utils.saveBitmap2file(thumbnail,Utils.initUserIcon())){
                               picInterface.PicSelect(Utils.initUserIcon(),thumbnail);
                            };
                        }catch (Exception e){
                            picInterface.PicSelectError(e.getMessage());
                        }
                    }else{
                        if (Utils.saveBitmap2file(thumbnail, temp.toString())) {
                            startPhotoZoom(Uri.fromFile(temp));
                        }
                    }
                 }else{
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;// 如果值设为true，那么将不返回实际的bitmap，也不给其分配内存空间，这样就避免了内存溢出。

                    Bitmap bitmap=BitmapFactory.decodeFile(Utils.initUserIcon(),options);

                    int realwidth = options.outWidth;
                    int realheight = options.outHeight;


                    options.inSampleSize =(realheight/960+realwidth/640)/2;
                     options.inJustDecodeBounds = false;
                    bitmap = BitmapFactory.decodeFile(Utils.initUserIcon(), options);
                    picInterface.PicSelect(Utils.initUserIcon(),bitmap);
                }


                break;
            case REQUESTCODE_RESULT_PIC:

                try {
                    if (data == null) return;
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        try {
                            temp = new File(Utils.initUserIcon());
                            Bitmap photo = extras.getParcelable("data");
                            FileOutputStream out = new FileOutputStream(temp);
                            photo.compress(Bitmap.CompressFormat.PNG, 100, out);
                            out.flush();
                            out.close();
                            picInterface.PicSelect(Utils.initUserIcon(),photo);
                        }catch (Exception e){
                            picInterface.PicSelectError(e.getMessage());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            default:
                break;
        }



    }

    public  Bitmap decodeUriAsBitmap(Uri uri){
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(activity.getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }

    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        activity.startActivityForResult(intent, REQUESTCODE_RESULT_PIC);
    }

    public interface  PicInterface{
        public void    PicSelect(String path, Bitmap bitmap);
        public void    PicSelectError(String errorMessage);

    }

}
