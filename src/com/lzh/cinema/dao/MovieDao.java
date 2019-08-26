package com.lzh.cinema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lzh.cinema.entity.Movie;
import com.lzh.cinema.entity.MovieList;
import com.lzh.cinema.util.JDBCUtil;
import com.lzh.cinema.view.AdminHallController;

/**
 * 对电影信息进行增删查改
 * 
 * @author 林泽鸿
 *
 */
public class MovieDao
{
	
	private Connection con;

	private PreparedStatement stmt;
	/*
	*//**
		 * 通过传递sql语句实现对电影进行增删改的操作
		 *//*
			 * public int admMovie(Connection con,String sql) { PreparedStatement ptmt=null;
			 * int result=0; try { ptmt=con.prepareStatement(sql);
			 * result=ptmt.executeUpdate(); } catch (SQLException e) { e.printStackTrace();
			 * } return result; }
			 */
/**
 * 管理员 遍历所有电影 返回一个movie集合
 * @return list(装有movie对象)
 */
	public List<Movie> QMovieA()
	{
		
		try
		{
			con = JDBCUtil.getCon();
			String sql = "select * from movie";
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();// 返回一个结果集
			Movie movie = null;//实体类对象
			// 对结果集得到的数据进行封装
			List<Movie> list = new ArrayList<Movie>(); //集合
			// 存储查询结果的对象
			while (rs.next())
			{
				movie = new Movie();
				
				movie.setMovieid(rs.getInt("movie_id"));
				movie.setMovieName(rs.getString("movie_name"));

				movie.setMainActor(rs.getString("movie_mainactor"));
				movie.setDiretor(rs.getString("movie_director"));

				movie.setPrice(rs.getDouble("movie_price"));
				movie.setDuration(rs.getInt("movie_duration"));

				list.add(movie);
			}
			return list;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	  return null;
	}
	/**
	 * 管理员
	 * 删除电影
	 * @return 1，0
	 */
	public int DeleteM(){
		
		int judge=0;
	
		try {
			
			con = JDBCUtil.getCon();
			
			String sql="DELETE FROM movie WHERE movie_id = ?";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setInt(1, AdminHallController.a);
			System.out.println( "adminhall---aaa");
			stmt.executeUpdate();
			System.out.println("deletedao");
			judge=1;
		} catch (SQLException e) {
			judge=0;
			System.out.println("删除电影失败dm");
			e.printStackTrace();
		}
		finally 
		{JDBCUtil.close(stmt, con);}
		return judge;
	}
	/**
	 * 更改电影信息
	 * @param m
	 * @return
	 */
	public int AlterM(Movie m){
		
		int judge=0;
	
		try {
			
			con = JDBCUtil.getCon();
			System.out.println("cseatt1");
			String sql="UPDATE movie SET movie_name=? ,movie_mainactor=?,movie_director=? ,movie_price=?,movie_duration=? WHERE movie_id = ?";
			PreparedStatement stmt=con.prepareStatement(sql);
			System.out.println("alterm Dao层");
			stmt.setString(1,m.getMovieName());
			stmt.setString(2,m.getMainActor());
			stmt.setString(3,m.getDiretor());
			
			stmt.setDouble(4, m.getPrice());
			stmt.setInt(5, m.getDuration());
			stmt.setInt(6, m.getMovieid());
			
			stmt.executeUpdate();

			judge=1;
		} catch (SQLException e) {
			judge=0;
			System.out.println("更改电影失败");
			e.printStackTrace();
		}
		finally {JDBCUtil.close(stmt, con);}
		return judge;
	}
	/**
	 * 增加电影信息
	 * @param m
	 * @return
	 */
	public int AddM(Movie m){
		
		int judge=0;
	
		try {
			
			con = JDBCUtil.getCon();
			System.out.println("cseatt1");
			String sql="insert into movie (movie_name,movie_mainactor,movie_director,movie_price,movie_duration) values (?,?,?,?,?)";
			PreparedStatement stmt=con.prepareStatement(sql);
			System.out.println("addm Dao层");
			stmt.setString(1,m.getMovieName());
			stmt.setString(2,m.getMainActor());
			stmt.setString(3,m.getDiretor());
			
			stmt.setDouble(4, m.getPrice());
			stmt.setInt(5, m.getDuration());
			
			stmt.executeUpdate();

			judge=1;
		} catch (SQLException e) {
			judge=0;
			System.out.println("增加电影失败");
			e.printStackTrace();
		}
		finally {JDBCUtil.close(stmt, con);}
		return judge;
	}

}