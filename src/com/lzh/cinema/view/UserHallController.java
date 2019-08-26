package com.lzh.cinema.view;

import java.net.URL;
import java.util.ResourceBundle;
import com.lzh.cinema.entity.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *用户大厅界面的控制器
 * @author 林泽鸿
 */
public class UserHallController implements Initializable
{
	private Main application;

	// 右边填写的是fx:id变量名

	@FXML
	private TableView<Movie> movieList; // 列元素的标题

	@FXML
	private TableColumn<Movie, Integer> hallCol, sessionCol;

	@FXML
	private TableColumn<Movie, String> movieNameCol;
 //每个窗口都有的方法，与Main函数相连
	public void setApp(Main application)
      { this.application =application; }
	/**
	 * 返回登录界面的按钮
	 * @param event
	 */
	  @FXML private void onBackLogin(ActionEvent event) {
	       System.out.println("退出登录"); 
	       application.useroutmain(); 
	       }
	  /***
	   * 键盘操作 esc返回登录界面
	   * @param event
	   */
	  @FXML private void onBackLoginByESC(KeyEvent event) {
		  if(event.getCode() == KeyCode.ESCAPE) {
	       System.out.println("退出登录"); 
	       application.useroutmain(); }
	       }
	  
	  /**
	   * 用户查询电影信息
	   * @param event
	   */
	  @FXML private void OnUQueryMovie(ActionEvent event) {
		  
	       System.out.println("用户名---"+LoginController.userName); 
	       application.gotoUQuery();
	       }
	  
	  /**
	   * 个人信息界面的跳转
	   * @param event
	   */
	  @FXML private void onPersonal(ActionEvent event) {
	       System.out.println("退出登录"); 
	       application.gotoPersonal(); 
	       }
	  

	  
	  /**
	   * 用户购买影票
	   * @param event
	   */
	  @FXML private void onBuy(ActionEvent event) {
	       System.out.println("退出登录"); 
		/* application.useroutmain(); */
	       }
	  
	  
	  /**
	   * 用户查询自己的影票信息
	   * @param event
	   */
	  @FXML private void onMyTicket(ActionEvent event) {
	     System.out.println("查看我的影票");
	       application.gotoMT(); 
	       }
    
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
	}

}
