package com.lzh.cinema.service;

import java.util.ArrayList;
import java.util.List;

import com.lzh.cinema.bean.Msg;
import com.lzh.cinema.controller.MovieListController;
import com.lzh.cinema.dao.BuyDao;
import com.lzh.cinema.dao.MovieDao;
import com.lzh.cinema.dao.MovieListDao;
import com.lzh.cinema.dao.UserQueryMovieDao;
import com.lzh.cinema.entity.Movie;
import com.lzh.cinema.entity.MovieList;
import com.lzh.cinema.entity.MoviePrice;
import com.lzh.cinema.entity.UserQueryMovie;
import com.lzh.cinema.property.UserQueryMovieProperty;
import com.lzh.cinema.view.UserQueryMovieController;


/**
 * 电影列表的业务逻辑
 * @author 林泽鸿
 *
 */
public class MovieListService
{
	private MovieListDao movieListDao = new MovieListDao();
	
	private MovieDao movieDao = new MovieDao();
	
	private BuyDao buyDao = new BuyDao();
	
	private UserQueryMovieDao userQueryMovieDao = new UserQueryMovieDao();
	
	//两个表联合查询
	public List<MovieList> TwoQurey(){
		
		 /**
		 * 调用DAO层
		 */
		return movieListDao.TwoQuery();
	}
	
	
	
	
	//两个表联合查询
	/**
	 * 用户查询电影
	 * @return
	 */
	public List<UserQueryMovie>UserQurey(){	
		 /**
		 * 调用DAO层
		 */
		return userQueryMovieDao.UserQuery();
	}
	
	
	/***
	 * 用户
	 * 对查找到的全部电影进行筛选，过滤掉不需要的实体类对象
	 */
		public List<UserQueryMovie>UserQureybt(String uQueryCt){
	        //转化前
	        //从数据库中拿到的数据，原始数据
	        List<UserQueryMovie> list = new ArrayList<UserQueryMovie>(); 
	        
	        List<UserQueryMovie> listbt = new ArrayList<UserQueryMovie>();//筛选后的对象集合
	      //调用dao层的方法，拿到所有查询的电影的最初信息
	       list = userQueryMovieDao.UserQuery();
			  UserQueryMovie uqm=null; 
			  System.out.println("list的长度-----"+list.size());
			  for(int i=0;i<list.size();i++) {
		       uqm=list.get(i);
		       if(uqm.getMoiveName().equals(uQueryCt))
			  {listbt.add(uqm);}
			  }
	       //对查询到的所有电影进行判断，分析，把拿到的对象放在新容器中
	       
	       return listbt;
	 
		}
		
		/**
		 * 管理员查询电影
		 * @return
		 */
		public List<Movie>QMovieA(){	
			 /**
			 * 调用DAO层
			 */
			return movieDao.QMovieA();
		}
		
		/***
		 * 管理员
		 * 对查找到的全部电影进行筛选，过滤掉不需要的实体类对象
		 */
			public List<Movie>AQureybt(String aQueryCt){
		        //转化前
		        //从数据库中拿到的数据，原始数据
		        List<Movie> list = new ArrayList<Movie>(); 
		        
		        List<Movie> listbt = new ArrayList<Movie>();//筛选后的对象集合
		      //调用dao层的方法，拿到所有查询的电影的最初信息
		       list = movieDao.QMovieA();
				Movie m=null; 
				  System.out.println("管理员电影list的长度-----"+list.size());
				  for(int i=0;i<list.size();i++) {
			       m=list.get(i);
			       if(m.getMovieName().equals(aQueryCt))
				  {listbt.add(m);}
				  }
		       //对查询到的所有电影进行判断，分析，把拿到的对象放在新容器中
		       
		       return listbt;
		 
			}
			/**
			 * 管理员删除电影
			 * @return 判断结果1或0
			 */
			public int DeleteM(){	
				 /**
				 * 调用DAO层
				 */
				return movieDao.DeleteM();
			}
			
			/**
			 * 管理员更改电影
			 * @return 判断结果1或0
			 */
			public int AlterM(Movie m){	
				 /**
				 * 调用DAO层
				 */
				return movieDao.AlterM(m);
			}
			/**
			 * 管理员增加电影信息
			 * @return 判断结果1或0
			 */
			public int AddM(Movie m){	
				 /**
				 * 调用DAO层
				 */
				return movieDao.AddM(m);
			}

		
}