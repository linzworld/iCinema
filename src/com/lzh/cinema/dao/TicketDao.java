package com.lzh.cinema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lzh.cinema.entity.MovieList;
import com.lzh.cinema.entity.MyMovie;
import com.lzh.cinema.entity.UserQueryMovie;
import com.lzh.cinema.util.JDBCUtil;

/**
 * 放与影票相关的 与数据库相关的
 * 
 * @author 林泽鸿
 *
 */
public class TicketDao
{
	BuyDao bd = new BuyDao();
	
	/**
	 * 得到schedule_id的值
	 */
	private Connection con;

	private PreparedStatement stmt;

/**
 * 通过schedule和user_id在ticket表中找到seat_id的值
 * status==1
 * @param schedule
 * @return seat_id的集合，放在mymovie对象中
 */
	public List<MyMovie>  GetSeat(int schedule)
	{
		try
		{
			
			con = JDBCUtil.getCon();
			BuyDao  bd  = new BuyDao(); 
			String sql = "select seat_id from ticket where schedule_id=? AND user_id =? AND status=1";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, schedule);
			stmt.setInt(2,bd.GUser());
			ResultSet rs = stmt.executeQuery();// 返回一个结果集
			MyMovie myMovie = null;
			// 对结果集得到的数据进行封装
			List<MyMovie> list = new ArrayList<MyMovie>(); // 集合
			while (rs.next())
			{
				// 每一个记录得到四个参数，分别放进对象中，形成相当于一张表格的数据
				/*
				 * 每次新建成一个对象的地址
				 */
				myMovie = new MyMovie();
			
				myMovie.setSeat(rs.getInt("seat_id"));
				list.add(myMovie);

			}
			return list;
		} catch (SQLException e)
		{
			e.printStackTrace();
			System.out.println("数据库连接异常mymovie");
		} finally
		{
			JDBCUtil.close(stmt, con);
		}
		return null;
	}
	/**
	 * 依此传入seat_id的值
	 * @param seat
	 * @return 装有seat_id,x,y的mymovie对象集合
	 * 相对应的是用户已订电影的全部信息
	 */
	public MyMovie GetXY(int seat)
	{
		try
		{
			
			con = JDBCUtil.getCon();
			String sql = "select seat_id,x,y from seat where seat_id =?";
		
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, seat);
				ResultSet rs = stmt.executeQuery();// 返回一个结果集
			MyMovie myMovie = null;
			// 对结果集得到的数据进行封装
			while (rs.next())
			{
				/*
				 * 每次新建成一个对象的地址
				 */
				myMovie = new MyMovie();
			
				myMovie.setSeat(rs.getInt("seat_id"));
				myMovie.setX(rs.getInt("x"));
				myMovie.setY(rs.getInt("y"));
				

			}
			return myMovie;
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据库连接异常getxy");
		} 
		finally
		{
			JDBCUtil.close(stmt, con);
		}
		return null;
	}
	
	/**
	 * 通过预览电影信息中的schedule_id(传入的参数是list集合) 1 在ticket表中结合user_id找到符合条件的seat_id, 2
	 * 进而在seat表中找到x,y
	 */
	public List<MyMovie> TQuery(List<UserQueryMovie> list1)
	{
		try
		{

			con = JDBCUtil.getCon();
			String sql = "select ticket .hall_id,schedule.session,movie.movie_name from schedule,movie where ticket.seat_id=seat.seat_id";
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();// 返回一个结果集
			MovieList movieList = null;
			// 对结果集得到的数据进行封装
			List<MyMovie> list = new ArrayList<MyMovie>(); // 集合
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
				/* list.add(movieList); */

			}
			return list;
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据库连接异常mymovie");
		} finally
		{
			JDBCUtil.close(stmt, con);
		}
		return null;
	}

	
	/**
	 * 退票的功能
	 * 通过seat_id在ticket表更新该票的status为0
	 */	
	public int RefundT(){
			
			int judge=0;
		
			try {
				
				con = JDBCUtil.getCon();
				
				String sql="UPDATE ticket SET status=0 WHERE seat_id = ?";
				PreparedStatement stmt=con.prepareStatement(sql);
				stmt.setInt(1, bd.GSeatT());
				System.out.println( "rt----seat"+bd.GSeatT());
				stmt.executeUpdate();
				System.out.println("rt");
				judge=1;
			} catch (SQLException e) {
				judge=0;
				System.out.println("退票失败rt");
				e.printStackTrace();
			}
			finally {JDBCUtil.close(stmt, con);}
			return judge;
		}
	/**
	 * 退票的功能
	 * 通过seat_id在sales表更新该票的status为0
	 * @return
	 */
	public int RefundS(){
		
		int judge=0;
	
		try {
			
			con = JDBCUtil.getCon();
			String sql="UPDATE sales SET status=0 WHERE seat_id = ?";
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setInt(1, bd.GSeatT());
			stmt.executeUpdate();

			judge=1;
		} catch (SQLException e) {
			judge=0;
			System.out.println("退票失败rs");
			e.printStackTrace();
		}
		finally {JDBCUtil.close(stmt, con);}
		return judge;
	}
	
}