package com.christinehakimideapark.chip.laporan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.util.Base64.*;


public class LayarAwal extends Activity {
    Button btn_Login, btn_Cancel;
    EditText txt_Username, txt_Password;

    TextView lbl_Result;
    int counter = 3;
    public static final String User_Name = "com.christinehakimideapark.chip.laporan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_awal);

        btn_Login = (Button)findViewById(R.id.btn_Login);
        txt_Username = (EditText)findViewById(R.id.txtUsername);
        txt_Password = (EditText)findViewById(R.id.txtPassword);

        btn_Cancel = (Button)findViewById(R.id.btn_Cancel);
        lbl_Result = (TextView)findViewById(R.id.lbl_Result);
        lbl_Result.setVisibility(View.GONE);

        btn_Login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(txt_Username.getText().toString().equals("admin") &&
                        txt_Password.getText().toString().equals("chipabcd")) {

                    txt_Password.setText("");
                    Toast.makeText(getApplicationContext(),"Redirecting...",Toast.LENGTH_SHORT).show();

                    Intent nIntent = new Intent(LayarAwal.this  , pilihan.class);
                    nIntent.putExtra(User_Name,txt_Username.getText().toString());
                    startActivity(nIntent);
                }else{
                    Toast.makeText(getApplicationContext(), "Password Salah",Toast.LENGTH_SHORT).show();

                    lbl_Result.setVisibility(View.VISIBLE);
                    //lbl_Result.setBackgroundColor(Color.RED);
                    counter--;
                    lbl_Result.setText(Integer.toString(counter));

                    if (counter == 0) {
                        btn_Login.setEnabled(false);
                    }
                }
            }
        });

        btn_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
}