package com.christinehakimideapark.chip.laporan;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import static android.util.Base64.DEFAULT;
import static android.util.Base64.encodeToString;

public class pilihan extends Activity {

    Calendar kalender ;
    EditText txtDariv, txtSampaiv;
    DatePickerDialog tanggal;
    Button btn_Submit;
    Spinner spinnerDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilihan);

        btn_Submit = (Button) findViewById(R.id.btnSubmit);
        txtDariv = (EditText) findViewById(R.id.txtDari);
        txtSampaiv = (EditText)findViewById(R.id.txtSampai);
        kalender = Calendar.getInstance();
        spinnerDB = (Spinner) findViewById(R.id.spinnerDB);


        /*        DatePickerDialog.OnShowListener tanggal = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                kalender.set(Calendar.YEAR,year);
                kalender.set(Calendar.MONTH,month);
                kalender.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                String myFormat = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.JAPAN);

                txtDari.setText(sdf.format(kalender.getTime()));
            }
        };

        txtDari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(pilihan.this,tanggal,kalender.get(Calendar.YEAR),kalender.get(Calendar.MONTH),kalender.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
*/

        txtDariv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar kldr = Calendar.getInstance();
                int mYear = kldr.get(Calendar.YEAR);
                int mMonth = kldr.get(Calendar.MONTH);
                int mDay = kldr.get(Calendar.DAY_OF_MONTH);

                tanggal = new DatePickerDialog(pilihan.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txtDariv.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, mYear, mMonth, mDay);
                tanggal.show();
            }
        });

        txtSampaiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar kldr = Calendar.getInstance();
                int mYear = kldr.get(Calendar.YEAR);
                int mMonth = kldr.get(Calendar.MONTH);
                int mDay = kldr.get(Calendar.DAY_OF_MONTH);

                tanggal = new DatePickerDialog(pilihan.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txtSampaiv.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, mYear, mMonth, mDay);
                tanggal.show();
            }
        });

        Intent nIntent = getIntent();
        String katax = nIntent.getStringExtra(LayarAwal.User_Name);

        TextView label1 = findViewById(R.id.label1);
        label1.setText(katax);

        btn_Submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();

                Intent oIntent;

                oIntent = new Intent(pilihan.this, list_Hasil.class);
                String inSource = String.valueOf(spinnerDB.getSelectedItem());
                oIntent.putExtra(konfigurasi.INSOURCE,inSource);
                oIntent.putExtra(konfigurasi.TGL1,txtDariv.getText().toString());
                oIntent.putExtra(konfigurasi.TGL2,txtSampaiv.getText().toString());
                startActivity(oIntent);


            }
        });
    }
}
