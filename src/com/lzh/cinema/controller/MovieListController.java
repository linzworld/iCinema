package com.lzh.cinema.controller;

import java.util.List;

import com.lzh.cinema.bean.Msg;
import com.lzh.cinema.entity.Movie;
import com.lzh.cinema.entity.MovieList;
import com.lzh.cinema.entity.MyMovie;
import com.lzh.cinema.entity.UserQueryMovie;
import com.lzh.cinema.service.MovieListService;
import com.lzh.cinema.service.TicketService;

/*
 * 放置与电影列表相关的调用方法
 */
public class MovieListController
{
	private MovieListService movieListService = new MovieListService();
	private TicketService ticketService = new TicketService();
/**
 * 用户大厅左侧的电影列表信息
 * @return 一个装有电影列表信息的容器
 */
	public  List<MovieList> TwoQurey(){
		
		//调用service层的方法
		return movieListService.TwoQurey();
	}
	
	
	/**
	 * 用户查询电影的初始界面
	 * @return
	 */
	public  List<UserQueryMovie> UserQurey(){
		
		//调用service层的方法
		return movieListService.UserQurey();
	}
	
	/**
	 * 用户查询电影的按钮 触发的效果
	 * 将全部之前的查询到的电影进行筛选
	 * @return 按用户输入的电影名进行查询，从而得到的电影信息的容器
	 */
	public  List<UserQueryMovie> UserQureybt(String uQueryCt){
		
		//调用service层的方法
		return movieListService.UserQureybt(uQueryCt);
	}
	
	/**
	 * 用户查询自己的电影信息
	 */
public  List<MyMovie> MMovie(){
		
		//调用service层的方法
		return ticketService.SeMovie();
	}

/**
 * 退票的功能
 * @return Msg
 */
public  Msg Refund(){
	
	//调用service层的方法
	return ticketService.Refund();
}
/**
 * 管理员查询电影信息
 * @return List<Movie>
 */
public  List<Movie> QMovieA(){
	
	//调用service层的方法
	return movieListService.QMovieA();
}

/**
 * 管理员查询电影的按钮 触发的效果
 * 将全部之前的查询到的电影进行筛选
 * @return 按管理员输入的电影名进行查询，从而得到的电影信息的容器
 */
public  List<Movie> AQureybt(String aQueryCt){
	
	//调用service层的方法
	return movieListService.AQureybt(aQueryCt);
}
/**
 * 管理员删除电影
 * @return 成功判断条件1，0
 */
public  int DeleteM(){
	
	//调用service层的方法
	return movieListService.DeleteM();
}
/**
 * 管理员更改电影
 * @return 成功判断条件1，0
 */
public  int AlterM(Movie m){
	
	//调用service层的方法
	return movieListService.AlterM( m);
}

/**
 * 管理员增加电影
 * @return 成功判断条件1，0
 */
public  int AddM(Movie m){
	
	//调用service层的方法
	return movieListService.AddM(m);
}


}
