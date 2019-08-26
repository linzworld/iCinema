package com.lzh.cinema.view;


import java.net.URL;
import java.util.ResourceBundle;

import com.lzh.cinema.bean.Msg;
import com.lzh.cinema.controller.UserController;
import com.lzh.cinema.util.ValidateUtil;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
 
/**
 * 登录界面的界面控制器
 * @author 林泽鸿
 */
public class LoginController implements Initializable {
@FXML private TextField account;
@FXML private PasswordField passwordField;
@FXML private Main application;
@FXML private Text actiontarget;
@FXML private CheckBox admin;
@FXML private Button loginbt;
public static String userName;
public static String password;

/**
 * 运行这个整个的login类    
 * @param application
 */
    public void setApp(Main application){

        this.application = application;
    }

/**
 * 登录按钮的事件处理
 * @param event
 */
     public void onLogin(ActionEvent event) {
    	 LoginController.userName =account.getText();
    	 LoginController.password =passwordField.getText();
 		if(ValidateUtil.isInvalidUserName(userName)) 
 		{actiontarget.setText("用户名为空，请输入用户名");}
        else if(ValidateUtil.isInvalidPassword(password))
        {actiontarget.setText("密码为空，请输入密码");}
        UserController utc = new UserController();
        Msg msg=utc.login(userName,password );
        if(msg.getResult().equals("用户登录成功")) 
        { application.gotohall();}
       else if(msg.getResult().equals("管理员登录成功"))
        {actiontarget.setText("管理员登录");
        application.gotoAdmin();}//管理员的界面跳转
       else if(msg.getResult().equals("密码错误"))
       {actiontarget.setText("密码错误");}
		/* else {actiontarget.setText("登录失败");} */
      }
     /**
      * enter键，键盘输入之后直接跳转到用户大厅的界面
      */
     public void onLoginEnter(KeyEvent  event) {
    	 if (event.getCode() == KeyCode.ENTER)
    	 {
    		 LoginController.userName =account.getText();
        	 LoginController.password =passwordField.getText();
 		if(ValidateUtil.isInvalidUserName(userName)) 
 		{actiontarget.setText("用户名为空，请输入用户名");}
        else if(ValidateUtil.isInvalidPassword(password))
        {actiontarget.setText("密码为空，请输入密码");}
         UserController utc = new UserController();
         Msg msg=utc.login(userName,password );//得到结果的消息对象
         if(msg.getResult().equals("用户登录成功")) 
         { application.gotohall();}
        else if(msg.getResult().equals("管理员登录成功"))
         {actiontarget.setText("管理员登录");
         application.gotoAdmin();}//管理员的界面跳转
       if(msg.getResult().equals("密码错误"))
        {System.out.println(123124);
        	actiontarget.setText("密码错误");}
    	 }
     }
     
   /**
    * 注册按钮的事件处理
    * @param event
    */
    @FXML
    private void onRegister(ActionEvent event) {
    	
    	//System.out.println("注册");
    	LoginController.userName =account.getText();
   	 LoginController.password =passwordField.getText();
 		
         actiontarget.setText("已按下注册键");
         UserController utc = new UserController();
         Msg msg= utc.register(userName,password );
         //返回结果的判断，以及对返回结果进行的不同做法
	         if(msg.getResult().equals("用户名为空"))
	        		 {actiontarget.setText("用户名为空");}
	         else if(msg.getResult().equals("密码为空")) 
	         			{actiontarget.setText("密码为空");}
	         else if(msg.getResult().equals("注册成功")) 
	         			{actiontarget.setText("已注册成功，请登录");}
	         else if(msg.getResult().equals("用户名重复")) 
	         			{actiontarget.setText("用户名重复，请重新注册");}
    }
    
    /**
     * 复选框的外部类，其按钮的监听事件，判断其是否已进行选择的操作
     * @param event
     */
    @FXML
    private void onAdmin(ActionEvent event) {
    	System.out.println("复选框");
        /**
         * admin复选框的使用,已选择返回true,为管理员，匿名内部类，即用即扔
         */
         admin.selectedProperty().addListener(new ChangeListener<Boolean>() {
        public void changed(ObservableValue<? extends Boolean> ov,Boolean old_val, Boolean new_val) 
        {
          System.out.println(admin.isSelected());
       }
     });
    }

    
    /**
     *该方法在这个类中会自动被调用
     *加载资源的功能
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
