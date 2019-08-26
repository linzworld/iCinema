package com.lzh.cinema.entity;
/**
 * 存储用户大厅中的电影预览信息
 * 有播放厅，电影场次，电影名字
 * 借助多表查询得到结果，封装在该对象中
 * @author 林泽鸿
 *
 */
public class MovieList {
	private int hall;  //排片的id
	
	private int session;  //电影场次
	
	private String moiveName;  //电影名

	/**
	 * 
	 */
	public MovieList()
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

	/**
	 * @param hall
	 * @param session
	 * @param moiveName
	 */
	public MovieList(int hall, int session, String moiveName)
	{
		super();
		this.hall = hall;
		this.session = session;
		this.moiveName = moiveName;
	}


}