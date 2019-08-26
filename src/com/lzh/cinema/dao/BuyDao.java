package com.lzh.cinema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.lzh.cinema.bean.MoneyMsg;
import com.lzh.cinema.bean.Msg;
import com.lzh.cinema.entity.Movie;
import com.lzh.cinema.entity.MovieList;
import com.lzh.cinema.entity.MoviePrice;
import com.lzh.cinema.entity.Ticket;
import com.lzh.cinema.entity.User;
import com.lzh.cinema.entity.UserQueryMovie;
import com.lzh.cinema.property.UserQueryMovieProperty;
import com.lzh.cinema.service.BuyService;
import com.lzh.cinema.service.MovieListService;
import com.lzh.cinema.util.JDBCUtil;
import com.lzh.cinema.view.LoginController;
import com.lzh.cinema.view.MyTicketController;
import com.lzh.cinema.view.PayController;
import com.lzh.cinema.view.UserQueryMovieController;

public class BuyDao
{
	
private Connection con;
	
private PreparedStatement stmt;
/**
 *  影票
 * @return seat_id
 */
public int GSeatT(){
	
	try {
		Integer s = 0;
		con = JDBCUtil.getCon();
		String sql = "SELECT * FROM seat WHERE seat_id=?";
		//被调用的方法的局部中的预编译要再次声明一次
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, MyTicketController.a);
		System.out.println("g----seat");
		ResultSet rs = stmt.executeQuery();
		while(rs.next())
		{
       s= rs.getInt("seat_id");
		}
		

		return s;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("数据库连接异常gseat");
	} finally{
		JDBCUtil.close(stmt, con);
	}
	return 0;
	}
/**
 * 购票
 * @return 
 */
public int GSeat(){
	
	try {
		Integer s = 0;
		con = JDBCUtil.getCon();
		String sql = "SELECT * FROM seat WHERE x=? AND y=? AND hall_id=?";
		//被调用的方法的局部中的预编译要再次声明一次
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, PayController.x);
		stmt.setInt(2, PayController.y);
		stmt.setInt(3, QHall());
		
		System.out.println("g----seat");
		ResultSet rs = stmt.executeQuery();
		while(rs.next())
		{
       s= rs.getInt("seat_id");
		}
		

		return s;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("数据库连接异常gseat");
	} finally{
		JDBCUtil.close(stmt, con);
	}
	return 0;
	}
/**
 * 得到当前用户的用户id
 * @return user_id
 */
public int GUser(){
	
	try {
		int u = 0;
		
		con = JDBCUtil.getCon();
	
		String sql = "SELECT * FROM user WHERE user_name = ?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, LoginController.userName);
		ResultSet rs = stmt.executeQuery();
	
		while(rs.next())
		{
		u = 0 ;
        u = rs.getInt("user_id");
		}
		return u;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("数据库连接异常guser");
	} finally{
		JDBCUtil.close(stmt, con);
	}
	return 0;
	}
/**
 * 通过user_id找出ticket_id
 * @return ticket_id
 */
