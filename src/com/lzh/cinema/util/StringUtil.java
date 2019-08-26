package com.lzh.cinema.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * String与Date之间的转化
 * @author 林泽鸿
 *
 */
public class StringUtil {
	
	/**
	 * 日期转字符串
	 * @param date
	 * @param formate
	 * @return
	 */
	public static String dateToString(Date date)
	{
		String result="";
		SimpleDateFormat sdf=new SimpleDateFormat("yy-MM-dd");
		if(date!=null)
		{
			result=sdf.format(date);
		}
		return result;
	}
	/**
	 * 字符串转日期
	 * @param date
	 * @param formate
	 * @return
	 */
	public static Date stringToDate(String date,String formate)
	{
		Date result=null;
		if(date==null&&"".equals(date))
		{
			return null;
		}
		else{
			DateFormat sdf=new SimpleDateFormat(formate);
			try{
				result=sdf.parse(date);
			}catch(ParseException e)
			{
				e.printStackTrace();
			}	
		}
		return result;
	}
}