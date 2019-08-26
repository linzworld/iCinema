/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lzh.cinema.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.lzh.cinema.controller.MovieListController;
import com.lzh.cinema.entity.UserQueryMovie;
import com.lzh.cinema.property.UserQueryMovieProperty;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

/**
 * 用户查询电影的界面控制器
 *
 * @author 林泽鸿
 */
public class UserQueryMovieController implements Initializable
{
	private Main application;
	@FXML
	private Pagination pn_pagination;// 分页
	@FXML
	private TableView<UserQueryMovieProperty> tv_persontable;// 表格
	@FXML
	private TableColumn<UserQueryMovieProperty, String> dateCol;// 每一列元素
	@FXML
	private TableColumn<UserQueryMovieProperty, Integer> sessionCol;
	@FXML
	private TableColumn<UserQueryMovieProperty, Integer> hallCol;
	@FXML
	private TableColumn<UserQueryMovieProperty, String> movieNameCol;
	@FXML
	private TableColumn<UserQueryMovieProperty, Integer> scheduleCol;
	@FXML
	private TextField UQueryCt; // 装有查询电影名内容的文本框
	
	public static int a=1;//用来装点击那一行后的单号

	
	// 可视化表格数据的初始化,装表格中各个列的数据
	ObservableList<UserQueryMovieProperty> list1 = FXCollections.observableArrayList();
	// 每个窗口都有的方法，与Main函数相连


	public void setApp(Main application)
	{

		this.application = application;
	}

	/**************************/
	 // 各种按钮
	/**
	 * 按钮
	 * 返回用户大厅界面
	 * 
	 * @param event
	 */
	@FXML
	private void onBackUser(ActionEvent event)
	{ 
		System.out.println("返回主菜单");
		application.gotohall();
	}

	/**
	 * 按钮
	 * 查询电影
	 * 
	 * @param event
	 */
	@FXML
	private void onUQueryM(ActionEvent event)
	{
		System.out.println("查询电影");

		/**
		 * 添加监听事件，获得选中行数据修改的id值
		 */
		tv_persontable.getSelectionModel().selectedItemProperty()
		 .addListener(new ChangeListener<UserQueryMovieProperty>() {

					public void changed(ObservableValue<? extends UserQueryMovieProperty> observable, 
							UserQueryMovieProperty oldValue, UserQueryMovieProperty newValue) {
						//重要 ！！绑定属性转化为int,后面加intValue
						UserQueryMovieController.a =newValue.getSchedule().intValue();
					
					}
				});
		
		//将原本存储的对象清空，装得到的东西
		/* System.out.println( "循环之前---------"+list1.size()); */
		   for(int i=list1.size()-1;i>=0;i--){
	            list1.remove(i);
			/*
			 * System.out.println("-------------"); System.out.println("循环---------"+i);
			 */	        }
		/* System.out.println( "循环之后------------+"+list1.size()); */
		  // 如果数据库中有数据,就可以直接从数据库中调出数据,不需要新建一些数据
		  
		  String uQueryCt = UQueryCt.getText();
		  
		  
		  // 装查询到的电影对象 List<UserQueryMovie> listbt = new ArrayList<UserQueryMovie>();
		  
		  MovieListController mlc = new MovieListController();
		  
		  List<UserQueryMovie> listbt = new ArrayList<UserQueryMovie>();
		  
		  listbt = mlc.UserQureybt(uQueryCt);
		  
		  UserQueryMovie uqm = null; 
		  
		  System.out.println( listbt.size());
		  for (int i = 0; i <=listbt.size()-1; i++) 
		  {
			  uqm = listbt.get(i);
			/* System.out.println( list1.size()+"--------list1最开始的长度"); */
		  // 最重要的一步，将数据库对应的实体类转化为表格专用的属性 
		  UserQueryMovieProperty uqmp = new UserQueryMovieProperty(uqm);
			/* System.out.println("开始添加"); */
		  list1.add(uqmp);
			/* System.out.println( list1.size()+"-------list1之后的长度"); */
		  }
		  tv_persontable.setItems(list1);
	}

	/**
	 * 按钮
	 * 购买影票
	 * 
	 * @param event
	 */
	@FXML
	private void onBuy(ActionEvent event)//调用其他的选择的方法，进行判断
	{
		System.out.println("返回主菜单");
		application.gotoBuy();
	}

	/**
	 * 初始化表格数据
	 * 
	 * @param pagesize
	 */
	private void showList(Integer pagesize)
	{
		// 给表格初始化数据的地方

		ObservableList<UserQueryMovieProperty> list1 = FXCollections.observableArrayList();// 可视化表格数据的初始化
		// 如果数据库中有数据,就可以直接从数据库中调出数据,不需要新建一些数据

		// 转化前
		// 从数据库中拿到的数据，原始数据
		List<UserQueryMovie> list = new ArrayList<UserQueryMovie>();
		// 传到控制层
		MovieListController mlc = new MovieListController();
		// 得到全部查询结果
		list = mlc.UserQurey();
		UserQueryMovie uqm = null;
		for (int i = 0; i < list.size(); i++)
		{
			uqm = list.get(i);

			// 最重要的一步，将数据库对应的实体类转化为表格专用的属性
			UserQueryMovieProperty uqmp = new UserQueryMovieProperty(uqm);

			list1.add(uqmp);

		}
		tv_persontable.setItems(list1);// 将集合中的所有元素防在列表中
		/**
		 * 添加监听事件，获得选中行的全部数据
		 */
		tv_persontable.getSelectionModel().selectedItemProperty()
		 .addListener(new ChangeListener<UserQueryMovieProperty>() {
					public void changed(ObservableValue<? extends UserQueryMovieProperty> observable, 
							UserQueryMovieProperty oldValue, UserQueryMovieProperty newValue) {					
						/**
						 * 重要 ！！绑定属性转化为int,后面加intValue,
						 *选择的排片值，即为单号，唯一确定一场特定的电影
						 */
						UserQueryMovieController.a =newValue.getSchedule().intValue();
						System.out.println(a);
					}
				});
	}
	/**
	 * 在fxml文件完成载入时自动被调用 初始化控制器类 
	 * 一定执行
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		// TODO
		dateCol.setCellValueFactory(cellData -> cellData.getValue().dateProperty());// 每一列单元格相对应的数据
		sessionCol.setCellValueFactory(cellData -> cellData.getValue().sessionProperty().asObject());
		hallCol.setCellValueFactory(cellData -> cellData.getValue().hallProperty().asObject());// integer特殊化处理
		movieNameCol.setCellValueFactory(cellData -> cellData.getValue().movieNameProperty());
		scheduleCol.setCellValueFactory(cellData -> cellData.getValue().scheduleProperty().asObject());
		// 分页查询的操作
		pn_pagination.setPageFactory(new Callback<Integer, Node>()
		{
			@Override
			public Node call(Integer param)
			{
				showList(param);
				return tv_persontable;
			}
		});
	}
}
