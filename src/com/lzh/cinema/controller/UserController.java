package com.lzh.cinema.controller;

import com.lzh.cinema.bean.Msg;
import com.lzh.cinema.service.UserService;

public class UserController {

	private UserService userService = new UserService();
	
	
	/**
	 * 用户登录
	 * @param userName
	 * @param password
	 * @return 跳转到controller层，调用login方法
	 */
	public  Msg login(String userName,String password){
		System.out.printf(userName);
		System.out.printf(password);
		//调用service层的方法
		return userService.login(userName, password);
	}
	
	/**
	* 用户注册
	* @param userName
	* @param password
	* @return 跳转到controller层，调用register方法
	*/
	public Msg register(String userName,String password){
		//调用service层的方法
		return userService.register(userName, password);
	}
}

