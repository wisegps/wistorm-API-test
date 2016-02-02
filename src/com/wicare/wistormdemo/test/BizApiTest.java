/**
 * UserApiTest.java
 * c
 * TODO
 * 2015-12-11
 */
package com.wicare.wistormdemo.test;

import java.util.HashMap;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.test.ActivityInstrumentationTestCase2;
import com.baidu.android.common.logging.Log;
import com.wicare.wistorm.api.WBusinessApi;
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
public class BizApiTest extends ActivityInstrumentationTestCase2<LoginTest> {

	/**
	 * @param activityClass
	 */
	public WBusinessApi bizApi;

	public WCommApi commApi;
	public Handler handler;

	public BizApiTest() {
		super(LoginTest.class);
		handler = new Handler(callback);

	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		bizApi = new WBusinessApi(handler);
		commApi = new WCommApi(handler);
		BaseVolley.init(getActivity());
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * 创建业务信息
	 * 
	 * 参数: seller_id; //商户Id cust_id: 车主用户ID cust_name: 车主名称 obj_id: 车辆ID
	 * obj_name: 车牌号 mileage: 行驶里程 business_type: 业务类型 business_content: 业务内容
	 * 返回： status_code: 状态码 business_id: 新建业务ID
	 */
	public void create() {

		HashMap<String, String> params = new HashMap<String, String>();
		params.put(
				"access_token",
				"f1b3afaf9bbedfcb0ca3f0465a1d2e7e157c1ea55ad8d2dbcaa7083d125d360cc1792bdb116812d81a3e51f8d269cccc");
		params.put("cust_id", "1239");
		params.put("seller_id", "178");
		params.put("cust_name", "testandr");
		params.put("obj_id", "2885");
		params.put("obj_name", "粤testandroid");
		params.put("mileage", "1000");
		params.put("business_type", "1");
		params.put("business_content", "业务内容Java测试");

		bizApi.create(params);
	}

	/**
	 * 获取业务列表 // 
	 * 参数: query_json: 查询json;</br>
	 *  fields: 返回字段 </br>
	 *  sorts: 排序字段,如果倒序,在字段前面加-</br>
	 * page: 分页字段 </br>
	 * min_id: 分页字段的本页最小值 </br>
	 * max_id: 分页字段的本页最小值 </br>
	 * limit: 返回数量 返回：</br>
	 * 按fields返回数据列表</br>
	 */
	public void get() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put(
				"access_token",
				"f1b3afaf9bbedfcb0ca3f0465a1d2e7e157c1ea55ad8d2dbcaa7083d125d360cc1792bdb116812d81a3e51f8d269cccc");
		params.put("max_id", "0");
		params.put("parent_cust_id", "178");
		params.put("query_type", "1");
		params.put("status", "1");
		params.put("sorts", "business_id");

		params.put("page", "business_id");
		
		params.put("limit", "20");
		
		
		String fields = "business_id,business_type,obj_name,obj_id,mileage,evaluate_level,status,arrive_time,leave_time,cust_id,cust_name,business_content,car_brand_id,car_brand,car_series_id,car_series,car_type_id,car_type";
		bizApi.get(params,fields);
	}

	/**
	 * 更新业务状态
	 */
	public void update() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put(
				"access_token",
				"f1b3afaf9bbedfcb0ca3f0465a1d2e7e157c1ea55ad8d2dbcaa7083d125d360cc1792bdb116812d81a3e51f8d269cccc");
		params.put("_business_id", "24");
		String leave_time = "2016-01-15 17:02:04";
		leave_time = Uri.encode(leave_time);
		params.put("leave_time", leave_time);
		params.put("mileage", "1000");
		params.put("obj_id", "2885");
		params.put("status", "2");
		bizApi.update(params);
	}

	public void getTotal() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put(
				"access_token",
				"f1b3afaf9bbedfcb0ca3f0465a1d2e7e157c1ea55ad8d2dbcaa7083d125d360cc1792bdb116812d81a3e51f8d269cccc");
		params.put("parent_cust_id", "178");
		String begin_time = "2015-01-01 00:00:00";
		begin_time = Uri.encode(begin_time);
		String end_time = "2016-01-18 15:15:38";
		end_time = Uri.encode(end_time);
		params.put("begin_time",begin_time);
		params.put("end_time", end_time);
		//String fields = "business_id,business_type,obj_name,obj_id,mileage,evaluate_level,status,arrive_time,leave_time,cust_id,cust_name,business_content,car_brand_id,car_brand,car_series_id,car_series,car_type_id,car_type";
		bizApi.getTotal(params,"");
	}
	Handler.Callback callback = new Handler.Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
			case Msg.M_Biz_Create:
				Bundle bundle = msg.getData();
				String status_code = bundle.getString("status_code");
				String business_id = bundle.getString("business_id");
				Log.i("LoginTest", "返回status_code值" + status_code);
				Log.i("LoginTest", "返回business_id值" + business_id);
				break;
			case Msg.M_Biz_Update:
				bundle = msg.getData();
				status_code = bundle.getString("status_code");
				Log.i("LoginTest", "返回Update status_code值" + status_code);
				break;
			case Msg.M_Biz_List:
				bundle = msg.getData();
				String list = bundle.getString("list");
				Log.i("LoginTest", "返回M_Biz_List status_code值" + list);
				break;
			case Msg.M_Biz_Total:
				bundle = msg.getData();
				String total = bundle.getString("total");
				Log.i("LoginTest", "返回M_Biz_Total status_code值" + total);
				break;
			}

			return false;
		}

	};

}
