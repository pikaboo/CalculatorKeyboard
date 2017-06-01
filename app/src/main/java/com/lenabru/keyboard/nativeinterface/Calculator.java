package com.lenabru.keyboard.nativeinterface;

/**
 * Created by rocko on 2016/11/26.
 */

public class Calculator {


  public native int add(int x, int y);
  public native int subtract(int x, int y);
  public native float divide(float x, float y);
  public native int multiply(int x, int y);
}
