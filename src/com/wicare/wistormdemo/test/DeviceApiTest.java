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
import com.wicare.wistorm.api.WDeviceApi;
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
public class DeviceApiTest extends ActivityInstrumentationTestCase2<LoginTest> {

	/**
	 * @param activityClass
	 */
	public WDeviceApi deviceApi;

	public Handler handler;

	public DeviceApiTest() {
		super(LoginTest.class);
		handler = new Handler(callback);

	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		deviceApi = new WDeviceApi(handler);

		BaseVolley.init(getActivity());
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void list() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put(
				"access_token",
				"f1b3afaf9bbedfcb0ca3f0465a1d2e7e157c1ea55ad8d2dbcaa7083d125d360cc1792bdb116812d81a3e51f8d269cccc");
		params.put("parent_cust_id", "178");
		params.put("sorts", "device_id");
		params.put("limit", "20");
		params.put("page", "0");
		String fields = "device_id,serial,cust_id,cust_name,device_type,sim,status,active_time";
		deviceApi.list(params, fields);
	}
	
	
	public void getObdData() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put(
				"access_token",
				"f1b3afaf9bbedfcb0ca3f0465a1d2e7e157c1ea55ad8d2dbcaa7083d125d360cc1792bdb116812d81a3e51f8d269cccc");
		params.put("parent_cust_id", "178");
		params.put("sorts", "device_id");
		params.put("limit", "1000");
		params.put("page", "rcv_time");
		String fields = "rcv_time,obd_data.dpdy,obd_data.sw";
		deviceApi.getObdDatas(params, fields);
	}
	
	public void update() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put(
				"access_token",
				"f1b3afaf9bbedfcb0ca3f0465a1d2e7e157c1ea55ad8d2dbcaa7083d125d360cc1792bdb116812d81a3e51f8d269cccc");
		params.put("_device_id", "1");
		params.put("status", "0");
		params.put("sim", "1331689");
		params.put("cust_id", "178");
		deviceApi.update(params, "");
	}


	Handler.Callback callback = new Handler.Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
			case Msg.M_Device_List:
				Bundle bundle = msg.getData();
				String device = bundle.getString("device");
				Log.i("M_Device_List", "返回device值" + device);
				break;
			case Msg.M_Device_Obd_Datas:
				bundle = msg.getData();
				device = bundle.getString("device");
				Log.i("M_Device_Obd_Datas", "返回device值" + device);
				break;
			case Msg.M_Device_Update:
				bundle = msg.getData();
				device = bundle.getString("device");
				Log.i("M_Device_Update", "返回device值" + device);
				break;
			}

			return false;
		}

	};

}
