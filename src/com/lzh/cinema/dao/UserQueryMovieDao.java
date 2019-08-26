package com.lzh.cinema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.lzh.cinema.entity.UserQueryMovie;
import com.lzh.cinema.util.JDBCUtil;
import com.lzh.cinema.util.StringUtil;

public class UserQueryMovieDao{
private Connection con;

private PreparedStatement stmt;

public List<UserQueryMovie> UserQuery( )
{

	try
	{
		
		con = JDBCUtil.getCon();
		String sql = " select schedule.hall_id,schedule.session,schedule.date,schedule.schedule_id,movie.movie_name from schedule,movie where schedule.movie_id=movie.movie_id";
		stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();// 返回一个结果集
		UserQueryMovie userQueryMovie = null;
		// 对结果集得到的数据进行封装
		List<UserQueryMovie> list = new ArrayList<UserQueryMovie>(); 
		while (rs.next())
		{
			// 每一个记录得到三个参数，分别放进对象中
			userQueryMovie = new UserQueryMovie();
			userQueryMovie.setHall(rs.getInt("hall_id"));
			userQueryMovie.setSession(rs.getInt("session"));
			userQueryMovie.setMoiveName(rs.getString("movie_name"));
			userQueryMovie.setSchedule(rs.getInt("schedule_id"));
			userQueryMovie.setDate(StringUtil.dateToString(rs.getDate("date")));//播放的日期,转化为字符串
		
				 list.add(userQueryMovie);
				 
		}
				return list;
	} 
	catch (SQLException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("数据库连接异常");
	} finally
	{
		JDBCUtil.close(stmt, con);
	}
	return null;


}
}
