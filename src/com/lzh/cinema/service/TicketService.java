package com.lzh.cinema.service;

import java.util.ArrayList;
import java.util.List;

import com.lzh.cinema.bean.MoneyMsg;
import com.lzh.cinema.bean.Msg;
import com.lzh.cinema.dao.BuyDao;
import com.lzh.cinema.dao.GetCell;
import com.lzh.cinema.dao.TicketDao;
import com.lzh.cinema.dao.UserQueryMovieDao;
import com.lzh.cinema.entity.MovieList;
import com.lzh.cinema.entity.MyMovie;
import com.lzh.cinema.entity.User;
import com.lzh.cinema.entity.UserQueryMovie;
import com.lzh.cinema.property.UserQueryMovieProperty;

public class TicketService
{
	BuyDao bd = new BuyDao();
	TicketDao td = new TicketDao();
	/**
	 * 先调用UQMD的方法
	 * 得到集合之后传入TicketDao中
	 * 返回一个筛选后生成影票信息的集合
	 * 选择出用户本身的电影列表
	 * @return List 装有用户个人的电影信息
	 */
	public List<MyMovie> SeMovie()
	{
		
		
		UserQueryMovieDao uqmd = new UserQueryMovieDao();//先得到电影信息集合
		List <UserQueryMovie> list1 = new ArrayList<UserQueryMovie>();
		
		TicketDao td = new TicketDao();//通过用户id进行筛选
		List <MyMovie> list = new ArrayList<MyMovie>();//得到最后的结果--->对象集合
		
		List <MyMovie> lists = new ArrayList<MyMovie>();//对象集合里面装的是seat_id的值
		
		
		list1 = uqmd.UserQuery();//list1先得到电影信息集合，显示在查询电影的对象信息
		
		UserQueryMovie uqm = null;
		MyMovie mm = null;
		
		//不断地拿出list1中的schedule的值
		  for (int i = 0; i <=list1.size()-1; i++) 
		  {
			  lists = null;//用完之后置空
			  uqm = null;
			  
			  uqm = new UserQueryMovie();//全部的电影列表中的对象
			uqm = list1.get(i);//将其对象中的schedule传入调用的方法中
			
			//一个list1集合中的对象（电影列表） 所对应的lists集合中的对象个数（该电影单独一个用户订的票数）
			lists = td.GetSeat(uqm.getSchedule());//得到装有seat_id的集合---->
			
			
					  for (int j = 0; j <=lists.size()-1; j++) //传入seat_id,得到座位中x,y的值
					  {
						  int n = 0;
						  
						  n = lists.get(j).getSeat();
						  
						  //先得到seat_id,x,y,属性的拼接功能。。全部放到另外的一个对象中（影票信息MyMovie）
						  mm = td.GetXY(n);
						  
						  //电影列表中的对象中的属性转移到影票信息上
						  mm.setDate(uqm.getDate());
						  mm.setHall(uqm.getHall());
						  mm.setMoiveName(uqm.getMoiveName());
						  mm.setSession(uqm.getSession());
						  mm.setSchedule(uqm.getSchedule());
						 mm.setHall(uqm.getSession());
						  list.add(mm);
						  
					  }
		  }
		
				return list;//得到重新封装好的影票信息的集合
				
		}

	/***
		 * 对查找到的全部电影进行筛选，过滤掉不需要的实体类对象
		 */
			public List<MyMovie>UserQureybt(String uQueryCt){
		        //转化前
		        //从数据库中拿到的数据，原始数据
		        List<MyMovie> list = new ArrayList<MyMovie>(); 
		        
		        List<MyMovie> listbt = new ArrayList<MyMovie>();//筛选后的对象集合
		      //调用dao层的方法，拿到所有查询的电影的最初信息
		       list =SeMovie();//调用已经处理好的结果list  用户的全部电影信息
				  MyMovie mm=null; 
				  
				  for(int i=0;i<list.size();i++) {
			       mm=list.get(i);
			       if(mm.getMoiveName().equals(uQueryCt))
				  {listbt.add(mm);}
				  }
		       //对查询到的所有电影进行判断，分析，把拿到的对象放在新容器中
		       
		       return listbt;
		 
			}
	
			public Msg Refund()
			{
				//先加钱
				User user = new User();
				MoneyMsg mg = bd.UMoneyQ(user);// 用于查询余额
				/* System.out.println("我现在的余额"+mg.getMoney()); */
				double mp = bd.QPrice();
				
				double m = mg.getMoney()+mp;
				//退票流程
				if(td.RefundS()==1) 
				{
				if(td.RefundT()==1)
					{

					//退票成功成功的情况下
					bd.AddMoney(user,m);
					
					 return new Msg("退票成功", null);
					}
				}
				else
					{return new Msg("refunds退票失败", null);}
				return null;
					
			}
}