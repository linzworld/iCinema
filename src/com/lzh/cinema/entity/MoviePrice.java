package com.lzh.cinema.entity;

public class MoviePrice
{
private double price;//电影的价钱

private int schedule;//电影的排片

public double getPrice()
{
	return price;
}

public void setPrice(double price)
{
	this.price = price;
}

public int getSchedule()
{
	return schedule;
}

public void setSchedule(int schedule)
{
	this.schedule = schedule;
}

@Override
public String toString()
{
	return "MoviePrice [price=" + price + ", schedule=" + schedule + "]";
}

/**
 * 
 */
public MoviePrice()
{
	super();
	// TODO Auto-generated constructor stub
}

/**
 * @param price
 * @param schedule
 */
public MoviePrice(double price, int schedule)
{
	super();
	this.price = price;
	this.schedule = schedule;
}



}
