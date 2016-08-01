//
// Created by Administrator on 2016/7/7.
//

#include "cn_jjmd_ndkdemo_JniUtils.h"
JNIEXPORT jstring JNICALL Java_cn_jjmd_ndkdemo_JniUtils_getStringFromJNI
        (JNIEnv *env,jclass  jobject){
    return (*env)->NewStringUTF(env,"这是来自C的字符串");
}
