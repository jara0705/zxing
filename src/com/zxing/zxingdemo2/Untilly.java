package com.zxing.zxingdemo2;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * 几个公用的方法
 * 1.自定义图片
 * 2.读文件
 * 3.保存到sd卡中
 * @author Jiaqi
 *
 */
public class Untilly {

	public static Bitmap zoomBitmap(Bitmap icon,int h){
		//缩放图片
		Matrix m = new Matrix();
		float sx = (float)2*h/icon.getWidth();
		float sy = (float)2*h/icon.getHeight();
		m.setScale(sx, sy);
		//重新构建一个2h*2h的图片
		return Bitmap.createBitmap(icon,0,0,icon.getWidth(),icon.getHeight(),m,false);
	}
}