public int GTicket(){
	int u = 0;
	try {
		
		con = JDBCUtil.getCon();
		String sql = "SELECT * FROM ticket WHERE user_name = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, LoginController.userName);
		ResultSet rs = stmt.executeQuery();
		if(rs.next())
		{
        u = rs.getInt("user_id");
		}
		return u;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("数据库连接异常");
	} finally
	{
		JDBCUtil.close(stmt, con);
	}
	return 0;
	}
	/**
	 * 查询该用户的余额信息，并返回其余额数值
	 * @param user
	 * @return MoneyMsg
	 */
	public MoneyMsg UMoneyQ(User user){
		
		try {
			con = JDBCUtil.getCon();
			String sql = "SELECT * FROM user WHERE user_name = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, LoginController.userName);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				//检查数据库中是否存在该用户名和密码
				if(LoginController.userName.equals(rs.getString("user_name")))
				{
					user.setMoney(rs.getDouble("user_money"));
					if(user.getMoney()>=0) 
					{
						
						return new MoneyMsg(user.getMoney(),user);
					
					}
						else return new MoneyMsg(0.00,user);
					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			System.out.println("数据库连接异常umq");
			e.printStackTrace();
		} finally{
			JDBCUtil.close(stmt, con);
		}
		return null;
		}
/**
 * 更改用户的金额数
 * @param user
 * @return 0则失败，1则成功更改
 */
public int AddMoney(User user,Double m) {
int judge=0;
con = JDBCUtil.getCon();
String sql = "UPDATE user SET user_money=?  WHERE user_name = ?";
try {
	PreparedStatement stmt = con.prepareStatement(sql);
	stmt.setDouble(1, m);
	stmt.setString(2, LoginController.userName);
	stmt.executeUpdate();
	judge=1;
	System.out.println("添加成功");
} catch (SQLException e) {
	judge=0;
	System.out.println("添加失败");
	e.printStackTrace();
}
finally {JDBCUtil.close(stmt, con);}
return judge;
}
/**
 * 查询用户选择的票是否已存在，
 * 已存在的话，判断其状态是否已被预定
 * 有三种情况
 * @param 无参
 * @return Ticket
 */
public  Ticket TicketExists(){
	try {
		con = JDBCUtil.getCon();
		//联合查询---ticket表和seat表，将查到的两张表的结果全部返回,对象的集合
		String sql =  "select ticket.ticket_id,ticket.seat_id,ticket.schedule_id,ticket.price,ticket.status,seat.hall_id,seat.x,seat.y from ticket,seat where ticket.seat_id=seat.seat_id";
		stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();// 返回一个结果集，n个对象
		System.out.println("ticket1");
		List <Ticket> list = new ArrayList<Ticket>();
		
		Ticket ticket =null;//作为返回值，供service层判断
		while (rs.next())
		{
			 ticket =new Ticket();
			//
			/*
			 * 每次新建成一个对象的地址
			 */
				
						//ticket表
						ticket.setTicket(rs.getInt("ticket_id"));//票的单号
						ticket.setSeat(rs.getInt("seat_id"));//座位的单号
						ticket.setSchedule(rs.getInt("schedule_id"));//电影播放的单号，排片序号	
						ticket.setPrice(rs.getDouble("price"));//票价
						ticket.setStatus(rs.getInt("status"));//记录当前票的状态（是否已被预定）
						//seat表
						ticket.setHall(rs.getInt("hall_id"));//记录票的播放厅
						ticket.setX(rs.getInt("x"));//x，座位的横坐标
						ticket.setY(rs.getInt("y"));//y，座位的纵坐标
					list.add(ticket);

		}
		if(ticket==null) 
		{
			
			return null;
		}
		Ticket t = null;
	
		for (int i = 0; i < list.size();i++)
		{
			
			t = list.get(i);
			/**
			 * 一个个比较schedule,无则为无该票信息----3，
			 * 有则根据status判断，0为可买--------------2
			 *  1为不可预订--------------------------------1
			 */
			if(UserQueryMovieController.a==t.getSchedule()) //2和1
			{
				if(PayController.x==t.getX()&&PayController.y==t.getY())//座位的比较,比较status
				{
					if(t.getStatus()==1)
					{return t;}
					else
					{
						return t;
					}

				}
			}
			else //   3的情况
			{
				System.out.println(11231);
				return null;//返回的票对象为空
			}
		}
		System.out.println("dao循环外----"+ticket.toString());
		return ticket;//若查询到票的结果则返回票的集合
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("数据库连接异常");
	} finally{
		JDBCUtil.close(stmt, con);
	}
	
	return null;//数据库中不存在该票
	}



/**
 * 查询hall_id的值 （schedule表中根据schedule_id的值查询）
 * @return hall_id的值
 */
	public double QPrice(){
		
		try {
			con = JDBCUtil.getCon();
			double p =0.0;
			MoviePrice mp = new MoviePrice();
				//两张表的有关price和schedule的所有数据
			String sql = "select movie.movie_price,schedule.schedule_id from movie,schedule where schedule.movie_id=movie.movie_id";//该数据唯一,自增
			stmt = con.prepareStatement(sql);
			System.out.println("当前的排片号schedule_id--"+UserQueryMovieController.a);
			ResultSet rs=stmt.executeQuery();// 返回一个结果集
			while (rs.next())
			{
			if(UserQueryMovieController.a==rs.getInt("schedule_id"))
			{
				
				mp.setPrice(rs.getDouble("movie_price"));
			}
			}
			p = mp.getPrice();
			System.out.println("qprice");
			return p;//若查询到票的结果则返回票的集合
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据库连接异常");
		} finally{
			JDBCUtil.close(stmt, con);
		}
		return 0;//查询不到票的价钱的结果
		}

/**
 * 查询hall_id的值 （schedule表中根据schedule_id的值查询）
 * @return hall_id的值
 */
	public int QHall(){
		
		try {
			
			int n=0;
			con = JDBCUtil.getCon();
			
			String sql = "select hall_id from schedule where schedule_id=?";//该数据唯一,自增
			
			stmt = con.prepareStatement(sql);
			
			stmt.setInt(1,UserQueryMovieController.a);
			System.out.println("qhall");
			ResultSet rs=stmt.executeQuery();// 返回一个结果集
			while (rs.next())
			{
			n = rs.getInt("hall_id");	
			}
		
			return n;//若查询到票的结果则返回票的集合
			
		}
	  catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("数据库连接异常qhall");
		} finally{
			JDBCUtil.close(stmt, con);
		}
		return 0;//查询不到票的价钱的结果
		}
	
