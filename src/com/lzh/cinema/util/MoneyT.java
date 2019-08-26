package com.lzh.cinema.util;

import com.lzh.cinema.dao.BuyDao;
import com.lzh.cinema.entity.User;
/**
 * 将金额转化为字符串
 * double --->String
 * @author 林泽鸿
 *
 */
public class MoneyT
{
	public static String t(){
	  BuyDao buyDao = new BuyDao();
	  User user = new User();
	  String m = String .valueOf(buyDao.UMoneyQ(user).getMoney());
	  return m;
	}
	}
