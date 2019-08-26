package com.lzh.cinema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lzh.cinema.bean.MoneyMsg;
import com.lzh.cinema.bean.Msg;
import com.lzh.cinema.entity.User;
import com.lzh.cinema.util.JDBCUtil;
import com.lzh.cinema.view.LoginController;

/**
 * 与用户登录注册相关的操作
 * @author 林泽鸿
 *
 */
public class UserDao {

private Connection con;
	
private PreparedStatement stmt;
	
	/**
	 * 用户登录
	 * 查找功能
	 * @param user 
	 * @return 返回msg对象，将信息存储在对象之中进行不同类之间的传输
	 */
	public Msg login(User user){
		
		try {
			con = JDBCUtil.getCon();
			String sql = "SELECT * FROM user WHERE user_name = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, user.getUserName());
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				//检查数据库中是否存在该用户名和密码
				if(user.getPassword().equals(rs.getString("user_password"))&&user.getUserName().equals(rs.getString("user_name")))
				{
					user.setUserId(rs.getInt("user_id"));
					user.setUserName(rs.getString("user_name"));
					if(rs.getInt("admin")==1)
					{return new Msg("管理员登录成功",user);}
					//System.out.println("success");
					//调用的是Msg的构造方法
					else return new Msg("用户登录成功",user);
					
				}
				if(user.getUserName().equals(rs.getString("user_name"))) return new Msg("密码错误",null);
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据库连接异常");
		} finally{
			JDBCUtil.close(stmt, con);
		}
		return new Msg("该用户不存在",null);
	}
	/**
	 * 注册用户
	 * @param user  注册用户默认的为普通用户
	 * @return返回1表示注册成功
	 * 					返回0表示注册失败
	 */
	public int register(User user){
		
		int judge=0;
		con = JDBCUtil.getCon();
		String sql="insert into user (user_name,user_password,admin) values (?,?,?)";
		try {
			PreparedStatement ptmt=con.prepareStatement(sql);
			ptmt.setString(1, user.getUserName());
			ptmt.setString(2, user.getPassword());
			ptmt.setInt(3, user.getAdmin());
			ptmt.executeUpdate();
			judge=1;
			System.out.println("添加成功");
		} catch (SQLException e) {
			judge=0;
			System.out.println("添加失败");
			e.printStackTrace();
		}
		return judge;
	}
	
/**
 * 查看用户名是否重复
 * @param user
 * @return  如果返回值为1表示重复
 * 					如果返回值为0表示没有重复了
 * 
 */
	public int repetition(User user)
	{
		
		int judge=0;
		con = JDBCUtil.getCon();
		String sql="select * from user where user_name=?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1,user.getUserName());
			ResultSet res=stmt.executeQuery();
			while(res.next())
			{
				judge=1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return judge;
	}


}
