package com.yu_zh.accountms.activity;

import com.yu_zh.accountms.dao.FlagDAO;
import com.yu_zh.accountms.model.Tb_flag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FlagManage extends Activity {
	
	private EditText txtFlagManage;
	private Button btnflagManageEdit;
	private Button btnflagManageDelete;
	String strid;
	FlagDAO flagDAO = new FlagDAO(FlagManage.this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flagmanage);
		
		txtFlagManage = (EditText) findViewById(R.id.txtFlagManage);
		btnflagManageEdit = (Button) findViewById(R.id.btnflagManageEdit);
		btnflagManageDelete = (Button) findViewById(R.id.btnflagManageDelete);
		
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		strid = bundle.getString(Showinfo.FLAG);
		txtFlagManage.setText(flagDAO.find(Integer.parseInt(strid)).getFlag());
				
		btnflagManageEdit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Tb_flag tb_flag = new Tb_flag();
				tb_flag.set_id(Integer.parseInt(strid));
				tb_flag.setFlag(txtFlagManage.getText().toString());
				flagDAO.update(tb_flag);
				Toast.makeText(FlagManage.this, "【便签数据】修改成功！", Toast.LENGTH_SHORT).show();
			}
		});
		
		btnflagManageDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				flagDAO.delete(Integer.parseInt(strid));
				Toast.makeText(FlagManage.this, "【便签数据】删除成功！", Toast.LENGTH_SHORT).show();
			}
		});
	}
}
