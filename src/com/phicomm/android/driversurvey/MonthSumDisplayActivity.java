package com.phicomm.android.driversurvey;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

public class MonthSumDisplayActivity extends Activity {

	// private TextView textViewAnswer1, textViewAnswer2, textViewAnswer3,
	// textViewAnswer4, textViewAnswer5, textViewAnswer6, textViewAnswer7,
	// textViewAnswer8, textViewAnswer9, textViewAnswer10, textViewInfo;
	// private TextView textViewInfo;
	private TextView textView_info;
	private ListView listView;
	private String driverName;
	private String carId;
	private String month;
	private int[][] answerOneToNine = new int[9][2];
	private int[] answerTen = new int[4];
	private HistogramView hv10_1, hv10_2, hv10_3, hv10_0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_month_summary);

		textView_info = (TextView) findViewById(R.id.textView_sum_display);

		getActionBar().setDisplayShowHomeEnabled(false);
		// getActionBar().setTitle("查询结果");

		driverName = getIntent().getExtras().getString(
				MonthSumInputActivity.MONTH_INPUT_DRIVER_NAME);
		carId = getIntent().getExtras().getString(
				MonthSumInputActivity.MONTH_INPUT_ID);
		month = getIntent().getExtras().getString(
				MonthSumInputActivity.MONTH_INPUT_DATE);

		SurveyDao dao = new SurveyDao(this);
		ArrayList<Record> records = new ArrayList<Record>();

		if (!(carId.equals(""))) {
			// textViewInfo.setText("该单结果为：");
			// get the result list finding by carId
			getActionBar().setTitle("该单结果");
			driverName = dao.findNameById(carId);
			textView_info.setText("单号：" + carId + "    司机姓名：" + driverName);
			records = (ArrayList<Record>) dao.findById(carId);

		} else if ((!(driverName.equals(""))) && month.equals("")) {
			// textViewInfo.setText("该司机历史记录为：");
			// get the result list finding by driverName
			getActionBar().setTitle("该司机历史记录");
			textView_info.setText("司机姓名：" + driverName);
			records = (ArrayList<Record>) dao.findByDriverName(driverName);
		} else if ((!(driverName.equals(""))) && (!month.equals(""))) {
			// get the result list finding by driverName
			// choose by date in summarizeRecords()
			records = (ArrayList<Record>) dao.findByDriverName(driverName);
			// textViewInfo.setText("该司机该月记录为：");
			getActionBar().setTitle(driverName + "该月记录");
			String[] str = month.split("-");
			textView_info.setText("月份：" + str[0] + "-" + str[1]);
		}

		// 数据库数据汇总到数组中
		summarizeRecords(records);
		// setText();

		// HistogramView hv1 = (HistogramView)
		// findViewById(R.id.hv_question_test);
		// float progress =
		// (float)answerOneToNine[4][1]/(answerOneToNine[4][1]+answerOneToNine[4][0]);
		// hv1.setProgress(progress);
		// hv1.setRateBackgroundColor("#123456");
		listView = (ListView) findViewById(R.id.lv_activity_month_summary);
		listView.setAdapter(new myAdapter());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.summarize_display_activity_options,
				menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_display_export:
			Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();

			FileExportHelper helper = new FileExportHelper(this);

			String fileName = "test.txt";
			helper.deleteSDFile(fileName);

			try {
				helper.createSDFile(fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (!(carId.equals(""))) {

				helper.writeSDFile("单号：" + carId + "     姓名" + driverName,
						fileName);
				helper.writeSDFile("\r\n\r\n", fileName);
				for (int i = 0; i < 9; i++) {
					helper.writeSDFile("第" + (i + 1) + "题   是："
							+ answerOneToNine[i][1] + "次，否："
							+ answerOneToNine[i][0] + "次\r\n\r\n", fileName);
				}
				helper.writeSDFile("第10题   非常满意：" + answerTen[0] + "次\r\n\r\n",
						fileName);
				helper.writeSDFile("第10题   满意：" + answerTen[1] + "次\r\n\r\n",
						fileName);
				helper.writeSDFile("第10题   不满意：" + answerTen[2] + "次\r\n\r\n",
						fileName);
				helper.writeSDFile(
						"第10题   非常不满意：" + answerTen[3] + "次\r\n\r\n", fileName);

			} else if ((!(driverName.equals(""))) && month.equals("")) {
				helper.writeSDFile("姓名：" + driverName, fileName);
				helper.writeSDFile("\r\n\r\n", fileName);
				for (int i = 0; i < 9; i++) {
					helper.writeSDFile("第" + (i + 1) + "题   是："
							+ answerOneToNine[i][1] + "次，否："
							+ answerOneToNine[i][0] + "次\r\n\r\n", fileName);
				}
				helper.writeSDFile("第10题   非常满意：" + answerTen[0] + "次\r\n\r\n",
						fileName);
				helper.writeSDFile("第10题   满意：" + answerTen[1] + "次\r\n\r\n",
						fileName);
				helper.writeSDFile("第10题   不满意：" + answerTen[2] + "次\r\n\r\n",
						fileName);
				helper.writeSDFile(
						"第10题   非常不满意：" + answerTen[3] + "次\r\n\r\n", fileName);
			} else if ((!(driverName.equals(""))) && (!month.equals(""))) {
				helper.writeSDFile("日期：" + month + "    姓名：" + driverName,
						fileName);
				helper.writeSDFile("\r\n\r\n", fileName);
				for (int i = 0; i < 9; i++) {
					helper.writeSDFile("第" + (i + 1) + "题   是："
							+ answerOneToNine[i][1] + "次，否："
							+ answerOneToNine[i][0] + "次\r\n\r\n", fileName);
				}
				helper.writeSDFile("第10题   非常满意：" + answerTen[0] + "次\r\n\r\n",
						fileName);
				helper.writeSDFile("第10题   满意：" + answerTen[1] + "次\r\n\r\n",
						fileName);
				helper.writeSDFile("第10题   不满意：" + answerTen[2] + "次\r\n\r\n",
						fileName);
				helper.writeSDFile(
						"第10题   非常不满意：" + answerTen[3] + "次\r\n\r\n", fileName);
			}

			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	// private void setText() {
	// textViewAnswer1.setText("问题1：是" + answerOneToNine[0][1] + "次，否："
	// + answerOneToNine[0][0] + "次");
	// textViewAnswer2.setText("问题2：是" + answerOneToNine[1][1] + "次，否："
	// + answerOneToNine[1][0] + "次");
	// textViewAnswer3.setText("问题3：是" + answerOneToNine[2][1] + "次，否："
	// + answerOneToNine[2][0] + "次");
	// textViewAnswer4.setText("问题4：是" + answerOneToNine[3][1] + "次，否："
	// + answerOneToNine[3][0] + "次");
	// textViewAnswer5.setText("问题5：是" + answerOneToNine[4][1] + "次，否："
	// + answerOneToNine[4][0] + "次");
	// textViewAnswer6.setText("问题6：是" + answerOneToNine[5][1] + "次，否："
	// + answerOneToNine[5][0] + "次");
	// textViewAnswer7.setText("问题7：是" + answerOneToNine[6][1] + "次，否："
	// + answerOneToNine[6][0] + "次");
	// textViewAnswer8.setText("问题8：是" + answerOneToNine[7][1] + "次，否："
	// + answerOneToNine[7][0] + "次");
	// textViewAnswer9.setText("问题9：是" + answerOneToNine[8][1] + "次，否："
	// + answerOneToNine[8][0] + "次");
	// textViewAnswer10.setText("司机的问题十统计结果为：“非常满意”" + answerTen[0] + "次，“满意”"
	// + answerTen[1] + "次，“不满意”" + answerTen[2] + "次，“非常不满意”"
	// + answerTen[3] + "次");
	// }

	private void summarizeRecords(ArrayList<Record> records) {

		for (Record record : records) {
			boolean monthCorrect = false;
			// 判断是否是本年度的这个月？
			if (!month.equals("")) {
				String[] monthInput = month.split("-");
				String[] monthDatabase = record.getDate().split("-");
				// System.out.println(record.getDate());
				// for (int i = 0; i < monthInput.length; i++) {
				// System.out.println(monthInput[i]);
				// }
				// System.out.println(monthDatabase.length);
				// for (int i = 0; i < monthDatabase.length; i++) {
				// System.out.println(monthDatabase[i]);
				// }
				if ((monthInput[0].equals(monthDatabase[0]))
						&& monthInput[1].equals(monthDatabase[1])) {
					monthCorrect = true;
				}
			}
			//若ID不为空，无视月份
			if (!carId.equals("")) {
				dataBaseToArray(record);
			} else if (month.equals("")
					|| ((!month.equals("")) && monthCorrect)) {
				// System.out.println("summarize data");
				dataBaseToArray(record);
			}
		}
	}

	private void dataBaseToArray(Record record) {
		answerOneToNine[0][0] += Integer.parseInt(record.getAnswer1_2());
		answerOneToNine[0][1] += Integer.parseInt(record.getAnswer1_1());
		answerOneToNine[1][0] += Integer.parseInt(record.getAnswer2_2());
		answerOneToNine[1][1] += Integer.parseInt(record.getAnswer2_1());
		answerOneToNine[2][0] += Integer.parseInt(record.getAnswer3_2());
		answerOneToNine[2][1] += Integer.parseInt(record.getAnswer3_1());
		answerOneToNine[3][0] += Integer.parseInt(record.getAnswer4_2());
		answerOneToNine[3][1] += Integer.parseInt(record.getAnswer4_1());
		answerOneToNine[4][0] += Integer.parseInt(record.getAnswer5_2());
		answerOneToNine[4][1] += Integer.parseInt(record.getAnswer5_1());
		answerOneToNine[5][0] += Integer.parseInt(record.getAnswer6_2());
		answerOneToNine[5][1] += Integer.parseInt(record.getAnswer6_1());
		answerOneToNine[6][0] += Integer.parseInt(record.getAnswer7_2());
		answerOneToNine[6][1] += Integer.parseInt(record.getAnswer7_1());
		answerOneToNine[7][0] += Integer.parseInt(record.getAnswer8_2());
		answerOneToNine[7][1] += Integer.parseInt(record.getAnswer8_1());
		answerOneToNine[8][0] += Integer.parseInt(record.getAnswer9_2());
		answerOneToNine[8][1] += Integer.parseInt(record.getAnswer9_1());
		answerTen[0] += Integer.parseInt(record.getAnswer10_1());
		answerTen[1] += Integer.parseInt(record.getAnswer10_2());
		answerTen[2] += Integer.parseInt(record.getAnswer10_3());
		answerTen[3] += Integer.parseInt(record.getAnswer10_4());
	}

	class myAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return 13;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if (position < 9) {
				View view = View.inflate(MonthSumDisplayActivity.this,
						R.layout.summarize_item, null);
				TextView tv1 = (TextView) view
						.findViewById(R.id.textview_answer1_monthSummary_yes);
				TextView tv2 = (TextView) view
						.findViewById(R.id.textview_answer1_monthSummary_title);
				TextView tv3 = (TextView) view
						.findViewById(R.id.textview_answer1_monthSummary_no);

				HistogramView hv = (HistogramView) view
						.findViewById(R.id.hv_question1);
				HistogramView hv1 = (HistogramView) view
						.findViewById(R.id.hv_question1);
				float progress = (float) answerOneToNine[position][1]
						/ (answerOneToNine[position][1] + answerOneToNine[position][0]);
				hv1.setProgress(progress);
				hv1.setRateBackgroundColorLeft("#ee8833");
				hv1.setRateBackgroundColorRight("#3366cc");

				DecimalFormat df = new DecimalFormat("#0.00");

				tv2.setText("第" + (position + 1) + "题");
				tv1.setText("是：" + df.format(progress * 100) + "%");
				tv3.setText(df.format(100 - progress * 100) + "%" + ":否");

				return view;
			} else {
				DecimalFormat df = new DecimalFormat("#0.00");
				View view = View.inflate(MonthSumDisplayActivity.this,
						R.layout.summarize_item, null);
				TextView tv1 = (TextView) view
						.findViewById(R.id.textview_answer1_monthSummary_yes);
				TextView tv2 = (TextView) view
						.findViewById(R.id.textview_answer1_monthSummary_title);

				tv2.setText("第10题");
				int sum = answerTen[0] + answerTen[1] + answerTen[2]
						+ answerTen[3];
				switch (position) {
				case 9:
					float progress0 = (float) answerTen[0] / sum;
					tv1.setText("非常满意：" + df.format(progress0 * 100) + "%");
					HistogramView hv0 = (HistogramView) view
							.findViewById(R.id.hv_question1);
					hv0.setProgress(progress0);
					hv0.setRateBackgroundColorLeft("#ee8833");
					hv0.setRateBackgroundColorRight("#ffffff");
					break;
				case 10:
					float progress1 = (float) answerTen[1] / sum;
					tv1.setText("满意：" + df.format(progress1 * 100) + "%");
					HistogramView hv1 = (HistogramView) view
							.findViewById(R.id.hv_question1);
					hv1.setProgress(progress1);
					hv1.setRateBackgroundColorLeft("#ee8833");
					hv1.setRateBackgroundColorRight("#ffffff");
					break;
				case 11:
					float progress2 = (float) answerTen[2] / sum;
					tv1.setText("不满意：" + df.format(progress2 * 100) + "%");
					HistogramView hv2 = (HistogramView) view
							.findViewById(R.id.hv_question1);
					hv2.setProgress(progress2);
					hv2.setRateBackgroundColorLeft("#ee8833");
					hv2.setRateBackgroundColorRight("#ffffff");
					break;
				case 12:
					float progress3 = (float) answerTen[3] / sum;
					tv1.setText("非常不满意：" + df.format(progress3 * 100) + "%");
					HistogramView hv3 = (HistogramView) view
							.findViewById(R.id.hv_question1);
					hv3.setProgress(progress3);
					hv3.setRateBackgroundColorLeft("#ee8833");
					hv3.setRateBackgroundColorRight("#ffffff");
					break;

				default:
					break;
				}
				return view;
			}

		}

	}

}
