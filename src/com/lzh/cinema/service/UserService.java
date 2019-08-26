package com.lzh.cinema.service;

import com.lzh.cinema.bean.MoneyMsg;
import com.lzh.cinema.bean.Msg;
import com.lzh.cinema.dao.UserDao;
import com.lzh.cinema.entity.User;
import com.lzh.cinema.util.ValidateUtil;

/**
 * 对传过来的要登陆的用户名和密码，进行调用方法的处理 业务逻辑
 * 
 * @author 林泽鸿
 *
 */
public class UserService
{

	private UserDao userDao = new UserDao();

	// 用户登录
	public Msg login(String userName, String password)
	{

		User user = new User();
		/**
		 * 调用判空的方法
		 */
		if (ValidateUtil.isInvalidUserName(userName) == true)
		{

			// 用一个对象存储返回值
			return new Msg("用户名为空", null);
		}
		/**
		 * 调用DAO层，检查是否存在该用户和 密码是否正确 以及确认其管理权限
		 */
		user.setUserName(userName);
		user.setPassword(password);
		// 调用了dao层
		Msg msg = userDao.login(user);
		return msg;
	}

	/**
	 * 用户注册
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public Msg register(String userName, String password)
	{
		int judge = 0;
		User user = new User();
		/**
		 * 调用判空的方法
		 */
		if (ValidateUtil.isInvalidUserName(userName) == true)
		{

			// 用一个对象存储返回值
			return new Msg("用户名为空", null);
		} else if (ValidateUtil.isInvalidPassword(password) == true)
		{
			return new Msg("密码为空", null);
		}
		/**
		 * 调用DAO层，检查是否存在该用户和 密码是否正确 以及确认其管理权限
		 */
		user.setUserName(userName);
		user.setPassword(password);

		/**
		 * 重复时
		 */
		if (userDao.repetition(user) == 1)
		{
			return new Msg("用户名重复", null);
		} else
		{
			judge = userDao.register(user);
			if(judge==1) return new Msg("注册成功", user);
			else return new Msg("注册失败", user);
		}
	}
}