package com.zxing.zxingdemo2;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * �������õķ���
 * 1.�Զ���ͼƬ
 * 2.���ļ�
 * 3.���浽sd����
 * @author Jiaqi
 *
 */
public class Untilly {

	public static Bitmap zoomBitmap(Bitmap icon,int h){
		//����ͼƬ
		Matrix m = new Matrix();
		float sx = (float)2*h/icon.getWidth();
		float sy = (float)2*h/icon.getHeight();
		m.setScale(sx, sy);
		//���¹���һ��2h*2h��ͼƬ
		return Bitmap.createBitmap(icon,0,0,icon.getWidth(),icon.getHeight(),m,false);
	}
}
