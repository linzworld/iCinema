package com.lzh.cinema.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.lzh.cinema.bean.Msg;
import com.lzh.cinema.controller.BuyController;
import com.lzh.cinema.controller.MovieListController;
import com.lzh.cinema.entity.MyMovie;
import com.lzh.cinema.entity.UserQueryMovie;
import com.lzh.cinema.property.MyMovieProperty;
import com.lzh.cinema.property.UserQueryMovieProperty;
import com.lzh.cinema.service.TicketService;
import com.lzh.cinema.util.MoneyT;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * 用户查询电影的界面控制器
 *
 * @author 林泽鸿
 */
public class MyTicketController implements Initializable
{
	private Main application;
	@FXML
	private Pagination pn_pagination;// 分页
	@FXML
	private TableView<MyMovieProperty> tv_persontable;// 表格
	@FXML
	private TableColumn<MyMovieProperty, String> dateCol;// 每一列元素
	@FXML
	private TableColumn<MyMovieProperty, Integer> sessionCol;
	@FXML
	private TableColumn<MyMovieProperty, Integer> hallCol;
	@FXML
	private TableColumn<MyMovieProperty, String> movieNameCol;
	@FXML
	private TableColumn<MyMovieProperty, Integer> scheduleCol;
	@FXML
	private TableColumn<MyMovieProperty, Integer> seatCol;
	@FXML
	private TableColumn<MyMovieProperty, Integer> xCol;
	@FXML
	private TableColumn<MyMovieProperty, Integer> yCol;
	@FXML
	private TextField UQueryCt; // 装有查询电影名内容的文本框
	@FXML
	private Button UQueryM; // 装有查询按钮
	@FXML
	private Text result;//退票成功的提示信息
	@FXML 
	private Text money;//显示余额
	  

	public static int a=1;//用来装点击那一行后的单号

	// 可视化表格数据的初始化,装表格中各个列的数据
	ObservableList<MyMovieProperty> list1 = FXCollections.observableArrayList();

	/**
	 * 每个窗口都有的方法，与Main函数相连
	 * 
	 * @param application
	 */
	public void setApp(Main application)
	{

		this.application = application;
	}

	/**************************/
	// 各种按钮
	/**
	 * 按钮 返回用户大厅界面
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
	 * 按钮 查询电影
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
		 .addListener(new ChangeListener<MyMovieProperty>() {

					public void changed(ObservableValue<? extends MyMovieProperty> observable, 
							MyMovieProperty oldValue, MyMovieProperty newValue) {
						//重要 ！！绑定属性转化为int,后面加intValue
				
						MyTicketController.a =newValue.getSeat().intValue();//装所选的行数seat_id
					System.out.println("a的2值为"+a);
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
		  
		  TicketService ts = new TicketService();
		  
		  List<MyMovie> listbt = new ArrayList<MyMovie>();
		  
		  listbt = ts.UserQureybt(uQueryCt);
		  

		  for (int i = 0; i <=listbt.size()-1; i++) 
		  {
			  MyMovie mm = null; 
			  mm = listbt.get(i);
			/* System.out.println( list1.size()+"--------list1最开始的长度"); */
		  // 最重要的一步，将数据库对应的实体类转化为表格专用的属性 
		  MyMovieProperty mmp = new MyMovieProperty(mm);
			/* System.out.println("开始添加"); */
		  list1.add(mmp);
			/* System.out.println( list1.size()+"-------list1之后的长度"); */
		  }
		  tv_persontable.setItems(list1);
	}

	/**
	 * 按钮 购买影票
	 * 
	 * @param event
	 */
	 @FXML private void onCancel(ActionEvent event) {
   	 System.out.println("------------------");
	       System.out.println("确定退票"); 
	    
	      MovieListController mlc = new MovieListController();
	       Msg msg = new Msg();
	       
	       System.out.println("refund前");
	       msg = mlc.Refund();
	     //返回结果的判断，以及对返回结果进行的不同做法
	       
	   if(msg.getResult().equals("退票成功"))
			  {
		   			result.setText("退票成功，已将票价退还到余额");
			  }
	 
	  	money.setText(MoneyT.t());//显示余额
	       }

	/**
	 * 初始化表格数据
	 * 
	 * @param pagesize
	 */
	private void showList(Integer pagesize)
	{
		// 给表格初始化数据的地方

		ObservableList<MyMovieProperty> list1 = FXCollections.observableArrayList();// 可视化表格数据的初始化
		// 如果数据库中有数据,就可以直接从数据库中调出数据,不需要新建一些数据

		// 转化前
		// 从数据库中拿到的数据，原始数据
		List<MyMovie> list = new ArrayList<MyMovie>();
		// 传到控制层
		MovieListController mlc = new MovieListController();
		// 得到全部查询结果

		list = mlc.MMovie();

		MyMovie mm = null;
		for (int i = 0; i < list.size(); i++)
		{
			mm = list.get(i);

			// 最重要的一步，将数据库对应的实体类转化为表格专用的属性
			MyMovieProperty mmp = new MyMovieProperty(mm);
			
			list1.add(mmp);

		}
		tv_persontable.setItems(list1);// 将集合中的所有元素防在列表中
		/**
		 * 添加监听事件，获得选中行的全部数据
		 */
		tv_persontable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<MyMovieProperty>()
		{
			public void changed(
					ObservableValue<? extends MyMovieProperty> observable, MyMovieProperty oldValue,
					MyMovieProperty newValue)
			{
				/**
				 * 重要 ！！绑定属性转化为int,后面加intValue, 选择的seat，唯一确定一场特定的电影
				 */
				MyTicketController.a = 0;
				
				MyTicketController.a =newValue.getSeat().intValue();
				System.out.println("我选择的座位id---"+a);
		}
		});
	}

	/**
	 * 在fxml文件完成载入时自动被调用 初始化控制器类 一定执行
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		// 每一列单元格相对应的数据
		dateCol.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
		sessionCol.setCellValueFactory(cellData -> cellData.getValue().sessionProperty().asObject());
		hallCol.setCellValueFactory(cellData -> cellData.getValue().hallProperty().asObject());// integer特殊化处理
		movieNameCol.setCellValueFactory(cellData -> cellData.getValue().movieNameProperty());
		scheduleCol.setCellValueFactory(cellData -> cellData.getValue().scheduleProperty().asObject());

		seatCol.setCellValueFactory(cellData -> cellData.getValue().seatProperty().asObject());
		xCol.setCellValueFactory(cellData -> cellData.getValue().xProperty().asObject());
		yCol.setCellValueFactory(cellData -> cellData.getValue().yProperty().asObject());


		// 分页查询的操作
		pn_pagination.setPageFactory(new Callback<Integer, Node>()
		{
			@Override
			public Node call(Integer param)
			{
				// 调用了初始化表格界面的方法，将查询到的list放进表格中
				showList(param);
				return tv_persontable;
			}
		});
	}
}
