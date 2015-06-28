package com.yu_zh.accountms.model;

//便签信息数据表模型类
public class Tb_flag {
	
	private int _id;
	private String flag;
	
	public Tb_flag() {
		super();
	}
	
	public Tb_flag(int _id, String flag) {
		super();
		this._id = _id;
		this.flag = flag;
	}
	
	public int get_id() {
		return _id;
	}
	
	public void set_id(int _id) {
		this._id = _id;
	}
	
	public String getFlag() {
		return flag;
	}
	
	public void setFlag(String flag) {
		this.flag = flag;
	}

}
