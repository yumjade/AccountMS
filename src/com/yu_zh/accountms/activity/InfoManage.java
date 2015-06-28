package com.yu_zh.accountms.activity;

import com.yu_zh.accountms.dao.InaccountDAO;
import com.yu_zh.accountms.dao.OutaccountDAO;
import com.yu_zh.accountms.model.Tb_inaccount;
import com.yu_zh.accountms.model.Tb_outaccount;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class InfoManage extends Activity {
	
	protected static final int DATE_DIALOG_ID = 0;
	private TextView tvtitle,tvInOutHandler;
	private EditText txtInOutMoney,txtInOutTime,txtInOutHandler,txtInOutMark;
	private Spinner spInOutType;
	private Button btnInOutEdit,btnInOutDelete;
	private String[] strInfos;
	private String strid,strType;
	private int mYear,mMonth,mDay;
	OutaccountDAO outaccountDAO = new OutaccountDAO(InfoManage.this);
	InaccountDAO inaccountDAO = new InaccountDAO(InfoManage.this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.infomanage);
		
		tvtitle = (TextView) findViewById(R.id.inouttitle);
		tvInOutHandler = (TextView) findViewById(R.id.tvInOutHandler);
		txtInOutMoney = (EditText) findViewById(R.id.txtInOutMoney);
		txtInOutTime = (EditText) findViewById(R.id.txtInOutTime);
		txtInOutHandler = (EditText) findViewById(R.id.txtInOutHandler);
		txtInOutMark = (EditText) findViewById(R.id.txtInOutMark);
		spInOutType = (Spinner) findViewById(R.id.spInOutType);
		btnInOutEdit = (Button) findViewById(R.id.btnInOutEdit);
		btnInOutDelete = (Button) findViewById(R.id.btnInOutDelete);
		
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		strInfos = bundle.getStringArray(Showinfo.FLAG);
		strid = strInfos[0];
		strType = strInfos[1];
		if (strType.equals("btnoutinfo")) {
			tvtitle.setText("支出管理");
			tvInOutHandler.setText("地    点：");
			Tb_outaccount tb_outaccount = outaccountDAO.find(Integer.parseInt(strid));
			txtInOutMoney.setText(String.valueOf(tb_outaccount.getMoney()));
			txtInOutTime.setText(tb_outaccount.getTime());
			spInOutType.setPrompt(tb_outaccount.getType());
			txtInOutHandler.setText(tb_outaccount.getAddress());
			txtInOutMark.setText(tb_outaccount.getMark());
		}else if (strType.equals("btnininfo")) {
			tvtitle.setText("收入管理");
			tvInOutHandler.setText("付款方：");
			Tb_inaccount tb_inaccount = inaccountDAO.find(Integer.parseInt(strid));
			txtInOutMoney.setText(String.valueOf(tb_inaccount.getMoney()));
			txtInOutTime.setText(tb_inaccount.getTime());
			spInOutType.setPrompt(tb_inaccount.getType());
			txtInOutHandler.setText(tb_inaccount.getHandler());
			txtInOutMark.setText(tb_inaccount.getMark());
		}
		
		btnInOutEdit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (strType.equals("btnoutinfo")) {
					Tb_outaccount tb_outaccount = new Tb_outaccount();
					tb_outaccount.set_id(Integer.parseInt(strid));
					tb_outaccount.setMoney(Double.parseDouble(txtInOutMoney.getText().toString()));
					tb_outaccount.setTime(txtInOutTime.getText().toString());
					tb_outaccount.setType(spInOutType.getSelectedItem().toString());
					tb_outaccount.setAddress(txtInOutHandler.getText().toString());
					tb_outaccount.setMark(txtInOutMark.getText().toString());
					outaccountDAO.update(tb_outaccount);
				}else if (strType.equals("btnininfo")) {
					Tb_inaccount tb_inaccount = new Tb_inaccount();
					tb_inaccount.set_id(Integer.parseInt(strid));
					tb_inaccount.setMoney(Double.parseDouble(txtInOutMoney.getText().toString()));
					tb_inaccount.setTime(txtInOutTime.getText().toString());
					tb_inaccount.setType(spInOutType.getSelectedItem().toString());
					tb_inaccount.setHandler(txtInOutHandler.getText().toString());
					tb_inaccount.setMark(txtInOutMark.getText().toString());
					inaccountDAO.update(tb_inaccount);
				}
				Toast.makeText(InfoManage.this, "【数据】修改成功！", Toast.LENGTH_SHORT).show();
			}
		});
		
		btnInOutDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (strType.equals("btnoutinfo")) {
					outaccountDAO.delete(Integer.parseInt(strid));
				}else if (strType.equals("btnininfo")) {
					inaccountDAO.delete(Integer.parseInt(strid));
				}
				Toast.makeText(InfoManage.this, "【数据】删除成功！", Toast.LENGTH_SHORT).show();
			}
		});
	}

}
