package com.yu_zh.accountms.activity;

import com.yu_zh.accountms.dao.FlagDAO;
import com.yu_zh.accountms.model.Tb_flag;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Accountflag extends Activity {
	
	private EditText txtFlag;
	private Button btnflagSave;
	private Button btnflagCancel;
	FlagDAO flagDAO = new FlagDAO(Accountflag.this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.accountflag);
		
		txtFlag = (EditText) findViewById(R.id.txtFlag);
		btnflagSave = (Button) findViewById(R.id.btnflagSave);
		btnflagCancel = (Button) findViewById(R.id.btnflagCancel);
		
		btnflagSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String strFlag = txtFlag.getText().toString();
				if (!strFlag.isEmpty()) {
					Tb_flag tb_flag = new Tb_flag(flagDAO.getMaxId() + 1, strFlag);
					flagDAO.add(tb_flag);
					Toast.makeText(Accountflag.this, "【新增便签】数据添加成功！", Toast.LENGTH_SHORT).show();
				}else {
					Toast.makeText(Accountflag.this, "请输入便签！", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
		
		btnflagCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				txtFlag.setText("");
				
			}
		});
	}
}
