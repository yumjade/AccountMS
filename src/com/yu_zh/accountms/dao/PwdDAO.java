package com.yu_zh.accountms.dao;

import com.yu_zh.accountms.model.Tb_pwd;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PwdDAO {

	private DBOpenHelper helper;
	private SQLiteDatabase db;
	
	public PwdDAO(Context context) {
		helper = new DBOpenHelper(context);
	}
	
	public void add(Tb_pwd tb_pwd){
		db = helper.getWritableDatabase();
		db.execSQL("insert into tb_pwd (nickname,password) values (?,?)",new Object[]{tb_pwd.getNickname(),
				tb_pwd.getPassword()});
		
	}
	
	public void update(Tb_pwd tb_pwd){
		db = helper.getWritableDatabase();
		db.execSQL("update tb_pwd set nickname = ?, password = ?", new Object[]{tb_pwd.getNickname(),
				tb_pwd.getPassword()});
	}
	
	public Tb_pwd find(){
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select nickname,password from tb_pwd", null);
		if (cursor.moveToNext()) {
			return new Tb_pwd(cursor.getString(cursor.getColumnIndex("nickname")),
					cursor.getString(cursor.getColumnIndex("password")));
		}
		return null;
		
	}
	
	public long getCount(){
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select count(nickname) from tb_pwd", null);
		while (cursor.moveToNext()) {
			return cursor.getLong(0);
		}
		return 0;
		
	}
	
}
