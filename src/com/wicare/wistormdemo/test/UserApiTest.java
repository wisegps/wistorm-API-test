/**
 * UserApiTest.java
 * c
 * TODO
 * 2015-12-11
 */
package com.wicare.wistormdemo.test;

import java.util.HashMap;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.test.ActivityInstrumentationTestCase2;
import com.baidu.android.common.logging.Log;
import com.wicare.wistorm.api.WCommApi;
import com.wicare.wistorm.api.WUserApi;
import com.wicare.wistorm.http.BaseVolley;
import com.wicare.wistorm.http.Msg;
import com.wicare.wistorm.model.Customer;
import com.wicare.wistormdemo.activity.LoginTest;

/**
 * UserApiTest
 * 
 * @author c TODO 2015-12-11
 */
public class UserApiTest extends ActivityInstrumentationTestCase2<LoginTest> {

	/**
	 * @param activityClass
	 */
	public WUserApi userApi;

	public WCommApi commApi;
	public Handler handler;

	public UserApiTest() {
		super(LoginTest.class);
		handler = new Handler(callback);

	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		userApi = new WUserApi(handler);

		commApi = new WCommApi(handler);
		BaseVolley.init(getActivity());
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * 测试获取token
	 */
	public void getTokenTest() {
		userApi.getToken("13316891158", "123456");
	}

	/**
	 * 测试登陆
	 */
	public void getLoginTest() {
		String account = "13316560478";
		String password = "123456";
		userApi.login(account, password);
	}

	/**
	 * 测试第三方登陆
	 */
	public void getThirdLoginTest() {
		String loginId = "";
		userApi.thridLogin(loginId);
	}

	/**
	 * 发送验证码短信
	 */
	public void sendSMS() {
		commApi.sendSMS("18566699543", WCommApi.Tpye_Nomal);
	}

	/**
	 * 验证效验码
	 */
	public void validCode() {
		userApi.volidCode("18566699543", "4486");
	}

	/**
	 * 注册
	 */
	public void register() {
		String account = "18566699543";
		String password = "123456";
		String validCode = "4486";
		userApi.register(account, password, validCode);
	}

	/**
	 * 创建用户
	 */
	public void createCustomer() {

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("access_token",
				"f1b3afaf9bbedfcb0ca3f0465a1d2e7e157c1ea55ad8d2dbcaa7083d125d360cc1792bdb116812d81a3e51f8d269cccc");
		params.put("mode", "2");
		params.put("seller_id", "1");
		params.put("cust_type", "2");
		params.put("mobile", "111");
		params.put("obj_name", "粤android");
		params.put("frame_no", "TTT");
		params.put("car_brand_id", "9");
		params.put("car_brand", "奥迪");
		params.put("car_series_id", "4385");
		params.put("car_series", "A3 e-tron");
		params.put("car_type_id", "113137");
		params.put("car_type", "2015 款 基本型");
		params.put("mileage", "1000");
		params.put("if_arrive", "0");
		params.put("business_type", "1");
		params.put("business_content", "业务内容Java测试");
		//
		userApi.create(params);

	}

	public void get() {
		String custId = "1233";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("access_token",
				"f1b3afaf9bbedfcb0ca3f0465a1d2e7e157c1ea55ad8d2dbcaa7083d125d360cc1792bdb116812d81a3e51f8d269cccc");
		params.put("cust_id", custId);
		userApi.get(params);
	}
	
	public void update(){
		HashMap<String, String> params = new HashMap<String, String>();
		String custId = "1233";
		params.put("_cust_id", custId);
		params.put("access_token",
				"f1b3afaf9bbedfcb0ca3f0465a1d2e7e157c1ea55ad8d2dbcaa7083d125d360cc1792bdb116812d81a3e51f8d269cccc");
		params.put("cust_name", "update_name");
		userApi.update(params);
	}
	
	

	Handler.Callback callback = new Handler.Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
			case Msg.M_Usr_Token:
				Bundle bundle = msg.getData();
				String access_token = bundle.getString("access_token");
				String valid_time = bundle.getString("valid_time");
				Log.i("LoginTest", "返回access_token值" + access_token);
				Log.i("LoginTest", "返回valid_time值" + valid_time);
				break;
			case Msg.M_Usr_Third_Login:
			case Msg.M_Usr_Login:
				bundle = msg.getData();
				int status_code = bundle.getInt("status_code");
				Log.i("LoginTest", "返回status_code值" + status_code);
				if (status_code == 0) {
					String cust_id = bundle.getString("cust_id");
					access_token = bundle.getString("access_token");
					valid_time = bundle.getString("valid_time");
					Log.i("LoginTest", "返回cust_id值" + cust_id);
					Log.i("LoginTest", "返回access_token值" + access_token);
					Log.i("LoginTest", "返回valid_time值" + valid_time);
				} else {
					Log.i("LoginTest", "登陆失败，用户名或密码错误");
				}
				break;
			case Msg.M_Comm_Sms_Send:
				break;
			case Msg.M_Usr_Volid_Code:
				bundle = msg.getData();
				boolean valid = bundle.getBoolean("valid");
				Log.i("LoginTest", "返回valid_code值" + valid);
				break;
			case Msg.M_Usr_Register:
				Log.i("LoginTest", "M_Usr_Register");
				bundle = msg.getData();
				String custId = bundle.getString("cust_id");
				Log.i("LoginTest", "注册返回custId值" + custId);
				break;

			case Msg.M_Usr_Create:
				Log.i("LoginTest", "M_Usr_Create");
				bundle = msg.getData();
				custId = bundle.getString("cust_id");
				Log.i("LoginTest", "M_Usr_Create返回custId值" + custId);
				break;

			case Msg.M_Usr_Get:
				Log.i("LoginTest", "M_Usr_Get");
				bundle = msg.getData();
				Customer customer = (Customer) bundle
						.getSerializable("customer");
				custId = customer.getCustId() + "";
				Log.i("LoginTest", "M_Usr_Get返回custId值" + custId);
				break;
			case Msg.M_Usr_Update:
				Log.i("LoginTest", "M_Usr_Update");
				bundle = msg.getData();
				customer = (Customer) bundle
						.getSerializable("customer");
				custId = customer.getCustId() + "";
				Log.i("LoginTest", "M_Usr_Update返回custId值" + custId);
				break;
			}

			return false;
		}

	};

}
