package com.lzh.cinema.bean;
/**
 *  要有set和get方法，以及无参的构造器->返回值默认是该类的实例、
 *  结果是字符串
 *  操作信息的转接
 * @author 林泽鸿
 *
 */
public class Msg {

	private String result;//返回的值
	
	private Object message;//封装成对象

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	//无参的构造器
	public Msg() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 接收信息的构造器，接收一个字符串
	 * @param result 将结果放在一个新生成的对象中
	 * @param message 状态，结果为真，则返回一个对象，否则返回一个null
	 */
	public Msg(String result, Object message) {
		super();
		this.result = result;
		this.message = message;
		System.out.println(result);
		System.out.println(message);
	}

	@Override
	public String toString() {
		
		return "Msg [result=" + result + ", message=" + message + "]";
	}

	
	
	
}
