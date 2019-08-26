package com.lzh.cinema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.lzh.cinema.bean.Msg;
import com.lzh.cinema.entity.MovieList;

import com.lzh.cinema.util.JDBCUtil;

/**
 * 排片表和电影表联合查询 用户大厅的左侧的预览电影功能 进行多表查询，返回所需值，封装在对象中
 * 
 * @author 林泽鸿
 *
 */
public class MovieListDao
{
	private Connection con;

	private PreparedStatement stmt;

	public List<MovieList> TwoQuery( )
	{

		try
		{
			con = JDBCUtil.getCon();
			String sql = "select schedule.hall_id,schedule.session,movie.movie_name from schedule,movie where schedule.movie_id=movie.movie_id";
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();// 返回一个结果集
			MovieList movieList = null;
			// 对结果集得到的数据进行封装
			List<MovieList> list = new ArrayList<MovieList>(); //集合
			while (rs.next())
			{
				// 每一个记录得到四个参数，分别放进对象中，形成相当于一张表格的数据
				/*
				 * 每次新建成一个对象的地址
				 */
				     movieList = new MovieList();
				     movieList.setHall(rs.getInt("hall_id"));
					 movieList.setSession(rs.getInt("session"));
					 movieList.setMoiveName(rs.getString("movie_name"));
					 list.add(movieList);
					 
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
