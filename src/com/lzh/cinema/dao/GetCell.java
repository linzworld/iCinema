package com.lzh.cinema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lzh.cinema.util.JDBCUtil;
import com.lzh.cinema.view.PayController;

/**
 * 得到所需的表格中最小单元格的值
 * @author 林泽鸿
 *
 */
public class GetCell
{
	private Connection con;
	
	private PreparedStatement stmt;
	/**
	 *  查询出用户符合的schedule的全部schedule集合
	 * @return  schedule_id 数字的集合
	 */
	public List<Integer> GSche(){
         int s = 0;
		try {
			List<Integer> list =new ArrayList<Integer>();
			con = JDBCUtil.getCon();
			String sql = "SELECT * FROM sales WHERE user_id =? and status=1";
			stmt = con.prepareStatement(sql);
			BuyDao b = new BuyDao();
			stmt.setInt(1, b.GUser());
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{
	       s= rs.getInt("schedule_id");
	       list.add(s);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据库连接异常");
		} finally{
			JDBCUtil.close(stmt, con);
		}
		return null;
		}
}
