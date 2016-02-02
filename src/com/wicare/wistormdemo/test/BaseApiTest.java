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
import com.wicare.wistorm.api.WBaseApi;
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
public class BaseApiTest extends ActivityInstrumentationTestCase2<LoginTest> {

	/**
	 * @param activityClass
	 */
	public WBaseApi baseApi;

	public Handler handler;

	public BaseApiTest() {
		super(LoginTest.class);
		handler = new Handler(callback);

	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		baseApi = new WBaseApi(handler);

		BaseVolley.init(getActivity());
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	
	/**
	 * 获取车辆品牌
	 */
	public void getBrands() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put(
				"access_token",
				"f1b3afaf9bbedfcb0ca3f0465a1d2e7e157c1ea55ad8d2dbcaa7083d125d360cc1792bdb116812d81a3e51f8d269cccc");
		baseApi.getBrands(params);
	}

	/**
	 * 获取车系
	 * pid 品牌的id
	 */
	public void getSeries() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put(
				"access_token",
				"f1b3afaf9bbedfcb0ca3f0465a1d2e7e157c1ea55ad8d2dbcaa7083d125d360cc1792bdb116812d81a3e51f8d269cccc");
		params.put("pid", "228");
		baseApi.getSeries(params);
	}

	/**
	 * 获取类型
	 * pid 车系的id
	 */
	public void getTypes() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put(
				"access_token",
				"f1b3afaf9bbedfcb0ca3f0465a1d2e7e157c1ea55ad8d2dbcaa7083d125d360cc1792bdb116812d81a3e51f8d269cccc");
		params.put("pid", "4403");
		baseApi.getTypes(params);
	}

	Handler.Callback callback = new Handler.Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
			case Msg.M_Base_Brands:
				Bundle bundle = msg.getData();
				String brands = bundle.getString("brands");
				Log.i("BaseApiTest", "返回brands值" + brands);
				break;
			case Msg.M_Base_Series:
				bundle = msg.getData();
				brands = bundle.getString("series");
				Log.i("BaseApiTest", "返回series值" + brands);
				break;
			case Msg.M_Base_Types:
				bundle = msg.getData();
				brands = bundle.getString("types");
				Log.i("BaseApiTest", "返回types值" + brands);
				break;
			}

			return false;
		}

	};

}
