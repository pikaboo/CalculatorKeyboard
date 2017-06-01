package com.lenabru.keyboard.base;

import android.os.Build;

import com.lenabru.keyboard.BuildConfig;

import org.robolectric.annotation.Config;

/**
 * Native Libraries support for testing from
 * https://github.com/zhengxiaopeng/RobolectricSupportNativeLibs
 */
@Config(sdk = Build.VERSION_CODES.KITKAT, application = RobolectricApplication.class, constants = BuildConfig.class)
public class BaseRobolectricTestCase extends TestWithDescription{


}
