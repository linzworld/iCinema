package com.lzh.cinema.entity;


/** 实体类
 * 包括管理员（通过布尔值1->admin区分）
 * 存放与用户属性有关的数据，与数据库中的数据表相映射
 * VO
 *
 * */
public class User {

	private int userId;  //用户id
	
	private String userName;  //用户名
	
	private String password;  //密码
	
	private Double money;  //余额
	
	private int admin=0;//管理员权限的区分,默认为普通用户
	
	
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString()
	{
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", money=" + money
				+ ", admin=" + admin + "]";
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}


	
}
