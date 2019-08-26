package com.lzh.cinema.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.lzh.cinema.controller.MovieListController;
import com.lzh.cinema.entity.Movie;
import com.lzh.cinema.entity.UserQueryMovie;
import com.lzh.cinema.property.MovieProperty;
import com.lzh.cinema.property.UserQueryMovieProperty;

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
public class AdminHallController implements Initializable
{
	private Main application;
	@FXML
	private Pagination pn_pagination;// 分页
	@FXML
	private TableView<MovieProperty> tv_persontable;// 表格
	@FXML
	private TableColumn<MovieProperty, Integer> movieidCol;
	@FXML
	private TableColumn<MovieProperty, String> movieNameCol;// 每一列元素

	@FXML
	private TableColumn<MovieProperty, String> mainactorCol;

	@FXML
	private TableColumn<MovieProperty, String> directorCol;
	
	@FXML
	private TableColumn<MovieProperty, Double> priceCol;
	@FXML
	private TableColumn<MovieProperty, Integer> durationCol;
	
	@FXML
	private TextField AQueryMCt; // 装有查询电影名内容的文本框
	@FXML
	private Button AQueryM; // 查询电影名的按钮
	/**
	 * 更改电影信息的对象中的属性，由文本框填入-----6个变量
	 */
	@FXML
	private TextField cmovieid; // 装有更改电影名内容的文本框
	@FXML
	private TextField  cmoviename;
	@FXML
	private TextField   cmainactor;
	@FXML
	private TextField  cdirector ;
	@FXML
	private TextField  cprice  ;
	@FXML
	private TextField cduration  ;
	/**
	 * 添加电影信息的属性，由文本框填入-----6个变量
	 */
	
	@FXML
	private TextField  amoviename;// 装有增加的电影内容的文本框
	@FXML
	private TextField   amainactor;
	@FXML
	private TextField  adirector ;
	@FXML
	private TextField  aprice  ;
	@FXML
	private TextField aduration  ;
	
	
	
	public static int a=1;//用来装点击那一行后的单号
	

	 
	ObservableList<MovieProperty> list1 = FXCollections.observableArrayList();// 可视化表格数据的初始化,装表格中各个列的数据
	// 每个窗口都有的方法，与Main函数相连


	public void setApp(Main application)
	{

		this.application = application;
	}

	/**************************/
	 // 各种按钮
	/**
	 * 按钮
	 * 返回用户注册的界面
	 * 
	 * @param event
	 */
	  @FXML private void onBackLogin(ActionEvent event) {
	       System.out.println("退出登录"); 
	       application.useroutmain(); 
	       }

