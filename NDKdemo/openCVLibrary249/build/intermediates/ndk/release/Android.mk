LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := openCVLibrary249
LOCAL_LDFLAGS := -Wl,--build-id
LOCAL_SRC_FILES := \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\android.toolchain.cmake \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\OpenCV.mk \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\OpenCVConfig-version.cmake \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\OpenCVConfig.cmake \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\OpenCVModules_armeabi-release.cmake \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\OpenCVModules_armeabi.cmake \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\OpenCVModules_armeabi_v7a-release.cmake \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\OpenCVModules_armeabi_v7a.cmake \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\OpenCVModules_mips-release.cmake \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\OpenCVModules_mips.cmake \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\OpenCVModules_x86-release.cmake \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\OpenCVModules_x86.cmake \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv\cv.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv\cvaux.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv\cxcore.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv\cxeigen.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\opencv.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\opencv_modules.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\calib3d\calib3d.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\contrib\contrib.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\contrib\detection_based_tracker.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\contrib\hybridtracker.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\contrib\openfabmap.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\contrib\retina.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\core\affine.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\core\core.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\core\cuda_devptrs.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\core\devmem2d.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\core\eigen.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\core\gpumat.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\core\internal.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\core\mat.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\core\opengl_interop.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\core\opengl_interop_deprecated.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\core\operations.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\core\version.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\core\wimage.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\features2d\features2d.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\flann\flann.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\flann\flann_base.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\flann\miniflann.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\highgui\highgui.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\imgproc\imgproc.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\legacy\blobtrack.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\legacy\compat.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\legacy\legacy.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\legacy\streams.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\ml\ml.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\objdetect\objdetect.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\ocl\matrix_operations.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\ocl\ocl.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\photo\photo.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\stitching\stitcher.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\stitching\warpers.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\stitching\detail\autocalib.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\stitching\detail\blenders.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\stitching\detail\camera.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\stitching\detail\exposure_compensate.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\stitching\detail\matchers.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\stitching\detail\motion_estimators.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\stitching\detail\seam_finders.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\stitching\detail\util.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\stitching\detail\util_inl.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\stitching\detail\warpers.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\stitching\detail\warpers_inl.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\superres\optical_flow.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\superres\superres.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\ts\gpu_perf.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\ts\gpu_test.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\ts\ts.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\ts\ts_perf.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\video\background_segm.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\video\tracking.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\video\video.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\videostab\deblurring.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\videostab\fast_marching.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\videostab\fast_marching_inl.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\videostab\frame_source.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\videostab\global_motion.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\videostab\inpainting.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\videostab\log.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\videostab\motion_stabilizing.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\videostab\optical_flow.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\videostab\stabilizer.hpp \
	C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni\include\opencv2\videostab\videostab.hpp \

LOCAL_C_INCLUDES += C:\Users\Administrator\NDKdemo\openCVLibrary249\src\main\jni
LOCAL_C_INCLUDES += C:\Users\Administrator\NDKdemo\openCVLibrary249\src\release\jni

include $(BUILD_SHARED_LIBRARY)
