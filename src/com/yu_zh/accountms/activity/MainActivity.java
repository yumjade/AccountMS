package com.yu_zh.accountms.activity;

import com.yu_zh.accountms.adapter.PictureAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;



public class MainActivity extends Activity {
	
	private GridView gvInfo;
	private String[] titles = new String[]{"����֧��","��������","�ҵ�֧��","�ҵ�����",
			"���ݹ���","ϵͳ����","��֧��ǩ","�˳�"};
	private int[] images = new int[]{R.drawable.addoutaccount,R.drawable.addinaccount,
			R.drawable.outaccountinfo,R.drawable.inaccountinfo,R.drawable.showinfo,
			R.drawable.sysset,R.drawable.accountflag,R.drawable.exit};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        gvInfo = (GridView) findViewById(R.id.gvInfo);
        PictureAdapter adapter = new PictureAdapter(titles,images,this);
        gvInfo.setAdapter(adapter);
        gvInfo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = null;
				switch (position) {
				case 0:
					intent = new Intent(MainActivity.this, AddOutaccount.class);
					startActivity(intent);
					break;
				case 1:
					intent = new Intent(MainActivity.this, AddInaccount.class);
					startActivity(intent);
					break;
				case 2:
					intent = new Intent(MainActivity.this, Outaccountinfo.class);
					startActivity(intent);
					break;
				case 3:
					intent = new Intent(MainActivity.this, Inaccountinfo.class);
					startActivity(intent);
					break;
				case 4:
					intent = new Intent(MainActivity.this, Showinfo.class);
					startActivity(intent);
					break;
				case 5:
					intent = new Intent(MainActivity.this, Sysset.class);
					startActivity(intent);
					break;
				case 6:
					intent = new Intent(MainActivity.this, Accountflag.class);
					startActivity(intent);
					break;
				case 7:
					finish();
				}		
			}
		});
    }

}
