package com.yu_zh.accountms.dao;

import com.yu_zh.accountms.model.Tb_inaccount;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class InaccountDAO {
	
	private DBOpenHelper helper;
	private SQLiteDatabase db;
	
	public InaccountDAO(Context context) {
		helper = new DBOpenHelper(context);
	}
	
	/**
	 * ���������Ϣ
	 * 
	 * @param tb_inaccount
	 */
	public void add(Tb_inaccount tb_inaccount){
		db = helper.getWritableDatabase();
		db.execSQL("insert into tb_inaccount (_id,money,time,type,handler,mark) values (?,?,?,?,?,?)", 
				new Object[]{tb_inaccount.get_id(),tb_inaccount.getMoney(),tb_inaccount.getTime(),
				tb_inaccount.getType(),tb_inaccount.getHandler(),tb_inaccount.getMark()});
	}

	/**
	 * ����������Ϣ
	 * 
	 * @param tb_inaccount
	 */
	public void update(Tb_inaccount tb_inaccount){
		db = helper.getWritableDatabase();
		db.execSQL("update tb_inaccount set money = ?,time = ?,type = ?,handler = ?, mark = ? where _id = ?", 
				new Object[]{tb_inaccount.getMoney(),tb_inaccount.getTime(),tb_inaccount.getType(),
				tb_inaccount.getHandler(),tb_inaccount.getMark(),tb_inaccount.get_id()});
	}
	
	
	/**
	 * ����������Ϣ
	 * 
	 * @param id
	 * @return 
	 */
	public Tb_inaccount find(int id){
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select _id,money,time,type,handler,mark from tb_inaccount where _id = ?",
				new String[]{String.valueOf(id)});
		if (cursor.moveToNext()) {
			return new Tb_inaccount(cursor.getInt(cursor.getColumnIndex("_id")), cursor.getDouble(cursor.getColumnIndex("money")),
					cursor.getString(cursor.getColumnIndex("time")), cursor.getString(cursor.getColumnIndex("type")),
					cursor.getString(cursor.getColumnIndex("handler")),cursor.getString(cursor.getColumnIndex("mark")));
		}
		return null;
	}
	
	/**
	 * ɾ��������Ϣ
	 * 
	 * @param ids
	 */
	public void delete(Integer... ids){
		if (ids.length > 0) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < ids.length; i++) {
				sb.append('?').append(',');
			}
			sb.deleteCharAt(sb.length() - 1);
			db = helper.getWritableDatabase();
			db.execSQL("delete from tb_inaccount where _id in (" + sb + ")", (Object[])ids);
		}
	}
	
	/**
	 * ��ȡ������Ϣ
	 * @param start ��ʼλ��
	 * @param count ÿҳ��ʾ����
	 * @return
	 */
	public List<Tb_inaccount> getScrollData(int start,int count){
		List<Tb_inaccount> tb_inaccount = new ArrayList<Tb_inaccount>();
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from tb_inaccount limit ?,?", new String[]{
				String.valueOf(start),String.valueOf(count)});
		while (cursor.moveToNext()) {
			tb_inaccount.add(new Tb_inaccount(cursor.getInt(cursor.getColumnIndex("_id")), 
					cursor.getDouble(cursor.getColumnIndex("money")),cursor.getString(cursor.getColumnIndex("time")), 
					cursor.getString(cursor.getColumnIndex("type")),cursor.getString(cursor.getColumnIndex("handler")),
					cursor.getString(cursor.getColumnIndex("mark"))));
		}
		return tb_inaccount;	
	}
	
	/**
	 * ��ȡ�ܼ�¼��
	 * 
	 * @return
	 */
	public long getCount(){
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select count(_id) from tb_inaccount", null);
		if (cursor.moveToNext()) {
			return cursor.getLong(0);
		}
		return 0;	
	}
	
	/**
	 * ��ȡ���������
	 * 
	 * @return
	 */
	public int getMaxId(){
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select max(_id) from tb_inaccount", null);
		if (cursor.moveToLast()) {
			return cursor.getInt(0);
		}
		return 0;
		
	}
}
