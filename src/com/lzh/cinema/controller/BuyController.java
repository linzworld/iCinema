package com.lzh.cinema.controller;

import com.lzh.cinema.bean.MoneyMsg;
import com.lzh.cinema.bean.Msg;

import com.lzh.cinema.service.BuyService;


public class BuyController
{
	private BuyService buyService = new BuyService();
	/**
	 * 查询用户余额
	 * @param userName
	 * @param password
	 * @return 
	 */
	public  MoneyMsg UMoneyQ(String userName,String password){
	
		return buyService.UMoneyQ(userName, password);
	}
/**
 * 增加用户余额
 * @param m 增加之后的值
 * @return MoneyMsg 放与余额有关的信息对象
 */
	public  MoneyMsg AddMoney(Double m){
		
		return buyService.AddMoney(m);
	}
	/**
	 * 查询是否存在该票，以及该票的状态
	 * @return
	 */
	public  Msg Judge(){
		
		return buyService.Judge();
	}
	
}

