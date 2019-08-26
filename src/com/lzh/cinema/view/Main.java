package com.lzh.cinema.view;
 
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
 
/**
 *整个界面程序的入口，相当于各个界面的总负责人
 * @author 林泽鸿
 */
public class Main extends Application {
    private Stage stage;
    private final double MINIMUM_WINDOW_WIDTH = 1200.0;
    private final double MINIMUM_WINDOW_HEIGHT = 800.0;
    
    //程序从main函数跳过来这里
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle("影院购票管理系统");
        stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
        stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
        //限制窗口变大变小
        stage.setMaxWidth(MINIMUM_WINDOW_WIDTH);
        stage.setMaxWidth(MINIMUM_WINDOW_WIDTH);
        
        gotologin();
        stage.show();
    }
    /****************/
    //界面的跳转
    
    /**
     * 跳转到登录界面
     */
    public void gotologin(){
      try {
    	 
            LoginController login = (LoginController) replaceSceneContent("Login.fxml");

            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * 跳转到用户界面
     */
    public void gotohall(){
     try {
            UserHallController main = (UserHallController) replaceSceneContent("UserHall.fxml");
            System.out.println("来到用户大厅");
            main.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    
    /**
     * 跳转到用户查询电影界面
     */
    public void gotoUQuery(){
      try {
    	  UserQueryMovieController uqmc = (UserQueryMovieController) replaceSceneContent("UserQueryMovie.fxml");
    	  uqmc.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * 跳转到购票界面
     */
    public void gotoBuy(){
     try {
           PayController buy = (PayController) replaceSceneContent("Buy.fxml");
            System.out.println("购票界面");
            buy.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * 跳转到个人信息
     */
    public void gotoPersonal(){
        try {
        	PersonalController buy = (PersonalController) replaceSceneContent("Personal.fxml");
               System.out.println("个人信息界面");
               buy.setApp(this);
           } catch (Exception ex) {
               Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
    /**
     * 跳转到我的影票
     */
    public void gotoMT(){
        try {
        	MyTicketController mt = (MyTicketController) replaceSceneContent("MyTicket.fxml");
               mt.setApp(this);
           } catch (Exception ex) {
               Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
    public void gotoAdmin(){
        try {
      	 
        	AdminHallController AdminH = (AdminHallController) replaceSceneContent("AdminHall.fxml");
        	AdminH.setApp(this);
          } catch (Exception ex) {
              Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
    /************************************/
    //界面的跳转
    /**
     * 登录后跳转到另一界面
     * @param account
     * @param password
     */
    public void userlogin(String account,String password){
 
      gotohall();
    }

   /**
    * 返回登录窗口
    */
    public void useroutmain(){
      gotologin();
    }
    
    /**
     * 跳转到用户查询电影的界面功能
     */
	public void userMovie()
	 { gotoUQuery(); }
	 
    
    
    /************/
	//Main函数的基本功能
    /**
     * 初始化的窗口，元窗口
     * 实例化登录窗口，窗口切换器
     * 函数名：replaceSceneContent
     * @param fxml 要构造的窗口的fxml文件名
     * @return Initializable 
     * @throws Exception
     */
   private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
      AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        } 
        Scene scene = new Scene(page, 1200, 800);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }
   
   
/**
 * 主函数
 * @param args
 */
    public static void main(String[] args) {
        launch(args);
    }
    


}
