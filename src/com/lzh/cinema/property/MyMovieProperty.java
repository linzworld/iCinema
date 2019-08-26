package com.lzh.cinema.property;

import com.lzh.cinema.entity.MyMovie;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 放置影票的属性，用户查询个人的影票记录
 * 与表格中的每一行相对应
 * @author 林泽鸿
 */
public class MyMovieProperty
{
	//java bean propery
    private IntegerProperty hall = new SimpleIntegerProperty();
    private IntegerProperty session = new SimpleIntegerProperty();
    private  StringProperty movieName  = new SimpleStringProperty();
	private StringProperty date    = new SimpleStringProperty();
	 private IntegerProperty schedule = new SimpleIntegerProperty();
	 
	    private IntegerProperty seat = new SimpleIntegerProperty();
	    private IntegerProperty x = new SimpleIntegerProperty();
	    private IntegerProperty y = new SimpleIntegerProperty();
	    
	    MyMovie myMovie = new MyMovie();
	/**
	 * 属性的构造方法
	 * 传进来与数据库对应的实体类，将其进行转化为properties，变成属性才能用于表格
	 * @param myMovie
	 */
    public MyMovieProperty(MyMovie myMovie){
    	
        this.setHall(new SimpleIntegerProperty(myMovie.getHall()));
        this.setSession(new SimpleIntegerProperty(myMovie.getSession()));
        this.setMovieName(new SimpleStringProperty(myMovie.getMoiveName()));
        this.setDate(new SimpleStringProperty(myMovie.getDate()));
        this.setSchedule(new SimpleIntegerProperty(myMovie.getSchedule()));
        this.setSeat(new SimpleIntegerProperty(myMovie.getSeat()));
        this.setX(new SimpleIntegerProperty(myMovie.getX()));
        this.setY(new SimpleIntegerProperty(myMovie.getY()));
  
    }


	public IntegerProperty getHall()
	{
		return hall;
	}


	public void setHall(IntegerProperty hall)
	{
		this.hall = hall;
	}


	public IntegerProperty getSession()
	{
		return session;
	}


	public void setSession(IntegerProperty session)
	{
		this.session = session;
	}


	public StringProperty getMovieName()
	{
		return movieName;
	}


	public void setMovieName(StringProperty movieName)
	{
		this.movieName = movieName;
	}


	public StringProperty getDate()
	{
		return date;
	}

	
	
	

	public void setDate(StringProperty date)
	{
		this.date = date;
	}


	public IntegerProperty getSchedule()
	{
		return schedule;
	}


	public void setSchedule(IntegerProperty schedule)
	{
		this.schedule = schedule;
	}


	public IntegerProperty getSeat()
	{
		return seat;
	}


	public void setSeat(IntegerProperty seat)
	{
		this.seat = seat;
	}


	public IntegerProperty getX()
	{
		return x;
	}


	public void setX(IntegerProperty x)
	{
		this.x = x;
	}


	public IntegerProperty getY()
	{
		return y;
	}


	public void setY(IntegerProperty y)
	{
		this.y = y;
	}


	public MyMovie getMyMovie()
	{
		return myMovie;
	}


	public void setMyMovie(MyMovie myMovie)
	{
		this.myMovie = myMovie;
	}


	/**
	 * @param hall
	 * @param session
	 * @param movieName
	 * @param date
	 * @param schedule
	 * @param seat
	 * @param x
	 * @param y
	 * @param myMovie
	 */
	public MyMovieProperty(
			IntegerProperty hall, IntegerProperty session, StringProperty movieName, StringProperty date,
			IntegerProperty schedule, IntegerProperty seat, IntegerProperty x, IntegerProperty y, MyMovie myMovie
	)
	{
		super();
		this.hall = hall;
		this.session = session;
		this.movieName = movieName;
		this.date = date;
		this.schedule = schedule;
		this.seat = seat;
		this.x = x;
		this.y = y;
		this.myMovie = myMovie;
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
    public IntegerProperty seatProperty() {
    	return seat;
    }
    public IntegerProperty xProperty() {
    	return x;
    }
    public IntegerProperty yProperty() {
    	return y;
    }
    
    

    
}
