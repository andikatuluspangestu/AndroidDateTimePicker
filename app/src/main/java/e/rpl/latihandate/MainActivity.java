package e.rpl.latihandate;


import android.os.Bundle;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.*;
import android.os.*;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends Activity 
{
	Calendar myCalender;
	DatePickerDialog.OnDateSetListener date;
	EditText txt_tgl, txt_jam;
	Button btn_get_datetime;
	TextView tampil_tanggal, tampil_jam;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		txt_tgl = (EditText)findViewById(R.id.txt_tgl);
		txt_jam = (EditText)findViewById(R.id.txt_jam);
		tampil_tanggal = (TextView) findViewById(R.id.tampil_date);
		tampil_jam = (TextView) findViewById(R.id.tampil_time);
		btn_get_datetime = (Button) findViewById(R.id.btn_get_datetime);
		myCalender = Calendar.getInstance();
		date = new DatePickerDialog.OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear, 
								  int dayOfMonth) {
				myCalender.set(Calendar.YEAR, year);
				myCalender.set(Calendar.MONTH, monthOfYear);
				myCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
				updateLabel();
			}
		};
		txt_tgl.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					new DatePickerDialog(MainActivity.this, 
										 date,myCalender.get(Calendar.YEAR),

										 myCalender.get(Calendar.MONTH),myCalender.get(Calendar.DAY_OF_MONTH)).show();
				}
			});
		txt_jam.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Calendar mcurrentTime = Calendar.getInstance();
					int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
					int minute = mcurrentTime.get(Calendar.MINUTE);
					TimePickerDialog mTimePicker;
					mTimePicker = new TimePickerDialog(MainActivity.this, new 
						TimePickerDialog.OnTimeSetListener() {
							@Override 
							public void onTimeSet(TimePicker timePicker, int 
												  selectedHour, int selectedMinute) {
								txt_jam.setText(selectedHour + ":" +selectedMinute);
							}
						}, hour, minute, true); // 24 hour time
					mTimePicker.setTitle("Select Time");
					mTimePicker.show();
				}
			});
		btn_get_datetime.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Toast.makeText(MainActivity.this,
								   "Tanggal : " + txt_tgl.getText().toString() +"\n" +
								   "Jam : " + txt_jam.getText().toString()
								   , Toast.LENGTH_SHORT).show();
					//menampilkan tanggal pada TextView
					tampil_tanggal.setText((CharSequence)txt_tgl.getText());
					tampil_jam.setText((CharSequence)txt_jam.getText());
				}
			});
	}
	private void updateLabel(){
		String myFormat ="yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(myFormat,Locale.US);
		txt_tgl.setText(sdf.format(myCalender.getTime()));
	}
	public void Clear(View view) {
		tampil_tanggal.setText("");
		tampil_jam.setText("");
		txt_tgl.getText().clear();
		txt_jam.getText().clear();
	}
	
		
   
}
