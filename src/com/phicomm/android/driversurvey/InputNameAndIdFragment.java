package com.phicomm.android.driversurvey;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlSerializer;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Xml;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputNameAndIdFragment extends Fragment {

	private EditText carEditText;
	private EditText driverEditText;
	private Button startButton;
	private String driverName;
	private String carId;
	private Question mQuestion;
	private SurveySQLiteOpenHelper helper;
	private int mResponseCode;
	private static final int MSG_SUCCESS = 200;
	private Handler mHandler = new Handler(){
		public void handleMessage(Message msg){
		switch(msg.what){
		case MSG_SUCCESS:
			Toast.makeText(getActivity(), "发送成功", Toast.LENGTH_LONG).show();
		break;
		default:
			Toast.makeText(getActivity(), "发送失败", Toast.LENGTH_LONG).show();
		}
		}
	};
	
	public InputNameAndIdFragment(Context context) {
		// TODO Auto-generated constructor stub
		helper = new SurveySQLiteOpenHelper(context);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_input_name_id,
				container, false);
		carEditText = (EditText) view.findViewById(R.id.editText_car_id);
		driverEditText = (EditText) view
				.findViewById(R.id.editText_driver_name);

		Drawable drawableName = getResources().getDrawable(
				R.drawable.accont_number);
		Drawable drawableCarId = getResources().getDrawable(R.drawable.icon_id);
		drawableCarId.setBounds(0, 0, 74, 74);
		drawableName.setBounds(0,0,70,70);
		
			
		carEditText.setCompoundDrawables(drawableCarId, null, null, null);
		driverEditText.setCompoundDrawables(drawableName, null, null, null);

		carEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				carId = s.toString();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		driverEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				driverName = s.toString();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});

		startButton = (Button) view.findViewById(R.id.button_start_survey);
		startButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if (carEditText.getText().toString().equals("")
						|| driverEditText.getText().toString().equals("")) {
					Toast.makeText(getActivity(), "信息不能为空", Toast.LENGTH_SHORT)
							.show();
					return;
				} else {
					SurveyDao dao = new SurveyDao(getActivity());
					ArrayList records = (ArrayList) dao.findById(carId);
					if (records.size() != 0) {
						Toast.makeText(getActivity(), "该单已经存在",
								Toast.LENGTH_SHORT).show();
						return;
					}
				}

				Intent intent = new Intent(getActivity(),
						SurveyActivity.class);
				intent.putExtra("carId", carId);
				intent.putExtra("driverName", driverName);
				startActivity(intent);
				startRefresh();
			}
		});
		return view;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.main_activity_options, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_maim_check_summarize_upload:
			new Thread(runnable).start();
			return true;
		case R.id.menu_maim_check_summarize:
			Intent intent = new Intent(getActivity(),
					MonthSumInputActivity.class);
			startActivity(intent);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	private void startRefresh() {
		// System.out.println(mQuestion.getResults());
		// System.out.println("start refresh");
		SurveyActivity.index = 0;
		// System.out.println(SurveyActivity.index);

	}
	
	public void uploadDBXML() throws Exception{
		//通过类装载器装载xml资源
		InputStream inputStream=this.getClass().getClassLoader().getResourceAsStream("test.xml");
//		byte[] xml = CreateXMLDoc().getBytes();
		
		String path = "http://192.168.1.100:8080/first/getDate.jsp";
		URL url = new URL(path);
		
//		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		conn.setRequestMethod("POST");
//		conn.setConnectTimeout(5000);
//		conn.setDoOutput(true);
//		
//		//设置http请求的头字段
//		conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8"); //内容类型
//		conn.setRequestProperty("Content-Length", String.valueOf(xml.length)); //实体内容长度
//		conn.getOutputStream().write(xml); //通过输出流把数据写到服务器
//		Log.e("upload", "the ResponseCode is" + conn.getResponseCode());
//		mResponseCode = conn.getResponseCode();	
		
		Map<String,String> params = new HashMap<String,String>();
		params.put("data",CreateXMLDoc());
		byte[] xml = getRequestData(params, "utf-8").toString().getBytes(); //获得请求体
		try{
			
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setConnectTimeout(5000);
			conn.setDoOutput(true);
			
			//设置http请求的头字段
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); //设置请求体的类型是文本类型
			conn.setRequestProperty("Content-Length", String.valueOf(xml.length)); //实体内容长度
			conn.getOutputStream().write(xml); //通过输出流把数据写到服务器
			mResponseCode = conn.getResponseCode();	
		} catch (IOException e) {
             //e.printStackTrace();
			Log.e("upload", "send xml data Excetption");
        }
		
	}
	
	public String CreateXMLDoc() throws Exception{
		  ByteArrayOutputStream baos=new ByteArrayOutputStream();
		  XmlSerializer xml=Xml.newSerializer();
		  xml.setOutput(baos, "UTF-8");
		  xml.startDocument("UTF-8", null);
		  
		  SQLiteDatabase db = helper.getReadableDatabase();

		  Cursor cursor = db.rawQuery("select * from survey",
					null);
		  ArrayList<RecordAll> records = (ArrayList<RecordAll>) createRecordsList(cursor);
		  db.close();
		  
		 
		  xml.startTag(null, "carOrderIDSum");
		  for(int id =0; id < records.size(); id++){
			  xml.startTag(null, "carOrderIDSum"+id);
		      xml.startTag(null, "date");
		      xml.text(records.get(id).getDate());
		      xml.endTag(null, "date");
		  
		      xml.startTag(null, "carId");
		      xml.text(records.get(id).getCarID());
		      xml.endTag(null, "carId");
		      
		      xml.startTag(null, "driverName");
		      xml.text(records.get(id).getDirverName());
		      xml.endTag(null, "driverName");
		      
		      xml.startTag(null, "answer1");
		      xml.text(records.get(id).getAnswer1());
		      xml.endTag(null, "answer1");
		      
		      xml.startTag(null, "answer2");
		      xml.text(records.get(id).getAnswer2());
		      xml.endTag(null, "answer2");
		      
		      xml.startTag(null, "answer3");
		      xml.text(records.get(id).getAnswer3());
		      xml.endTag(null, "answer3");
		      
		      xml.startTag(null, "answer4");
		      xml.text(records.get(id).getAnswer4());
		      xml.endTag(null, "answer4");
		      
		      xml.startTag(null, "answer5");
		      xml.text(records.get(id).getAnswer5());
		      xml.endTag(null, "answer5");
		      
		      xml.startTag(null, "answer6");
		      xml.text(records.get(id).getAnswer6());
		      xml.endTag(null, "answer6");
		      
		      xml.startTag(null, "answer7");
		      xml.text(records.get(id).getAnswer7());
		      xml.endTag(null, "answer7");
		      
		      xml.startTag(null, "answer8");
		      xml.text(records.get(id).getAnswer8());
		      xml.endTag(null, "answer8");
		      
		      xml.startTag(null, "answer9");
		      xml.text(records.get(id).getAnswer9());
		      xml.endTag(null, "answer9");
		      
		      xml.startTag(null, "answer10");
		      xml.text(records.get(id).getAnswer10());
		      xml.endTag(null, "answer10");
		      xml.endTag(null, "carOrderIDSum"+id);
		  }
		  xml.endTag(null, "carOrderIDSum");
		  xml.endDocument();
		  byte[] xmlData=baos.toByteArray();
		  String xmlString=new String(xmlData,"UTF-8");
		  baos.flush();
		  baos.close();
		  baos=null;
		  return xmlString;
		 }
	
	public List<RecordAll> createRecordsList(Cursor cursor) {
		List<RecordAll> records = new ArrayList<RecordAll>();
		while (cursor.moveToNext()) {
			String date = cursor.getString(cursor.getColumnIndex("date"));
			String carId = cursor.getString(cursor.getColumnIndex("carId"));
			String driverName = cursor.getString(cursor.getColumnIndex("driverName"));
			String answer1 = cursor.getString(cursor
					.getColumnIndex("answer1"));
			String answer2 = cursor.getString(cursor
					.getColumnIndex("answer2"));
			String answer3 = cursor.getString(cursor
					.getColumnIndex("answer3"));
			String answer4 = cursor.getString(cursor
					.getColumnIndex("answer4"));
			String answer5 = cursor.getString(cursor
					.getColumnIndex("answer5"));
			String answer6 = cursor.getString(cursor
					.getColumnIndex("answer6"));
			String answer7 = cursor.getString(cursor
					.getColumnIndex("answer7"));
			String answer8 = cursor.getString(cursor
					.getColumnIndex("answer8"));
			String answer9 = cursor.getString(cursor
					.getColumnIndex("answer9"));
			String answer10 = cursor.getString(cursor
					.getColumnIndex("answer10"));
			RecordAll recordAll = new RecordAll(date, carId, driverName, answer1, answer2, answer3, answer4,
					answer5, answer6, answer7, answer8, answer9,
					answer10);
			records.add(recordAll);
		}
		return records;
	}
	
	Runnable runnable = new Runnable(){
		public void run(){
			try {
				uploadDBXML();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mHandler.obtainMessage(mResponseCode).sendToTarget();
		}
	};
	
	   public static StringBuffer getRequestData(Map<String, String> params, String encode) {
		      StringBuffer stringBuffer = new StringBuffer();        //存储封装好的请求体信息
		      try {
		            for(Map.Entry<String, String> entry : params.entrySet()) {
		                stringBuffer.append(entry.getKey())
		                      .append("=")
		                      .append(URLEncoder.encode(entry.getValue(), encode))
		                      .append("&");
		            }
		           stringBuffer.deleteCharAt(stringBuffer.length() - 1);    //删除最后的一个"&"
		        } catch (Exception e) {
		           e.printStackTrace();
		       }
		       return stringBuffer;
		    }
	
}
