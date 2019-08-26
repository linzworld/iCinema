package com.lzh.cinema.property;

import com.lzh.cinema.entity.Movie;
import com.lzh.cinema.entity.UserQueryMovie;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 管理员查询电影的列表
 * @author 林泽鸿
 *
 */
public class MovieProperty
{
	  private IntegerProperty movieid = new SimpleIntegerProperty();//电影id
	   
	    private  StringProperty movieName  = new SimpleStringProperty();//电影名字
		private StringProperty mainactor    = new SimpleStringProperty();//主演
		private StringProperty director    = new SimpleStringProperty();//导演
		
		 private DoubleProperty price = new SimpleDoubleProperty();//票价
		 private IntegerProperty duration = new SimpleIntegerProperty();//持续时长
		 
		Movie movie = new Movie();
		/**
		 * 传进来与数据库对应的实体类，将其进行转化为properties，变成属性才能用于表格
		 * @param userQueryMovie
		 */
	    public MovieProperty(Movie movie){
	    	
	        this.setMovieid(new SimpleIntegerProperty(movie.getMovieid()));
	        this.setMovieName(new SimpleStringProperty(movie.getMovieName()));
	        
	        this.setMainactor(new SimpleStringProperty(movie.getMainActor()));
	        this.setDirector(new SimpleStringProperty(movie.getDiretor()));
	        
	        this.setPrice(new SimpleDoubleProperty(movie.getPrice()));
	        this.setDuration(new SimpleIntegerProperty(movie.getDuration()));
	    }




		/**
	     * 属性的构造
	     * @return
	     */
	    public IntegerProperty movieidProperty() {
		return movieid;
	}
	    public StringProperty movieNameProperty() {
		return movieName;
	}
	    
	    public StringProperty mainactorProperty() {
		return mainactor;
	}
	    public StringProperty directorProperty() {
			return director;
		}
	    
	    public DoubleProperty priceProperty() {
			return price;
		}
	    public IntegerProperty durationProperty() {
			return duration;
		}



	    
	    
		/**
		 * 
		 */
		public MovieProperty()
		{
			super();
			// TODO Auto-generated constructor stub
		}




/**
		 * @param movieid
		 * @param movieName
		 * @param mainactor
		 * @param director
		 * @param price
		 * @param duration
		 * @param movie
		 */
		public MovieProperty(
				IntegerProperty movieid, StringProperty movieName, StringProperty mainactor, StringProperty director,
				DoubleProperty price, IntegerProperty duration, Movie movie
		)
		{
			super();
			this.movieid = movieid;
			this.movieName = movieName;
			this.mainactor = mainactor;
			this.director = director;
			this.price = price;
			this.duration = duration;
			this.movie = movie;
		}




		//set和get方法
public IntegerProperty getMovieid()
{
	return movieid;
}




public void setMovieid(IntegerProperty movieid)
{
	this.movieid = movieid;
}




public StringProperty getMovieName()
{
	return movieName;
}




public void setMovieName(StringProperty movieName)
{
	this.movieName = movieName;
}




public StringProperty getMainactor()
{
	return mainactor;
}




public void setMainactor(StringProperty mainactor)
{
	this.mainactor = mainactor;
}




public StringProperty getDirector()
{
	return director;
}




public void setDirector(StringProperty director)
{
	this.director = director;
}




public DoubleProperty getPrice()
{
	return price;
}




public void setPrice(DoubleProperty price)
{
	this.price = price;
}




public IntegerProperty getDuration()
{
	return duration;
}




public void setDuration(IntegerProperty duration)
{
	this.duration = duration;
}




public Movie getMovie()
{
	return movie;
}




public void setMovie(Movie movie)
{
	this.movie = movie;
}







								
								
}