	/**
	 * 按钮
	 * 查询电影
	 * 
	 * @param event
	 */
	@FXML
	private void onAQueryM(ActionEvent event)
	{
		System.out.println("查询电影");

		/**
		 * 添加监听事件，获得选中行数据修改的id值
		 */
		tv_persontable.getSelectionModel().selectedItemProperty()
		 .addListener(new ChangeListener<MovieProperty>() {

					public void changed(ObservableValue<? extends MovieProperty> observable, 
							MovieProperty oldValue, MovieProperty newValue) {
						//重要 ！！绑定属性转化为int,后面加intValue
						AdminHallController.a =newValue.getMovieid().intValue();
					
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
		  
		  String aQueryMCt = AQueryMCt.getText();
		  
		  
		  // 装查询到的电影对象 List<UserQueryMovie> listbt = new ArrayList<UserQueryMovie>();
		  
		  MovieListController mlc = new MovieListController();
		  
		  List<Movie> listbt = new ArrayList<Movie>();
		  
		  listbt = mlc.AQureybt(aQueryMCt);
		  
		  Movie m = null; 
		  
		  System.out.println( listbt.size());
		  
		  for (int i = 0; i <=listbt.size()-1; i++) 
		  {
			  m = listbt.get(i);
			/* System.out.println( list1.size()+"--------list1最开始的长度"); */
		  // 最重要的一步，将数据库对应的实体类转化为表格专用的属性 
			  MovieProperty mp = new MovieProperty(m);
			/* System.out.println("开始添加"); */
			  list1.add(mp);
				/* System.out.println( list1.size()+"-------list1之后的长度"); */
			  }
			  tv_persontable.setItems(list1);
	}
	

	/**
	 * 按钮
	 * 删除电影
	 * 
	 * @param event
	 */
	@FXML
	private void onDelete(ActionEvent event)//调用其他的选择的方法，进行判断
	{
		MovieListController mlc = new MovieListController();
		int n = 0;
		n = mlc.DeleteM();
		System.out.println("删除电影---"+n);
		
		}
	/**
	 * 更改电影信息
	 * @param event
	 */
		@FXML
		private void onAlter(ActionEvent event)//调用其他的选择的方法，进行判断
		{
			Movie m = null;
			m = new Movie();
			
			//文本框信息
		
			String moviename = cmoviename.getText();
			String mainactor = cmainactor.getText();
			String diretor = cdirector.getText();
			
			String mid = cmovieid.getText();
			int movieid = Integer.parseInt(mid);//电影单号movieid
			String dur = cmovieid.getText();
			int duration = Integer.parseInt(dur);//电影时长duration
			String mt = cmovieid.getText();
			double price = Double.parseDouble(mt);//电影价钱
			
			m.setMovieid(movieid);
			m.setMovieName(moviename);
			m.setMainActor(mainactor);
			m.setDiretor(diretor);
			m.setPrice(price);
				m.setDuration(duration);
			
			MovieListController mlc = new MovieListController();
			int n = 0;
			n = mlc.AlterM(m);
			System.out.println("更改电影---"+n);
			
		}
		/**
		 * 增加电影信息
		 * @param event
		 */
			@FXML
			private void onAdd(ActionEvent event)//调用其他的选择的方法，进行判断
			{
				Movie mo = null;
				mo = new Movie();
				System.out.println("onadd");
				//文本框信息
			
				String aamoviename = amoviename.getText();
				String aamainactor = amainactor.getText();
				String aadiretor = adirector.getText();
				
			
				String dur =aduration.getText();
				int aaduration = Integer.parseInt(dur);//电影时长duration
				String mt = aprice.getText();
				double aaprice = Double.parseDouble(mt);//电影价钱
				 System.out.println("");
				mo.setMovieName(aamoviename);
				mo.setMainActor(aamainactor);
				mo.setDiretor(aadiretor);
				mo.setPrice(aaprice);
					mo.setDuration(aaduration);
				
				MovieListController mlc = new MovieListController();
				int n = 0;
				n = mlc.AddM(mo);
				System.out.println("增加电影---"+n);
				
			}

	/**
	 * 初始化表格数据
	 * 
	 * @param pagesize
	 */
	private void showList(Integer pagesize)
	{
		// 给表格初始化数据的地方

		ObservableList<MovieProperty> list1 = FXCollections.observableArrayList();// 可视化表格数据的初始化
		// 如果数据库中有数据,就可以直接从数据库中调出数据,不需要新建一些数据

		// 转化前
		// 从数据库中拿到的数据，原始数据
		List<Movie> list = new ArrayList<Movie>();
		// 传到控制层
		MovieListController mlc = new MovieListController();
		// 得到全部查询结果
		list = mlc.QMovieA();
		Movie m = null;
		for (int i = 0; i < list.size(); i++)
		{
			m = list.get(i);

			// 最重要的一步，将数据库对应的实体类转化为表格专用的属性
			MovieProperty mp = new MovieProperty(m);

			list1.add(mp);

		}
		tv_persontable.setItems(list1);// 将集合中的所有元素防在列表中
		/**
		 * 添加监听事件，获得选中行的全部数据
		 */
		tv_persontable.getSelectionModel().selectedItemProperty()
		 .addListener(new ChangeListener<MovieProperty>() {
					public void changed(ObservableValue<? extends MovieProperty> observable, 
							MovieProperty oldValue, MovieProperty newValue) {					
						/**
						 * 重要 ！！绑定属性转化为int,后面加intValue,
						 *选择的排片值，即为单号，唯一确定一场特定的电影
						 */
						AdminHallController.a =newValue.getMovieid().intValue();
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
		// TODO\
		movieidCol.setCellValueFactory(cellData -> cellData.getValue().movieidProperty().asObject());// 每一列单元格相对应的数据
		movieNameCol.setCellValueFactory(cellData -> cellData.getValue().movieNameProperty());
		
		mainactorCol.setCellValueFactory(cellData -> cellData.getValue().mainactorProperty());
		directorCol.setCellValueFactory(cellData -> cellData.getValue().directorProperty());
		
		priceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());// integer特殊化处理
		durationCol.setCellValueFactory(cellData -> cellData.getValue().durationProperty().asObject());
		
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

