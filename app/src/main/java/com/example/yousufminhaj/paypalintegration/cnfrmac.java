package com.example.yousufminhaj.paypalintegration;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class cnfrmac extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cnfrmac);
        Intent i = getIntent();

        try {
            JSONObject jsondetails = new JSONObject(i.getStringExtra("Pay Details"));
        } catch (JSONException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    private void showdetails(JSONObject jsd, String pa) throws JSONException {
        TextView viewid = (TextView) findViewById(R.id.pi);
        TextView viewstatus = (TextView) findViewById(R.id.ps);
        TextView viewamount = (TextView) findViewById(R.id.pa);

        viewid.setText(jsd.getString("id"));
        viewstatus.setText(jsd.getString("state"));
        viewamount.setText(pa + " USD");

    }
}
