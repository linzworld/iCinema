package com.lzh.cinema.util;
/*
 * 验证用户名和密码是否在数据库中已经存在，如果已存在则返回真，没有则返回家
 * */
public class ValidateUtil {
	/**
	 * 类方法
	 * 判空处理
	 * 验证用户名
	 */
	public static boolean isInvalidUserName(String userName){
		if(userName == null || userName.equals("")){
			System.out.println("zero");
			return true;
		}
		return false;
	}
	/**
	 * 判断密码是否为空
	 * @param password
	 * @return
	 */
	public static boolean isInvalidPassword(String password){
		if(password == null || password.equals("")){
			System.out.println("密码为空");
			return true;
		}
		return false;
	}
}
