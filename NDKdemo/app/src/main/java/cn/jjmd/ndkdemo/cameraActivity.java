package cn.jjmd.ndkdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.NativeCameraView;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 * Created by Administrator on 2016/7/18.
 */
public class cameraActivity extends Activity implements CameraBridgeViewBase.CvCameraViewListener2 {

    private NativeCameraView mOpenCvCameraView;
    Mat mRgba;
    boolean isProcess=true;
    @Override
    public void onCameraViewStarted(int width, int height) {
        mRgba = new Mat(height, width, CvType.CV_8UC4);
    }

    @Override
    public void onCameraViewStopped() {
        mRgba.release();

    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        if(isProcess)
        Imgproc.cvtColor(inputFrame.gray(), mRgba, Imgproc.COLOR_GRAY2RGBA, 4);
        else
        mRgba = inputFrame.rgba();
        return mRgba;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        mOpenCvCameraView = (NativeCameraView) findViewById(R.id.tutorial1_activity_native_surface_view);
        mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);
        mOpenCvCameraView.setCvCameraViewListener(this);
         View mBtn =  findViewById(R.id.fab);
         mBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 isProcess = !isProcess;
             }
         });
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (OpenCVLoader.initDebug()) { //默认加载opencv_java.so库
            Log.e("tag", "OpenCVLoader.initDebug()");
            System.loadLibrary("opencv_java");

            baseLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
//加载依赖opencv_java.so的jni库
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
                            mOpenCvCameraView.enableView();

                            break;
                        default:
                            Log.e("tag", "加载失败");
                            break;
                    }
                }
            };


}
