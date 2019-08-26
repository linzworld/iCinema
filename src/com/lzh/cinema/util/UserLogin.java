package com.lzh.cinema.util;
/**
 *  登录 对数据库的操作
 * 
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lzh.cinema.entity.User;

/**
 * 登陆所用到的工具，与数据库相连接
 * 验证是否存在该用户
 * @author 林泽鸿
 *
 */
public  class UserLogin {
	public User login(Connection con,User user){//形参是数据库的连接和User类的对象，使用User类中的属性
		User resultUser=null;//User类的对象，初始化并赋空值
		//对用户的密码加密，pwdcompare返回1，则密码正确。
		String sql="select * From users where user_name = ? and pwdcompare(?, user_password)=1";
		//预编译，防止sql注入的危险
		PreparedStatement pstmt;
		try {
			pstmt=con.prepareStatement(sql);
			//pstmt.setInt(1, user.getId());//可得到id
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassword());
			// 返回结果集
			ResultSet rs=pstmt.executeQuery();
			//处理ResultSet结果集，next()不断处理下一行
			if(rs.next()){
				//给User类的对象实例化
				resultUser=new User();
				resultUser.setUserId(rs.getInt("use_id"));
				resultUser.setUserName(rs.getString("user_Name"));
				resultUser.setPassword(rs.getString("user_password"));
			}
			pstmt.close();
			 con.close();
			 
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return resultUser;
	}
}

