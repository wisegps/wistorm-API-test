/**
 * VehicleApiTest.java
 * c
 * TODO
 * 2015-1-20
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
import com.wicare.wistorm.api.WVehicleApi;

import com.wicare.wistorm.http.BaseVolley;
import com.wicare.wistorm.http.Msg;
import com.wicare.wistorm.model.Customer;
import com.wicare.wistormdemo.activity.LoginTest;

/**
 * VehicleApiTest
 * 
 * @author c TODO 2015-1-20
 */
public class VehicleApiTest extends ActivityInstrumentationTestCase2<LoginTest> {

	/**
	 * @param activityClass
	 */
	public WVehicleApi vehicleApi;

	public Handler handler;

	public VehicleApiTest() {
		super(LoginTest.class);
		handler = new Handler(callback);

	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		vehicleApi = new WVehicleApi(handler);

		BaseVolley.init(getActivity());
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void update() {
		HashMap<String, String> params = new HashMap<String, String>();

		params.put(
				"access_token",
				"f1b3afaf9bbedfcb0ca3f0465a1d2e7e157c1ea55ad8d2dbcaa7083d125d360cc1792bdb116812d81a3e51f8d269cccc");
		params.put("_cust_id", "1273");//客户ID
		params.put("_obj_id", "2949");//车辆ID
		params.put("car_brand", "奥克斯");
		params.put("car_brand_id", " 12");
		params.put("car_series", "朗杰");
		params.put("car_series_id", "1559");
		params.put("car_type", "2004 款 普通型");
		params.put("car_type_id", "6894");
		params.put("cust_name", "客户");
		params.put("frame_no", "123456");
		params.put("mileage", "1000");
		params.put("obj_name", "粤CESHI001");
		vehicleApi.update(params);
	}
	
	
	
	public void search() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put(
				"access_token",
				"f1b3afaf9bbedfcb0ca3f0465a1d2e7e157c1ea55ad8d2dbcaa7083d125d360cc1792bdb116812d81a3e51f8d269cccc");
		params.put("seller_id", "178");
		params.put("max_id","0");
		params.put("obj_name","a");
		params.put("sorts","obj_id");
		params.put("limit","20");
		
		String fields = "nick_name,cust_name,obj_id,cust_id,obj_name,device_id,car_brand_id,car_brand,car_series_id,car_series,car_type_id,car_type,frame_no,maintain_last_mileage,mileage,arrive_count,evaluate_count,last_arrive_time,business_status,evaluate_level";
		vehicleApi.search(params,fields);
	}
	
	public void list() {
		HashMap<String, String> params = new HashMap<String, String>();

		params.put(
				"access_token",
				"f1b3afaf9bbedfcb0ca3f0465a1d2e7e157c1ea55ad8d2dbcaa7083d125d360cc1792bdb116812d81a3e51f8d269cccc");
		params.put("seller_id","178");
		params.put("sorts","obj_id");
		params.put("limit","20");
		String fields = "nick_name,cust_name,obj_id,cust_id,obj_name,device_id,car_brand_id,car_brand,car_series_id,car_series,car_type_id,car_type,frame_no,maintain_last_mileage,mileage,arrive_count,evaluate_count,last_arrive_time,business_status,evaluate_level";
		vehicleApi.list(params,fields);
	}



	Handler.Callback callback = new Handler.Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
			case Msg.M_Vehicle_Update:
				Bundle bundle = msg.getData();
				String vehicle = bundle.getString("vehicle");
				Log.i("VehicleApiTest", "返回vehicle值" + vehicle);
				break;
			case Msg.M_Vehicle_Search:
				bundle = msg.getData();
				vehicle = bundle.getString("vehicle");
				Log.i("VehicleApiTest", "返回M_Vehicle_Search值" + vehicle);
				break;
			case Msg.M_Vehicle_List:
				bundle = msg.getData();
				vehicle = bundle.getString("vehicle");
				Log.i("VehicleApiTest", "返回M_Vehicle_List值" + vehicle);
				break;
			}

			return false;
		}

	};

}
