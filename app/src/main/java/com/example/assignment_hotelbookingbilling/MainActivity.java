package com.example.assignment_hotelbookingbilling;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    TextView txtCheckIn, txtCheckOut,txtRoom,txtDays,txtTotal,txtTotalVat,txtTotalSC,txtGTotal;
    EditText etRoom;
    Button btnCalculate;
    boolean checkIn=false;
    boolean checkOut=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Spinner cityDropDown = findViewById(R.id.spnCity);
        String[] city = new String[]{"Select City","Kathmandu", "Pokhara", "Bhaktapur", "Biratnagar", "Itahari"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, city);
        cityDropDown.setAdapter(adapter);

        // Hotel type in drop down
        final Spinner hotelTypeDropDown = findViewById(R.id.spnHotelType);
        String[] hotelType = new String[]{"Select Room Type","Classic - Rs 1000", "AC - Rs1500", "Deluxe - Rs 2000"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, hotelType);
        hotelTypeDropDown.setAdapter(adapter1);
        txtTotal=findViewById(R.id.txtTotal);
        txtTotalVat=findViewById(R.id.txtTotalVat);
        txtTotalSC=findViewById(R.id.txtTotalSC);
        etRoom=findViewById(R.id.etRoom);
        txtDays=findViewById(R.id.txtDays);
        btnCalculate=findViewById(R.id.btnCalculate);
        txtCheckIn=findViewById(R.id.txtCheckIn);
        txtCheckOut=findViewById(R.id.txtCheckOut);
        txtRoom=findViewById(R.id.txtRoomCost);

        txtCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIn=true;
                checkOut=false;
                loadDatePicker();
            }
        });
        txtCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 checkIn=false;
                 checkOut=true;
                loadDatePicker();
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int difference=((int)((startDate.getTime()/(24*60*60*1000))
//                                -(int)(endDate.getTime()/(24*60*60*1000))));
                if(hotelTypeDropDown.getSelectedItem().toString()=="Select Room Type"){
                    Toast.makeText(getApplicationContext(), "Select Room type", Toast.LENGTH_LONG).show();
                    return;
                }
                if(hotelTypeDropDown.getSelectedItem().toString()=="Classic - Rs 1000"){
                    txtRoom.setText("1000");
                }
                else if(hotelTypeDropDown.getSelectedItem().toString()=="AC - Rs1500"){
                    txtRoom.setText("1500");
                }
                else{
                    txtRoom.setText("2000");
                }

                txtDays.setText(etRoom.getText().toString()+" * "+ txtRoom.getText().toString());
                int total=Integer.parseInt(etRoom.getText().toString())* Integer.parseInt(txtRoom.getText().toString());
                int withvat=total + ((13*total)/100);
                txtTotal.setText(total+"");
                txtTotalVat.setText(withvat+ "");
                txtTotalSC.setText(withvat + ((10*withvat)/100)+ "");
            }

        });
    }


    private void loadDatePicker(){
        final Calendar c=Calendar.getInstance();
        int year=  c.get(Calendar.YEAR);
        int month=  c.get(Calendar.MONTH);
        int day=  c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd=new DatePickerDialog(this,this,year,month,day);
        dpd.show();

    }

    public void onDateSet(DatePicker view, int year,int month,int dayOfMonth){
        String date=dayOfMonth+"/"+month+"/"+year;
        if(checkIn){
        txtCheckIn.setText(date);
        checkIn=false;
        }
        else{
            txtCheckOut.setText(date);
            checkOut=false;
        }
    }



}
