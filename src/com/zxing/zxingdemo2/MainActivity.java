package com.zxing.zxingdemo2;

import java.io.IOException;
import java.nio.BufferUnderflowException;

import com.google.zxing.WriterException;
import com.zxing.utils.activity.CaptureActivity;
import com.zxing.utils.encoding.EncodingHandler;

import android.R.integer;
import android.app.Activity;
import android.bluetooth.le.ScanCallback;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.ParcelFileDescriptor.OnCloseListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private Button scan_btn, gene_btn, back_btn, fore_btn;
	private EditText content;
	private ImageView imageView;
	private String con_str, url_str;
	int FOREGROUND_COLOR = 0xff000000;// ǰ��ɫ Ĭ��Ϊ��ɫ
	int BACKGROUND_COLOR = 0xffffffff;// ����ɫ Ĭ��Ϊ��ɫ

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// ��ʼ��
		init();

		// �����ص���Ӧ�¼������ȥ
		scan_btn.setOnClickListener(this);
		gene_btn.setOnClickListener(this);
		back_btn.setOnClickListener(this);
		fore_btn.setOnClickListener(this);

	}

	// ��ʼ���ؼ�
	public void init() {
		scan_btn = (Button) findViewById(R.id.button1);
		gene_btn = (Button) findViewById(R.id.button2);
		back_btn = (Button) findViewById(R.id.button3);
		fore_btn = (Button) findViewById(R.id.button4);
		content = (EditText) findViewById(R.id.editText1);
		imageView = (ImageView) findViewById(R.id.imageView1);
	}

	// Ϊ�����ť���ü����¼�����д�˷�����Ҫʵ��OnclickListener�ӿ�
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button1:
			generate();
			break;
		case R.id.button2:
			scan();
			break;
		case R.id.button3:
			onBJS();
			break;
		case R.id.button4:
			onQJS();
			break;
		}
	}

	// ɨ�蹦��
	public void scan() {
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, CaptureActivity.class);
		startActivityForResult(intent, 0); // ��Ҫ�ڶ���activity��ֵ
	}

	// ���ɶ�ά��
	public void generate() {
		con_str = content.getText().toString();
		// url_str = "http://222.195.151.10:8080/demo?id="+con_str;
		Resources r;
		r = this.getBaseContext().getResources();
		if (!con_str.equals("")) {
			try {
				//���ɶ�ά�� �����Զ������е���ɫ
				Bitmap qrcodemap = EncodingHandler.createQrCode2(con_str, 300,
						r, BACKGROUND_COLOR, FOREGROUND_COLOR);
				imageView.setImageBitmap(qrcodemap);
			} catch (WriterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Toast.makeText(MainActivity.this, "This is empty...", 1000).show();
		}

	}

	//���ö�ά��ǰ��ɫ��һ��Ĭ��Ϊ��ɫ���ǲ��֣�
	public void onQJS() {
		ColorPickerDialog dialog = new ColorPickerDialog(this, "����ǰ��ɫ",
				new ColorPickerDialog.OnColorChangeListener() {

					@Override
					public void colorChanged(int color) {
						// TODO Auto-generated method stub
						FOREGROUND_COLOR = color;
					}
				});
		dialog.show();
	}

	//���ö�ά��ı���ɫ��һ��������Ϊ�������
	public void onBJS() {
		ColorPickerDialog dialog = new ColorPickerDialog(this, "���ñ���ɫ",
				new ColorPickerDialog.OnColorChangeListener() {

					@Override
					public void colorChanged(int color) {
						// TODO Auto-generated method stub
						BACKGROUND_COLOR = color;
					}
				});
		dialog.show();
	}

	/**
	 * ��������CaptureActivity��������ֵ
	 * */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		//
		if (resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			String scanResult = bundle.getString("result");
			String str[] = scanResult.split("=");
			Toast.makeText(MainActivity.this, str[1], 1000).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
