package com.yu_zh.accountms.dao;

import java.util.ArrayList;
import java.util.List;

import com.yu_zh.accountms.model.Tb_flag;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class FlagDAO {
	
	private DBOpenHelper helper;
	private SQLiteDatabase db;
	
	public FlagDAO(Context context) {
		helper = new DBOpenHelper(context);
	}
	
	public void add(Tb_flag tb_flag){
		db = helper.getWritableDatabase();
		db.execSQL("insert into tb_flag (_id,flag) values (?,?)", new Object[]{tb_flag.get_id(),tb_flag.getFlag()});
	}
	
	public void update(Tb_flag tb_flag){
		db = helper.getWritableDatabase();
		db.execSQL("update tb_flag set flag = ? where _id = ?", new Object[]{tb_flag.getFlag(),tb_flag.get_id()});
	}

	public Tb_flag find(int id){
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select _id,flag from tb_flag where _id =?", new String[]{String.valueOf(id)});
		if (cursor.moveToNext()) {
			return new Tb_flag(cursor.getInt(cursor.getColumnIndex("_id")), cursor.getString(cursor.getColumnIndex("flag")));
		}
		return null;
		
	}
	
	public void delete(Integer... ids){
		if (ids.length > 0) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < ids.length; i++) {
				sb.append('?').append(',');
			}
			sb.deleteCharAt(sb.length() - 1);
			db = helper.getWritableDatabase();
			db.execSQL("delete from tb_flag where _id in (" + sb + ")", (Object[])ids);
		}
	}
	
	public List<Tb_flag> getScrollData(int start,int count){
		List<Tb_flag> tb_flags = new ArrayList<Tb_flag>();
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from tb_flag limit ?,?",
				new String[]{String.valueOf(start),String.valueOf(count)});
		while (cursor.moveToNext()) {
			tb_flags.add(new Tb_flag(cursor.getInt(cursor.getColumnIndex("_id")), 
					cursor.getString(cursor.getColumnIndex("flag"))));
		}
		return tb_flags;
		
	}
	
	public long getCount(){
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select count(_id) from tb_flag", null);
		while (cursor.moveToNext()) {
			return cursor.getLong(0);
		}
		return 0;
		
	}
	
	public int getMaxId(){
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select max(_id) from tb_flag", null);
		while (cursor.moveToLast()) {
			return cursor.getInt(0);
		}
		return 0;
		
	}
}
