package com.lzh.cinema.entity;


public class UserQueryMovie
{
	private int hall;  //排片的id
	
	private int session;  //电影场次
	
	private String moiveName;  //电影名

	private String date;//电影日期，转化为字符串形式的日期
	
	private Integer schedule;//排片单号



	/**
	 * @param hall
	 * @param session
	 * @param moiveName
	 * @param date
	 * @param schedule
	 */
	public UserQueryMovie(int hall, int session, String moiveName, String date, Integer schedule)
	{
		super();
		this.hall = hall;
		this.session = session;
		this.moiveName = moiveName;
		this.date = date;
		this.schedule=schedule;
	}

	/**
	 * 
	 */
	public UserQueryMovie()
	{
		super();
		// TODO Auto-generated constructor stub
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
	
}
