package com.yu_zh.accountms.activity;

import java.util.List;

import com.yu_zh.accountms.dao.OutaccountDAO;
import com.yu_zh.accountms.model.Tb_outaccount;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Outaccountinfo extends Activity {
	
	public static final String FLAG = "id";
	private ListView lvoutinfo;
	String strType = "";
	OutaccountDAO outaccountDAO = new OutaccountDAO(Outaccountinfo.this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.outaccountinfo);
		
		lvoutinfo = (ListView) findViewById(R.id.lvoutaccountinfo);
		Showinfo(R.id.btnoutinfo);
		
		lvoutinfo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String strInfo = String.valueOf(((TextView)view).getText());
				String strid = strInfo.substring(0, strInfo.indexOf(')'));
				Intent intent = new Intent(Outaccountinfo.this, InfoManage.class);
				intent.putExtra(FLAG, new String[]{strid,strType});
				startActivity(intent);
			}
		});
	}

	private void Showinfo(int intType) {
		String[] strInfos = null;
		ArrayAdapter<String> arrayAdapter = null;
		strType = "btnoutinfo";
		List<Tb_outaccount> listinfos = outaccountDAO.getScrollData(0, (int) outaccountDAO.getCount());
		strInfos = new String[listinfos.size()];
		int m = 0;
		for (Tb_outaccount tb_outaccount : listinfos) {
			strInfos[m] = tb_outaccount.get_id() + ")" + tb_outaccount.getType() + 
					String.valueOf(tb_outaccount.getMoney()) +"Ԫ		" + 
					tb_outaccount.getTime();
			m++;
		}
		arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strInfos);
		lvoutinfo.setAdapter(arrayAdapter);
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		Showinfo(R.id.btnoutinfo);
	}
}
