package com.yu_zh.accountms.activity;

import com.yu_zh.accountms.dao.PwdDAO;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
	
	private EditText txtnickname;
	private EditText txtpassword;
	private Button btnlogin;
	private Button btnexit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		txtnickname = (EditText) findViewById(R.id.txtnickname);
		txtpassword = (EditText) findViewById(R.id.txtpassword);
		btnlogin = (Button) findViewById(R.id.btnLogin);
		btnexit = (Button) findViewById(R.id.btnExit);
		
		btnlogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Login.this, MainActivity.class);
				PwdDAO pwdDAO = new PwdDAO(Login.this);
				String nickname = txtnickname.getText().toString();
				String password = txtpassword.getText().toString();
				if (pwdDAO.getCount() == 0) {
					startActivity(intent);
				}else {
					if (pwdDAO.find().getNickname().equals(nickname) && pwdDAO.find().getPassword()
							.equals(password)) {
						startActivity(intent);
					}else {
						txtpassword.setText("");
						Toast.makeText(Login.this, "请输入正确的账号或密码！", Toast.LENGTH_SHORT).show();
					}	
				}
				txtpassword.setText("");
			}
			
		});
		
		btnexit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
	}
}
