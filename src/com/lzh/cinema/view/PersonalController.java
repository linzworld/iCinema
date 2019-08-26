package com.lzh.cinema.view;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import com.lzh.cinema.bean.MoneyMsg;
import com.lzh.cinema.bean.Msg;
import com.lzh.cinema.controller.BuyController;
import com.lzh.cinema.controller.UserController;
import com.lzh.cinema.entity.User;
import com.lzh.cinema.util.MoneyT;
import com.lzh.cinema.util.ValidateUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * 用户个人信息的查询界面的控制器
 * @author 林泽鸿
 *
 */
public class PersonalController implements Initializable {
	   @FXML private Main application;
	   @FXML private Button onBacktohall;
	   @FXML private Text uname;
	   @FXML private Text money;
	   @FXML private TextField addMoney;
	   @FXML private Button add;
		/**
		 * 运行这个整个的login类    
		 * @param application
		 */

		    public void setApp(Main application){
		        this.application = application;
		        uname.setText(LoginController.userName);
		        //将用户余额设置在文本框中
		        money.setText(MoneyT.t());
		    }

		    /*************************/
		    //按钮

			/**
			 * 按钮
			 * 返回用户大厅
			 * @param event
			 */
		    @FXML private void onBacktohall(ActionEvent event) {
			       application.gotohall();
			       }		     	    
		    /**
		     * 按钮
		     * 用户充值
		     * @param event
		     */
		    @FXML private void onAdd(ActionEvent event) {
		    	 String mt = addMoney.getText();
		    	double addm = Double.parseDouble(mt);//得到原来的金额
		    	System.out.println(addm);
		    	 BuyController bct = new BuyController();
		    	 User user = new User();
			        MoneyMsg mmsg= bct.UMoneyQ(user.getUserName(),user.getPassword() );	        
			        if(addm<0.0) {System.out.println("增加值不能为负值");}
			        else{
			       double m0 = mmsg.getMoney(); 
			       double m =0;
			       m = m0+addm;
		          MoneyMsg mmsg1=bct.AddMoney(m);//得到结果的消息对象
		    	String ma = String .valueOf(mmsg1.getMoney());
		    	
		    	money.setText(ma);
		    	}
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
