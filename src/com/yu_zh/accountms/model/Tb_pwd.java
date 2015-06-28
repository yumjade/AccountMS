package com.yu_zh.accountms.model;

//密码信息数据表模型类
public class Tb_pwd {
	
	private String nickname;
	private String password;
	
	public Tb_pwd() {
		super();
	}
	
	public Tb_pwd(String nickname, String password) {
		super();
		this.nickname = nickname;
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