/**
 * 先增加seat表中的记录，让其返回一个seat的值，给后面ticket表的增加提供条件
 * @param h  --->hall_id的值
 * @return int 插入成功的与否的判断
 */
		public int ASeat(){
			
			int judge=0;
		
			con = JDBCUtil.getCon();
			try {
				String sql="insert into seat (hall_id,x,y) values (?,?,?)";
				PreparedStatement ptmt=con.prepareStatement(sql);
				ptmt.setInt(1, QHall());
				ptmt.setInt(2, PayController.x);
				ptmt.setInt(3, PayController.y);
				ptmt.executeUpdate();
				
				
				System.out.println("添加影票成功aseat");
				judge=1;
			} catch (SQLException e) {
				judge=0;
				System.out.println("添加影票失败as");
				e.printStackTrace();
			}
			finally {JDBCUtil.close(stmt, con);}
			return judge;
		}
		/**
		 * 
		 */
		public int ASeatT() {
			int judge = 0;
			
			System.out.println("我在外面");
			try {
		 ASeat();
		 con = JDBCUtil.getCon();
		String sql="insert into ticket (seat_id,schedule_id,price,user_id,status) values (?,?,?,?,1)";
		 
		 
		PreparedStatement ptmt=con.prepareStatement(sql);
		 
		ptmt.setInt(1, GSeat());
		 System.out.println(322222);
		ptmt.setInt(2, UserQueryMovieController.a);
		 System.out.println(422222);
		ptmt.setDouble(3, QPrice());
		ptmt.setInt(4, GUser());
		ptmt.executeUpdate();
		System.out.println("添加影票成功aseat");
		judge=1;
	} catch (SQLException e) {
		judge=0;
		System.out.println("添加影票失败");
		e.printStackTrace();
	}
	finally {JDBCUtil.close(stmt, con);}
	return judge;
		}
		/**
		 * ticket表
		 * 改变记录的用户名和status (0->1)，以及seat_id
		 * @return 判断值
		 */
public int CSeatT(){
			
			int judge=0;
		
			try {
				
				con = JDBCUtil.getCon();
				System.out.println("cseatt1");
				String sql="UPDATE ticket SET user_id=? ,status=1,seat_id=? WHERE seat_id = ?";
				PreparedStatement stmt=con.prepareStatement(sql);
				System.out.println("cseatt2");
				stmt.setInt(1, GUser());
				stmt.setInt(2, GSeat());
				stmt.setInt(3, GSeat());
				stmt.executeUpdate();

				judge=1;
			} catch (SQLException e) {
				judge=0;
				System.out.println("添加影票失败");
				e.printStackTrace();
			}
			finally {JDBCUtil.close(stmt, con);}
			return judge;
		}
		
		

/** 
 *  改的操作--->对于ticket表的操作  购票之后减少hall表中的seat座位，让其减少1		
 *  用来处理票不存在的购票引发的其他表的数据的同步
 * @param 无参，调用其他函数来得到所需的值
 * @return 修改成功与否的判断   1--成功，0---失败
 */
		public int DownSeat(){
			
			int judge=0;
			con = JDBCUtil.getCon();
			try {
				int n=0;//剩余的座位数
				String s = "select hall_seat from hall where hall_id=?";//该数据唯一,自增
				PreparedStatement stmt=con.prepareStatement(s);
				stmt.setInt(1,QHall());
				ResultSet rs=stmt.executeQuery();// 返回一个结果集
				while (rs.next())
				{
				n = rs.getInt("hall_seat");
				}
				
				JDBCUtil.close(stmt, con);
			    con = JDBCUtil.getCon();
				
				String sql="update hall set hall_seat=? where hall_id=?";
				PreparedStatement ptmt=con.prepareStatement(sql);
				ptmt.setInt(1,n-1);
				ptmt.setInt(2, QHall());
				ptmt.executeUpdate();
				judge=1;
				System.out.println("添加成功");
			} catch (SQLException e) {
				judge=0;
				System.out.println("添加失败");
				e.printStackTrace();
			}
			finally {JDBCUtil.close(stmt, con);}
			return judge;
		}
		/**
		 * 改变订单表的值
		 * @return 修改成功与否的判断   1--成功，0---失败
		 */
		public int ASales(){
			
			int judge=0;
			con = JDBCUtil.getCon();
			try {
				
				//进行增加订单记录的操作.... 6个元素
				String sql="insert into sales (schedule_id,user_id,spent,status,payment_time,seat_id) values (?,?,?,?,?,?)";
				PreparedStatement ptmt=con.prepareStatement(sql);
				ptmt.setInt(1,UserQueryMovieController.a);
				ptmt.setInt(2, GUser());
			    ptmt.setDouble(3, QPrice());
				ptmt.setInt(4, 1);
				
		        Timestamp stamp =   new Timestamp(System.currentTimeMillis());//获得系统时间.
		    	ptmt.setTimestamp(5, stamp);
		    	
		    	ptmt.setInt(6, GSeat());
		    	
				ptmt.executeUpdate();
				judge=1;
				System.out.println("sales添加成功");
			} catch (SQLException e) {
				judge=0;
				System.out.println("sales添加失败");
				e.printStackTrace();
			}
			finally {JDBCUtil.close(stmt, con);}
			return judge;
		}
		
}
