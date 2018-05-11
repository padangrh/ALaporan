package com.christinehakimideapark.chip.laporan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import static android.util.Base64.*;

public class list_Hasil extends AppCompatActivity {

    private ListView lvResult;
    private TextView txtJudul;
    private String JSON_STRING;

    private String inSource;
    private String tglDari;
    private String tglSampai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__hasil);
        lvResult = (ListView) findViewById(R.id.lv_Result);
        txtJudul = (TextView) findViewById(R.id.txtJudul);
        Intent iIntent = getIntent();

        inSource = iIntent.getStringExtra(konfigurasi.INSOURCE);
        tglDari = iIntent.getStringExtra(konfigurasi.TGL1);
        tglSampai = iIntent.getStringExtra(konfigurasi.TGL2);

        txtJudul.setText(inSource);

        getJSON();
    }

    private void showResult(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String Source = jo.getString(konfigurasi.TAG_SOURCE);
                String Tanggal = jo.getString(konfigurasi.TAG_TANGGAL);
                String Total = jo.getString(konfigurasi.TAG_TOTAL);
                Long vTotal =  Long.parseLong(Total);
                //DecimalFormat formatter = new Dec imalFormat("###,###,###,###");

                HashMap<String, String> Hasil = new HashMap<>();
                Hasil.put(konfigurasi.TAG_SOURCE, Source);
                Hasil.put(konfigurasi.TAG_TOTAL, NumberFormat.getNumberInstance(Locale.getDefault()).format(vTotal));
                list.add(Hasil);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                list_Hasil.this, list, R.layout.list_item,
                new String[]{konfigurasi.TAG_SOURCE,konfigurasi.TAG_TOTAL},
                new int[]{R.id.Source,R.id.Total});

        lvResult.setAdapter(adapter);
    }


    private void getJSON() {
        class GetJSON extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute(){
                super.onPreExecute();
                loading = ProgressDialog.show(list_Hasil.this,"Mengambil Data","Mohon Tunggu");
            }

            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showResult();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = "";
// Sending side
                //txt_Username.setText(new String (encodeValue));
                //byte[] data = tglDari.getBytes("UTF-8");
                //String base64 = Base64.encodeToString(data, Base64.DEFAULT);
                //String b64 = Base64.encodeToString(tglDari.getBytes(),Base64.DEFAULT);
                byte[] encodeValue = Base64.encode(tglDari.getBytes(), Base64.DEFAULT);
                String B641 = new String(encodeValue).replace("\n","");
                encodeValue = Base64.encode(tglSampai.getBytes(),Base64.DEFAULT);
                String B642 = new String (encodeValue).replace("\n","");

                if (inSource.equals("Semua")){
                // Sending side
                    s = rh.sendGetRequest(konfigurasi.URL_GET_ALL.concat("?tanggal1=").concat(B641).concat("&tanggal2=").concat(B642));
                    //s = rh.sendGetRequest(konfigurasi.URL_GET_ALL);
                    //s = rh.sendGetRequest(konfigurasi.URL_GET_ALL.concat("?tanggal1=").concat(tglDari).concat("&tanggal2=").concat(tglSampai));
                }else{
                    s = rh.sendGetRequest(konfigurasi.URL_GET_DIV.concat("?inSource=").concat(inSource).concat("&tanggal1=").concat(B641).concat("&tanggal2=").concat(B642));
                }

                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }
}
