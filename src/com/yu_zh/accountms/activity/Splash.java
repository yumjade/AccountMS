package com.yu_zh.accountms.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class Splash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//ÎÞtitle
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);//È«ÆÁ
		setContentView(R.layout.splash);
		startLoginActivity();
	}

	private void startLoginActivity() {
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				Intent intent = new Intent(Splash.this, Login.class);
				startActivity(intent);
				Splash.this.finish();
			}
		}, 2000);
		
	}
}
