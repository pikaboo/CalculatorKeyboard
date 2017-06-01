#include <jni.h>
#include <string>

extern "C"
jstring
Java_com_lenabru_keyboard_nativeinterface_Calculator_stringFromJNI(
        JNIEnv *env,
        jobject object/* this */) {
    //    简单返回个字符串
    std::string hello = "Hello from Native.";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
jint Java_com_lenabru_keyboard_nativeinterface_Calculator_add(JNIEnv *env, jobject object,jint x, jint y) {
    return x+y;
}
extern "C"
jint Java_com_lenabru_keyboard_nativeinterface_Calculator_subtract(JNIEnv *env, jobject object,jint x, jint y) {
    return x-y;
}
extern "C"
jfloat Java_com_lenabru_keyboard_nativeinterface_Calculator_divide(JNIEnv *env, jobject object,jfloat x, jfloat y) {
    return x/y;
}
extern "C"
jint Java_com_lenabru_keyboard_nativeinterface_Calculator_multiply(JNIEnv *env, jobject object,jint x, jint y) {
    return x*y;
}