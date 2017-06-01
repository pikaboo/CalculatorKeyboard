package com.lenabru.keyboard.base;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.lenabru.keyboard.KeyboardApplication;

import org.robolectric.shadows.ShadowLog;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RobolectricApplication
		extends KeyboardApplication {

	private static final String TAG = RobolectricApplication.class.getSimpleName();

	static {
		ShadowLog.stream = System.out; //Android logcat output.
	}

	@Override
	protected void loadNativeLibraries() {
		//Disable super class load so file.
		//super.loadNativeLibraries();
		Log.d(TAG, "=====>> Robolectric load native libraries.");

		String libsBasePath = new File(new File("").getAbsolutePath() + "/app/src/test/libs").getAbsolutePath();
		String os = System.getProperty("os.name");
		os = !TextUtils.isEmpty(os) ? os : "";
		List<File> soFileList = new ArrayList<>();
		String systemArchPath = libsBasePath + "/framework/";
		//!!! 64
		if (os.contains("Mac")) {
			//load system library if need
			String macSysSoBasePath = systemArchPath + "macOS/";
			soFileList.addAll(addLibs(macSysSoBasePath));
			// App so...
			String macAppSoPath = libsBasePath + "/macOS_x86-64/";
			// mac下so要使用macOS专用库
			soFileList.addAll(addLibs(macAppSoPath));
		} else if (os.contains("Linux")) {
			//load system library if need
			String linuxSysSoBasePath = systemArchPath + "arch_x86-64/";
			soFileList.addAll(addLibs(linuxSysSoBasePath));
			// App so...
			String linuxAppSoPath = libsBasePath + "/linux_x86-64/";
			soFileList.addAll(addLibs(linuxAppSoPath));
		} else if (os.contains("Windows")) {
			//
		}

		for (File soFie : soFileList) {
			System.load(soFie.getAbsolutePath());
		}
	}

	private List<File> addLibs(@NonNull String path) {
		File[] basePathFiles = new File(path).listFiles();
		List<File> pathFilesList = new ArrayList<>();
		if (basePathFiles != null && basePathFiles.length > 0) {
			pathFilesList.addAll(Arrays.asList(basePathFiles));
		}
		return pathFilesList;
	}
}
