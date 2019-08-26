package com.lzh.cinema.property;

import com.lzh.cinema.entity.UserQueryMovie;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
 
/**
 *表格属性对应的元素
 *存在set,get方法,包括一个属性方法
 * @author 林泽鸿
 */
public class UserQueryMovieProperty {
	//java bean propery
    private IntegerProperty hall = new SimpleIntegerProperty();
    private IntegerProperty session = new SimpleIntegerProperty();
    private  StringProperty movieName  = new SimpleStringProperty();
	private StringProperty date    = new SimpleStringProperty();
	 private IntegerProperty schedule = new SimpleIntegerProperty();
	UserQueryMovie userQueryMovie = new UserQueryMovie();
	/**
	 * 传进来与数据库对应的实体类，将其进行转化为properties，变成属性才能用于表格
	 * @param userQueryMovie
	 */
    public UserQueryMovieProperty(UserQueryMovie userQueryMovie){
    	
        this.setHall(new SimpleIntegerProperty(userQueryMovie.getHall()));
        this.setSession(new SimpleIntegerProperty(userQueryMovie.getSession()));
        this.setMovieName(new SimpleStringProperty(userQueryMovie.getMoiveName()));
        this.setDate(new SimpleStringProperty(userQueryMovie.getDate()));
        this.setSchedule(new SimpleIntegerProperty(userQueryMovie.getSchedule()));
  
    }


    /**
     * 属性的构造
     * @return
     */
    public IntegerProperty hallProperty() {
	return hall;
}
    public IntegerProperty sessionProperty() {
	return session;
}   
    
    public StringProperty movieNameProperty() {
	return movieName;
}   
    
    public StringProperty dateProperty() {
	return date;
}
    public IntegerProperty scheduleProperty() {
	return schedule;
}   
    
    
	/**
	 * 
	 */
	public UserQueryMovieProperty()
	{
		super();
		// TODO Auto-generated constructor stub
	}



	/**
	 * @param hall
	 * @param session
	 * @param movieName
	 * @param date
	 * @param schedule
	 * @param userQueryMovie
	 */
	public UserQueryMovieProperty
	(
			IntegerProperty hall, IntegerProperty session, StringProperty movieName, StringProperty date,
			IntegerProperty schedule, UserQueryMovie userQueryMovie
	)
	{
		super();
		this.hall = hall;
		this.session = session;
		this.movieName = movieName;
		this.date = date;
		this.schedule = schedule;
		this.userQueryMovie = userQueryMovie;
	}






	public IntegerProperty getHall()
	{
		return hall;
	}
							
	public IntegerProperty getSession()
	{
		return session;
	}
	public IntegerProperty getSchedule()
	{
		return schedule;
	}
	public StringProperty getMovieName()
	{
		return movieName;
	}
	public StringProperty getDate()
	{
		return date;
	}
						public void setHall(IntegerProperty hall)
						{
							this.hall = hall;
						}
	
						public void setSchedule(IntegerProperty schedule)
						{
							this.schedule = schedule;
						}
						public void setSession(IntegerProperty session)
						{
							this.session = session;
						}
	
						public void setMovieName(StringProperty movieName)
						{
							this.movieName = movieName;
						}
	
							public void setDate(StringProperty date)
							{
								this.date = date;
							}

}