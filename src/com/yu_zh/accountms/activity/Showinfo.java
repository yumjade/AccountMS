package com.yu_zh.accountms.activity;

import java.util.List;

import com.yu_zh.accountms.dao.FlagDAO;
import com.yu_zh.accountms.dao.InaccountDAO;
import com.yu_zh.accountms.dao.OutaccountDAO;
import com.yu_zh.accountms.model.Tb_flag;
import com.yu_zh.accountms.model.Tb_inaccount;
import com.yu_zh.accountms.model.Tb_outaccount;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Showinfo extends Activity {
	
	public static final String FLAG = "id";
	private Button btnoutinfo,btnininfo,btnflaginfo;
	private ListView lvinfo;
	String strType = "";
	OutaccountDAO outaccountDAO = new OutaccountDAO(Showinfo.this);
	InaccountDAO inaccountDAO = new InaccountDAO(Showinfo.this);
	FlagDAO flagDAO = new FlagDAO(Showinfo.this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showinfo);
		
		btnoutinfo = (Button) findViewById(R.id.btnoutinfo);
		btnininfo = (Button) findViewById(R.id.btnininfo);
		btnflaginfo = (Button) findViewById(R.id.btnflaginfo);
		lvinfo = (ListView) findViewById(R.id.lvinfo);
		
		ShowInfo(R.id.btnoutinfo);
		
		btnoutinfo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ShowInfo(R.id.btnoutinfo);
				
			}
		});
		
		btnininfo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ShowInfo(R.id.btnininfo);
				
			}
		});
		
		btnflaginfo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ShowInfo(R.id.btnflaginfo);
				
			}
		});
		
		lvinfo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String strInfo = String.valueOf(((TextView)view).getText());
				String strid = strInfo.substring(0, strInfo.indexOf(')'));
				Intent intent = null;
				if (strType == "btnoutinfo" || strType == "btnininfo") {
					intent = new Intent(Showinfo.this, InfoManage.class);
					intent.putExtra(FLAG, new String[]{strid,strType});
				}else if (strType == "btnflaginfo") {
					intent = new Intent(Showinfo.this, FlagManage.class);
					intent.putExtra(FLAG, strid);
				}
				startActivity(intent);
			}
		});
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		ShowInfo(R.id.btnoutinfo);
	}
	
	
	private void ShowInfo(int intType) {
		String[] strInfos = null;
		ArrayAdapter<String> arrayAdapter = null;
		switch (intType) {
		case R.id.btnoutinfo:
			strType = "btnoutinfo";
			List<Tb_outaccount> listoutinfos = outaccountDAO.getScrollData(0, (int) outaccountDAO.getCount());
			strInfos = new String[listoutinfos.size()];
			int i = 0;
			for (Tb_outaccount tb_outaccount : listoutinfos) {
				strInfos[i] = tb_outaccount.get_id() + ")" + tb_outaccount.getType() + " " + 
						String.valueOf(tb_outaccount.getMoney())+ "Ԫ		" + tb_outaccount.getTime();
				i++;
			}
			break;
		case R.id.btnininfo:
			strType = "btnininfo";
			List<Tb_inaccount> listininfos = inaccountDAO.getScrollData(0, (int) inaccountDAO.getCount());
			strInfos = new String[listininfos.size()];
			int j = 0;
			for (Tb_inaccount tb_inaccount : listininfos) {
				strInfos[j] = tb_inaccount.get_id() + ")" + tb_inaccount.getType() + " " + 
						String.valueOf(tb_inaccount.getMoney())+ "Ԫ		" + tb_inaccount.getTime();
				j++;
			}
			break;
		case R.id.btnflaginfo:
			strType = "btnflaginfo";
			List<Tb_flag> listflaginfos = flagDAO.getScrollData(0, (int) flagDAO.getCount());
			strInfos = new String[listflaginfos.size()];
			int k = 0;
			for (Tb_flag tb_flag : listflaginfos) {
				strInfos[k] = tb_flag.get_id() + ")" + tb_flag.getFlag();
				if (strInfos[k].length() > 15) {
					strInfos[k] = strInfos[k].substring(0, 15) + "......";
				}
				k++;
			}
			break;
		}
		arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strInfos);
		lvinfo.setAdapter(arrayAdapter);
	}
}
