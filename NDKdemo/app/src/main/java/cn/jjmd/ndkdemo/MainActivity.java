package cn.jjmd.ndkdemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends Activity implements View.OnClickListener, PicUtils.PicInterface {
    static {
        System.loadLibrary("m_jni");
    }

    ImageView imgHuaishi;
    PicUtils picUtils;
    Button btnProcess, btn_gray_process_java;
    TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time = (TextView) this.findViewById(R.id.helloword);
        time.setText(new JniUtils().getStringFromJNI());
        initUI();
        picUtils = new PicUtils(this, this);

    }

    public void initUI() {
        btnProcess = (Button) findViewById(R.id.btn_gray_process);
        btn_gray_process_java = (Button) findViewById(R.id.btn_gray_process_java);

        imgHuaishi = (ImageView) findViewById(R.id.image);
        imgHuaishi.setOnClickListener(this);
        btnProcess.setOnClickListener(this);
        btn_gray_process_java.setOnClickListener(this);
        this.findViewById(R.id.daochu).setOnClickListener(this);
        this.findViewById(R.id.btn_gray_process_bianyan).setOnClickListener(this);
        this.findViewById(R.id.tiaozhuandaoxiangji).setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        picUtils.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        String vs = null;
        if (v instanceof Button)
            vs = ((Button) v).getText().toString();
        switch (v.getId()) {
            case R.id.btn_gray_process:
                if (vs.equals("灰色处理_jni")) {
                    ((Button) v).setText("查看原图");
                    huisechuli();
                    imgHuaishi.setImageBitmap(graybitmap);

                } else if (vs.equals("查看原图")) {
                    imgHuaishi.setImageBitmap(src);
                    ((Button) v).setText("灰色处理_jni");

                } else {

                }

                break;
            case R.id.btn_gray_process_java:

                if (vs.equals("灰色处理_java")) {
                    ((Button) v).setText("查看原图");
                    getGrayBitmap();
                    imgHuaishi.setImageBitmap(graybitmap);

                } else if (vs.equals("查看原图")) {
                    imgHuaishi.setImageBitmap(src);
                    ((Button) v).setText("灰色处理_java");

                } else {

                }

                break;
            case R.id.btn_gray_process_bianyan:
                bianyuanjiance();
                imgHuaishi.setImageBitmap(graybitmap);
                break;
            case R.id.image:
                picUtils.getPicTypeSelectShow();
                break;
            case R.id.daochu:
                daochu();
                break;
            case R.id.tiaozhuandaoxiangji:
                Intent in = new Intent(this, cameraActivity.class);
                startActivity(in);

                break;
        }
    }

    private void daochu() {
        BufferedOutputStream os = null;
        try {
            if (graybitmap == null) {
                graybitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.test);

            }

            File file = new File(cn.jjmd.ndkdemo.Utils.getSDPath() + "/xuhuaimg.jpg");
            // String _filePath_file.replace(File.separatorChar +
            // file.getName(), "");
            if (!file.exists())
                file.createNewFile();
            os = new BufferedOutputStream(new FileOutputStream(file));
            graybitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
            Toast.makeText(this, "成功！", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "失败！", Toast.LENGTH_SHORT).show();

        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {

                }
            }
        }


    }

    Bitmap src, graybitmap;

    void huisechuli() {
        long tim1 = System.currentTimeMillis();
        if (src == null)
            src = BitmapFactory.decodeResource(getResources(), R.mipmap.test);
        Mat graymat = new Mat(src.getWidth(), src.getHeight(), CvType.CV_8UC3);
        Mat rgbMat = new Mat(src.getWidth(), src.getHeight(), CvType.CV_8UC3);
        Utils.bitmapToMat(src, rgbMat);
        Imgproc.cvtColor(rgbMat, graymat, Imgproc.COLOR_RGB2GRAY);

      /*  Rect rec = new Rect(0, 0, mat2.cols(), mat2.rows());
        // submat(y坐标, 图片2的高, x坐标，图片2的宽);
        mat1Sub = mat1.submat(rec);
        mat2.copyTo(mat1Sub);*/
        graybitmap = Bitmap.createBitmap(src.getWidth(), src.getHeight(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(graymat, graybitmap);
        time.setText((System.currentTimeMillis() - tim1) + "");

    }


    public void getGrayBitmap() {
        long tim1 = System.currentTimeMillis();
        if (src == null)
            src = BitmapFactory.decodeResource(getResources(), R.mipmap.test);
        graybitmap = Bitmap.createBitmap(src.getWidth(), src.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas mCanvas = new Canvas(graybitmap);
        Paint mPaint = new Paint();
        //创建颜色变换矩阵
        ColorMatrix mColorMatrix = new ColorMatrix();
        //设置灰度影响范围
        mColorMatrix.setSaturation(0);
        //创建颜色过滤矩阵
        ColorMatrixColorFilter mColorFilter = new ColorMatrixColorFilter(mColorMatrix);
        //设置画笔的颜色过滤矩阵
        mPaint.setColorFilter(mColorFilter);
        //使用处理后的画笔绘制图像
        mCanvas.drawBitmap(src, 0, 0, mPaint);
        time.setText((System.currentTimeMillis() - tim1) + "");


    }

    private void bianyuanjiance() {
        if (src == null)
            src = BitmapFactory.decodeResource(getResources(), R.mipmap.test);
        Mat img = new Mat(src.getWidth(), src.getHeight(), CvType.CV_8UC3);
        graybitmap = Bitmap.createBitmap(src.getWidth(), src.getHeight(), Bitmap.Config.ARGB_8888);

        //  Mat img = Highgui.imread("/sdcard/face8.jpg");
        Size dSize = new Size((double) img.width(), (double) img.height());
        Mat img2 = new Mat(dSize, CvType.CV_8SC1);
        Mat img3 = new Mat();
        Utils.bitmapToMat(src, img);
        img.convertTo(img2, CvType.CV_8UC3);
        Imgproc.Canny(img, img3, 123, 250);

        Utils.matToBitmap(img3, graybitmap);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!OpenCVLoader.initDebug()) { //默认加载opencv_java.so库
            baseLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
//加载依赖opencv_java.so的jni库
            //  System.loadLibrary("opencv_java.so");
        }

    }

    BaseLoaderCallback baseLoaderCallback =

            new BaseLoaderCallback(this) {
                @Override
                public void onManagerConnected(int status) {
                    super.onManagerConnected(status);
                    switch (status) {
                        case BaseLoaderCallback.SUCCESS:
                            Log.e("tag", "成功加载");
                            break;
                        default:
                            Log.e("tag", "加载失败");
                            break;
                    }
                }
            };

    @Override
    public void PicSelect(String path, Bitmap bitmap) {
        src = bitmap;
        imgHuaishi.setImageBitmap(src);
    }

    @Override
    public void PicSelectError(String errorMessage) {

    }
}
