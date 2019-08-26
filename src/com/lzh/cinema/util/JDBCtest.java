package com.lzh.cinema.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 * 测试数据库是否已连接
 * @author 林泽鸿
 *
 */
public class JDBCtest {
public static void main(String[] args){
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {
		conn = JDBCUtil.getCon();
		System.out.println("11连上了");
		
		ps = conn.prepareStatement("insert into user (user_id,user_name, user_password)values(?,?,?)");
		
		ps.setInt(1, 10);
		ps.setString(2,"hu");
		ps.setString(3, "123456");
		
		
		ps.execute();
	} catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		JDBCUtil.close(rs,ps,conn);
	}
}
}
