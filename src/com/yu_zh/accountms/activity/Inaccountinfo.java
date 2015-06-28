package com.yu_zh.accountms.activity;

import java.util.List;

import com.yu_zh.accountms.dao.InaccountDAO;
import com.yu_zh.accountms.model.Tb_inaccount;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Inaccountinfo extends Activity {

	public static final String FLAG = "id";
	private ListView lvininfo;
	private String strType = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inaccountinfo);
		
		lvininfo = (ListView) findViewById(R.id.lvinaccountinfo);
		ShowInfo(R.id.btnininfo);
		
		lvininfo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String strInfo = String.valueOf(((TextView)view).getText());
				String strid = strInfo.substring(0, strInfo.indexOf(')'));
				Intent intent = new Intent(Inaccountinfo.this, InfoManage.class);
				intent.putExtra(FLAG, new String[]{strid,strType});
				startActivity(intent);
			}
			
		});
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		ShowInfo(R.id.btnininfo);
	}
	
	private void ShowInfo(int intType){
		String[] strInfos = null;
		ArrayAdapter<String> arrayAdapter = null;
		strType = "btnininfo";
		InaccountDAO inaccountDAO = new InaccountDAO(Inaccountinfo.this);
		List<Tb_inaccount> listinfos = inaccountDAO.getScrollData(0, (int) inaccountDAO.getCount());
		strInfos = new String[listinfos.size()];
		int m = 0;
		for (Tb_inaccount tb_inaccount : listinfos) {
			strInfos[m] = tb_inaccount.get_id() + ")" + tb_inaccount.getType() + " " + 
					String.valueOf(tb_inaccount.getMoney()) + "Ԫ		" + tb_inaccount.getTime();
			m++;
		}
		arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strInfos);
		lvininfo.setAdapter(arrayAdapter);
	}
}
