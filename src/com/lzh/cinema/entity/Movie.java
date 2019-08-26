package com.lzh.cinema.entity;


/**
 * 装电影信息的对象
 *与数据库对应
 * @author 林泽鸿
 *
 */
public class Movie
{
private int movieid;//电影的id(重命名)
private String movieName;//电影名称
private String diretor;//电影的导演
private String mainActor;//电影的主演
private double Price;//票价
private int duration;// 电影的播放时长

/**
 * @param movieid
 * @param movieName
 * @param diretor
 * @param mainActor
 * @param price
 * @param duration
 */
public Movie(int movieid, String movieName, String diretor, String mainActor, double price, int duration)
{
	super();
	this.movieid = movieid;
	this.movieName = movieName;
	this.diretor = diretor;
	this.mainActor = mainActor;
	this.Price = price;
	this.duration = duration;
}
/**
 * 
 */
public Movie()
{
	super();
	// TODO Auto-generated constructor stub
}


public int getMovieid()
{
	return movieid;
}
public void setMovieid(int movieid)
{
	this.movieid = movieid;
}
public String getMovieName()
{
	return movieName;
}
public void setMovieName(String movieName)
{
	this.movieName = movieName;
}
public String getDiretor()
{
	return diretor;
}
public void setDiretor(String diretor)
{
	this.diretor = diretor;
}
public String getMainActor()
{
	return mainActor;
}
public void setMainActor(String mainActor)
{
	this.mainActor = mainActor;
}
public double getPrice()
{
	return Price;
}
public void setPrice(double price)
{
	Price = price;
}
public int getDuration()
{
	return duration;
}
public void setDuration(int duration)
{
	this.duration = duration;
}

}
