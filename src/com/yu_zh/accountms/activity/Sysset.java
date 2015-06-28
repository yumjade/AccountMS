package com.yu_zh.accountms.activity;

import com.yu_zh.accountms.dao.PwdDAO;
import com.yu_zh.accountms.model.Tb_pwd;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Sysset extends Activity {

	private EditText txtsetnickname,txtsetpwd;
	private Button btnSet;
	private Button btnsetCancel;
	PwdDAO pwdDAO = new PwdDAO(Sysset.this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sysset);
		
		txtsetnickname = (EditText) findViewById(R.id.txtsetnickname);
		txtsetpwd = (EditText) findViewById(R.id.txtsetpwd);
		btnSet = (Button) findViewById(R.id.btnSet);
		btnsetCancel = (Button) findViewById(R.id.btnsetCancel);
		
		btnSet.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {				
				Tb_pwd tb_pwd = new Tb_pwd(txtsetnickname.getText().toString(),txtsetpwd.getText().toString());
				if (pwdDAO.getCount() == 0) {
					pwdDAO.add(tb_pwd);
				}else {
					pwdDAO.update(tb_pwd);
				}
				Toast.makeText(Sysset.this, "°æ√‹¬Î°ø…Ë÷√≥…π¶£°", Toast.LENGTH_SHORT).show();
			}
		});
		
		btnsetCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				txtsetpwd.setText("");
				txtsetpwd.setHint("«Î ‰»Î√‹¬Î");
				
			}
		});
	}
}
