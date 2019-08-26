package com.lzh.cinema.entity;

public class MyMovie
{
	private int hall;  //排片的id
	
	private int session;  //电影场次
	
	private String moiveName;  //电影名

	private String date;//电影日期，转化为字符串形式的日期
	
	private Integer schedule;//排片单号
	
	private double money_spent;//花费的金额

private int x;//列数

private int y;//行数

private int seat;//座位id

/**
 * @param hall
 * @param session
 * @param moiveName
 * @param date
 * @param schedule
 * @param money_spent
 * @param x
 * @param y
 * @param seat
 */
public MyMovie(
		int hall, int session, String moiveName, String date, Integer schedule, double money_spent, int x, int y,
		int seat
)
{
	super();
	this.hall = hall;
	this.session = session;
	this.moiveName = moiveName;
	this.date = date;
	this.schedule = schedule;
	this.money_spent = money_spent;
	this.x = x;
	this.y = y;
	this.seat = seat;
}

public int getHall()
{
	return hall;
}

public void setHall(int hall)
{
	this.hall = hall;
}

public int getSession()
{
	return session;
}

public void setSession(int session)
{
	this.session = session;
}

public String getMoiveName()
{
	return moiveName;
}

public void setMoiveName(String moiveName)
{
	this.moiveName = moiveName;
}

public String getDate()
{
	return date;
}

public void setDate(String date)
{
	this.date = date;
}

public Integer getSchedule()
{
	return schedule;
}

public void setSchedule(Integer schedule)
{
	this.schedule = schedule;
}

public double getMoney_spent()
{
	return money_spent;
}

public void setMoney_spent(double money_spent)
{
	this.money_spent = money_spent;
}

public int getX()
{
	return x;
}

public void setX(int x)
{
	this.x = x;
}

public int getY()
{
	return y;
}

public void setY(int y)
{
	this.y = y;
}

public int getSeat()
{
	return seat;
}

public void setSeat(int seat)
{
	this.seat = seat;
}

/**
 * 
 */
public MyMovie()
{
	super();
	// TODO Auto-generated constructor stub
}

}