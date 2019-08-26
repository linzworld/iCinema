package com.lzh.cinema.entity;
/**
 * 记录买票查询过程中得到的数据
 * ticket表加上座位表--->(x,y)
 * @author 林泽鸿
 *
 */
public class Ticket {
	private int ticket;//票的单号
	
	private int seat;//座位的id
	
	private int schedule;  //排片的id

    private double price;//票价 
	
	private int status;//票的状态

    private int x;  //座位的横坐标
	
	private int y;  //座位的纵坐标

	private int hall;//播放厅的id

	/**
	 * @param ticket
	 * @param seat
	 * @param schedule
	 * @param price
	 * @param status
	 * @param x
	 * @param y
	 * @param hall
	 */
	public Ticket(int ticket, int seat, int schedule, double price, int status, int x, int y, int hall)
	{
		super();
		this.ticket = ticket;
		this.seat = seat;
		this.schedule = schedule;
		this.price = price;
		this.status = status;
		this.x = x;
		this.y = y;
		this.hall = hall;
	}

	/**
	 * 
	 */
	public Ticket()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString()
	{
		return "Ticket [ticket=" + ticket + ", seat=" + seat + ", schedule=" + schedule + ", price=" + price
				+ ", status=" + status + ", x=" + x + ", y=" + y + ", hall=" + hall + "]";
	}

	public int getTicket()
	{
		return ticket;
	}

	public void setTicket(int ticket)
	{
		this.ticket = ticket;
	}

	public int getSeat()
	{
		return seat;
	}

	public void setSeat(int seat)
	{
		this.seat = seat;
	}

	public int getSchedule()
	{
		return schedule;
	}

	public void setSchedule(int schedule)
	{
		this.schedule = schedule;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
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

	public int getHall()
	{
		return hall;
	}

	public void setHall(int hall)
	{
		this.hall = hall;
	}

}