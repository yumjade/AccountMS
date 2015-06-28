package com.yu_zh.accountms.activity;

import java.util.Calendar;

import com.yu_zh.accountms.dao.OutaccountDAO;
import com.yu_zh.accountms.model.Tb_outaccount;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddOutaccount extends Activity {

	protected static final int DATE_DIALOG_ID = 0;
	private EditText txtOutMoney, txtOutTime, txtOutAddress, txtOutMark;
	private Spinner spOutType;
	private Button btnOutSave, btnOutCancel;
	private int mYear, mMonth, mDay;
	OutaccountDAO outaccountDAO = new OutaccountDAO(AddOutaccount.this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addoutaccount);

		txtOutMoney = (EditText) findViewById(R.id.txtOutMoney);
		txtOutTime = (EditText) findViewById(R.id.txtOutTime);
		txtOutAddress = (EditText) findViewById(R.id.txtOutAddress);
		txtOutMark = (EditText) findViewById(R.id.txtOutMark);
		spOutType = (Spinner) findViewById(R.id.spOutType);
		btnOutSave = (Button) findViewById(R.id.btnOutSave);
		btnOutCancel = (Button) findViewById(R.id.btnOutCancel);

		txtOutTime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);

			}
		});
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
		updateDisplay();

		btnOutSave.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String strOutMoney = txtOutMoney.getText().toString();
				if (!strOutMoney.isEmpty()) {
					Tb_outaccount tb_outaccount = new Tb_outaccount(outaccountDAO.getMaxId() + 1,
							Double.parseDouble(strOutMoney), txtOutTime.getText().toString(), 
							spOutType.getSelectedItem().toString(), txtOutAddress.getText().toString(),
							txtOutMark.getText().toString());
					outaccountDAO.add(tb_outaccount);
					Toast.makeText(AddOutaccount.this, "【新增支出】数据添加成功！", Toast.LENGTH_SHORT).show();
				}else {
					Toast.makeText(AddOutaccount.this, "请输入支出金额！", Toast.LENGTH_SHORT).show();
				}

			}
		});

		btnOutCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				txtOutMoney.setText("");
				txtOutMoney.setHint("0.00");
				txtOutTime.setText("");
				txtOutTime.setHint("2015-06-01");
				spOutType.setSelection(0);
				txtOutAddress.setText("");
				txtOutMark.setText("");
			}
		});
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
					mDay);
		}
		return null;
	}

	DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			updateDisplay();
		}

	};

	private void updateDisplay() {
		txtOutTime.setText(new StringBuilder().append(mYear).append("-")
				.append(mMonth + 1).append("-").append(mDay));

	}
}
