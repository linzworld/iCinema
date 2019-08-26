package com.lzh.cinema.service;

import java.util.ArrayList;
import java.util.List;

import com.lzh.cinema.bean.MoneyMsg;
import com.lzh.cinema.bean.Msg;
import com.lzh.cinema.dao.BuyDao;
import com.lzh.cinema.dao.MovieListDao;
import com.lzh.cinema.dao.UserDao;
import com.lzh.cinema.dao.UserQueryMovieDao;
import com.lzh.cinema.entity.Movie;
import com.lzh.cinema.entity.MoviePrice;
import com.lzh.cinema.entity.Ticket;
import com.lzh.cinema.entity.User;
import com.lzh.cinema.entity.UserQueryMovie;
import com.lzh.cinema.view.LoginController;
import com.lzh.cinema.view.PayController;
import com.lzh.cinema.view.UserQueryMovieController;

public class BuyService
{
	BuyDao buyDao = new BuyDao();

	/**
	 * 用户的余额
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public MoneyMsg UMoneyQ(String userName, String password)
	{

		User user = new User();
		/*	*//**
				 * 调用判空的方法
				 *//*
					 * if(ValidateUtil.isInvalidUserName(userName)==true){
					 * 
					 * //用一个对象存储返回值 return new MoneyMsg(user.getMoney(),null); }
					 */
		/**
		 * 调用DAO层，检查是否存在该用户和 密码是否正确 以及确认其管理权限
		 */
		user.setUserName(userName);
		user.setPassword(password);
		// 调用了dao层
		MoneyMsg mmsg = buyDao.UMoneyQ(user);
		return mmsg;
	}

	/**
	 * 用户充值金额
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public MoneyMsg AddMoney(Double m)
	{
		int judge = 0;
		User user = new User();
		/*	*//**
				 * 调用判空的方法
				 *//*
					 * if(ValidateUtil.isInvalidUserName(userName)==true){
					 * 
					 * //用一个对象存储返回值 return new MoneyMsg(user.getMoney(),null); }
					 */
		/**
		 * 调用DAO层，检查是否存在该用户和 密码是否正确 以及确认其管理权限
		 */
		user.setUserName(LoginController.userName);
		user.setPassword(LoginController.password);
		// 调用了dao层
		judge = buyDao.AddMoney(user, m);
		if (judge == 1)
			return new MoneyMsg(m, user);
		else
			return new MoneyMsg(0.00, null);
	}

	/**
	 * 去调用buyDao得到Msg信息结果
	 * 对查找到的全部电影进行筛选, 判断用户选择的电影票是否未被预定(IsOrder)
	 * 
	 * @return 最后得到一个对象或者为null
	 */
	public Msg IsOrder()
	{
		Ticket ticket = new Ticket();
			
		// 转化前
		// 从数据库中拿到的数据，原始数据
		ticket = buyDao.TicketExists();
		System.out.println("isorder");

		if (ticket == null) // 查询的影票不存在   3
		{
			return new Msg("不存在该影票", null);
		}  
		else if (ticket.getStatus()== 0)// 影票未被购买     2
				{
					return new Msg("该票未被预定", ticket);
				} else if (ticket.getStatus() == 1) // 影票已被购买    1
				{
					return new Msg("该票已被预定", null);
				}
		return null;
	}


	/**
	 * 调用IsOrder这个方法（service中）
	 * 判断购买影票，进而查询结果的三种情况 得到购票结果
	 * 
	 * @return 返回装有信息的对象
	 */
	public Msg Judge()
	{
		User user = new User();
		Msg msg = IsOrder();// 将查询结果传入msg
		MoneyMsg mg = buyDao.UMoneyQ(user);// 用于查询余额
		/* System.out.println("我现在的余额"+mg.getMoney()); */
		double mp = buyDao.QPrice();
		
		double m = mg.getMoney()-mp;
		if (mg.getMoney() < mp) // 余额不足
		{
			return new Msg("余额不足", null);
		} 
		else // 余额充足的情况下
		{
			if (msg.getResult().equals("该票已被预定")) // 1 该票已被预定, 不用访问数据库
			{
				return new Msg("该票已被预定", null);
			} else if (msg.getResult().equals("该票未被预定"))// 2 进行改的操作 update ___ set,     改ticket表，增sales表
			{
							int judge = buyDao.CSeatT();//对ticket表的操作
								if (judge == 1)
								{
											// hall表剩余座位的更改
											if (buyDao.DownSeat() == 1)
											{
														// sales表的更改--status变为1
														if (buyDao.ASales() == 1)
														{
															System.out.println("购买成功");
															buyDao.AddMoney(user,m);
															return new Msg("购买成功", user);
														} 
														else
														{// sales表的更改--status变为1
															return new Msg("sales表增加失败", null);
														}
											}
											else// hall表剩余座位的更改
												       return new Msg("hall表座位减少失败", null);
						
								} 
								else
								{
									return new Msg("AseatT影票购买失败", null);
								}
			} 
			else 
				if (msg.getResult().equals("不存在该影票"))// 3 数据库 进行增的操作 insert into
			{
					// 先通过schedule表中的schedule_id查询找到hall_id的值
					int h = buyDao.QHall();
								if (h == 0)
								{
									return new Msg("数据库在查询hall的值的过程(QHall)失败", null);
								} 
								
								
								else // 进行增的操作
								{
									// 购票，依次增加两张表的记录（seat表，ticket表）
									int judge = buyDao.ASeatT();
											if (judge == 1)
											{
														// hall表剩余座位的更改
														if (buyDao.DownSeat() == 1)
														{
															// sales表的更改--status变为1
															if (buyDao.ASales() == 1)
															{
																System.out.println("购买成功");
																buyDao.AddMoney(user,m);
																return new Msg("购买成功", user);
															} 
															else
															{// sales表的更改--status变为1
																return new Msg("sales表增加失败", null);
															}
											} 
											else// hall表剩余座位的更改
													return new Msg("hall表座位减少失败", null);
					} 
				else
					{
						return new Msg("AseatT影票购买失败", null);
					}
					
				}
			
			}
		}
		return null;

	}
}
