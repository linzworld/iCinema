package com.lzh.cinema.view;

import java.net.URL;
import java.util.ResourceBundle;

import com.lzh.cinema.bean.MoneyMsg;
import com.lzh.cinema.bean.Msg;
import com.lzh.cinema.controller.BuyController;
import com.lzh.cinema.dao.BuyDao;
import com.lzh.cinema.entity.User;
import com.lzh.cinema.util.MoneyT;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
/**
 * 控制器
 * 购票界面
 * @author 林泽鸿
 *
 */
public class PayController implements Initializable {
/**
 * 类成员变量，用来记录座位的坐标
 */
	public static int x=0,y=0;
	  @FXML private Button confirm;
	  @FXML private Button backMoney;
	  @FXML private Main application;
	  @FXML private Text result;
	  @FXML private Text money;//显示余额
	  
    //座位按钮
    @FXML private Button s1;
    @FXML private Button s2;
    @FXML private Button s3;
    @FXML private Button s4;
    @FXML private Button s5;
    @FXML private Button s6;
    @FXML private Button s7;
    @FXML private Button s8; 
    @FXML private Button s9;
    @FXML private Button s10;
    @FXML private Button s11;
    @FXML private Button s12;
    @FXML private Button s13;
    @FXML private Button s14;
    @FXML private Button s15;
    @FXML private Button s16;
    @FXML private Button s17;
    @FXML private Button s18;
    @FXML private Button s19;
    @FXML private Button s20; 
    
	/**
	 * 运行这个整个的login类    
	 * @param application
	 */
	    public void setApp(Main application){
	        this.application = application;
	        System.out.println("------------------------");
	    }
	    /**
	     * 确定购买
	     * @param event
	     */
	    @FXML private void onConfim(ActionEvent event) {
	    	 
		       System.out.println("x="+x);
		       System.out.println("y="+y);
		
		       System.out.println("确定购买"); 
		       if(x==0||y==0)
		    	   {System.out.println("未选座");}
		       else//已选座
		          {System.out.println("已选座");}
		    
		       BuyController bct = new BuyController();
		       Msg msg = new Msg();
		       System.out.println("未到达judge");
		       msg = bct.Judge();
		     //返回结果的判断，以及对返回结果进行的不同做法
		       
		   if(msg.getResult().equals("余额不足"))
				  {result.setText("余额不足，请充值！");}
		  if(msg.getResult().equals("购买成功")) 
		  				{result.setText("购买成功"+"--第"+PayController.y+"行  第"+PayController.x+"列");} 
		  else if(msg.getResult().equals("该票已被预定")) 
		  				{result.setText("该座位已被预定");} 
		 
		  	money.setText(MoneyT.t());//显示余额
		       }
	    /**
	     * 返回查询界面
	     * @param event
	     */
	    @FXML private void onBackQuery(ActionEvent event) {
		       System.out.println("返回个人信息界面"); 
		       application.gotoUQuery(); 
		       }
	    /**
	     * 去到个人信息，余额的充值
	     * @param event
	     */
	    @FXML private void onBackMoney(ActionEvent event) {
	    	application.gotoPersonal(); 
	    }
	    
	/**
     *该方法在这个类中会自动被调用
     *加载资源的功能
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    /**
     * 过于简单粗暴的按钮
     * @param event
     */
    @FXML private void ons1(ActionEvent event) {PayController.x=1;PayController.y=1;}
    @FXML private void ons2(ActionEvent event) {PayController.x=2;PayController.y=1;}
    @FXML private void ons3(ActionEvent event) {PayController.x=3;PayController.y=1;}
    @FXML private void ons4(ActionEvent event) {PayController.x=4;PayController.y=1;}
    @FXML private void ons5(ActionEvent event) {PayController.x=5;PayController.y=1;}
    @FXML private void ons6(ActionEvent event) {PayController.x=1;PayController.y=2;}
    @FXML private void ons7(ActionEvent event) {PayController.x=2;PayController.y=2;}
    @FXML private void ons8(ActionEvent event) {PayController.x=3;PayController.y=2;}
    @FXML private void ons9(ActionEvent event) {PayController.x=4;PayController.y=2;}
    @FXML private void ons10(ActionEvent event) {PayController.x=5;PayController.y=2;}
    @FXML private void ons11(ActionEvent event) {PayController.x=1;PayController.y=3;}
    @FXML private void ons12(ActionEvent event) {PayController.x=2;PayController.y=3;}
    @FXML private void ons13(ActionEvent event) {PayController.x=3;PayController.y=3;}
    @FXML private void ons14(ActionEvent event) {PayController.x=4;PayController.y=3;} 
    @FXML private void ons15(ActionEvent event) {PayController.x=5;PayController.y=3;}
    @FXML private void ons16(ActionEvent event) {PayController.x=1;PayController.y=4;}
    @FXML private void ons17(ActionEvent event) {PayController.x=2;PayController.y=4;}
    @FXML private void ons18(ActionEvent event) {PayController.x=3;PayController.y=4;}
    @FXML private void ons19(ActionEvent event) {PayController.x=4;PayController.y=4;}
    @FXML private void ons20(ActionEvent event) {PayController.x=5;PayController.y=4;}

    
    
}

