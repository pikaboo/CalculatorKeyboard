package com.lenabru.keyboard;

import android.app.Application;
import com.lenabru.keyboard.nativeinterface.NativeLibrariesManager;

public class KeyboardApplication
		extends Application {

  @Override public void onCreate() {
    super.onCreate();
    loadNativeLibraries();
  }

  protected void loadNativeLibraries() {
    NativeLibrariesManager.loadNativeLibraries();
  }
}
