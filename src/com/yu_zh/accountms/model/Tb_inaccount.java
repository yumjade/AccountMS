package com.yu_zh.accountms.model;

//收入信息实体类
public class Tb_inaccount {       
	
	private int _id;
	private double money;
	private String time;
	private String type;
	private String handler;
	private String mark;
	
	//默认构造函数
	public Tb_inaccount() {
		super();
	}
	
	//定义有参构造函数，用来初始化收入信息实体类中的各个字段
	public Tb_inaccount(int _id, double money, String time, String type,
			String handler, String mark) {
		super();
		this._id = _id;
		this.money = money;
		this.time = time;
		this.type = type;
		this.handler = handler;
		this.mark = mark;
	}
	
	public int get_id() {
		return _id;
	}
	
	public void set_id(int _id) {
		this._id = _id;
	}
	
	public double getMoney() {
		return money;
	}
	
	public void setMoney(double money) {
		this.money = money;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getHandler() {
		return handler;
	}
	
	public void setHandler(String handler) {
		this.handler = handler;
	}
	
	public String getMark() {
		return mark;
	}
	
	public void setMark(String mark) {
		this.mark = mark;
	}
	
	
}
