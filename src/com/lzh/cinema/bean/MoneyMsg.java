package com.lzh.cinema.bean;

/**
 * 放查询到的用户余额的信息
 * @author 林泽鸿
 *
 */
public class MoneyMsg
{
private Double money;
private Object user;
/**
 * @param money
 * @param user
 */
public MoneyMsg(Double money, Object user)
{
	super();
	this.money = money;
	this.user = user;
}

public Double getMoney()
{
	return money;
}

public void setMoney(Double money)
{
	this.money = money;
}

public Object getUser()
{
	return user;
}

public void setUser(Object user)
{
	this.user = user;
}

/**
 * 
 */
public MoneyMsg()
{
	super();
	// TODO Auto-generated constructor stub
}

@Override
public String toString()
{
	return "MoneyMsg [money=" + money + ", user=" + user + "]";
}


}
