package com.example.yousufminhaj.paypalintegration;

import android.app.Activity;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity  {
    Button pay;
    EditText amount;
    private String pa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
       // Intent i = new Intent(this, PayPalService.class);
        //startService(i);

    }

    private void init() {
        pay = (Button) findViewById(R.id.pay);
        amount = (EditText) findViewById(R.id.editText);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getpayment();
            }
        });
    }
    public  static  final int PAYPAL_REQUEST_CODE = 123;

    private static PayPalConfiguration config = new PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_SANDBOX).clientId(PayPalConfig.PAYPAL_CLIENT_ID);
    public void getpayment()
    {
        pa = amount.getText().toString();
        PayPalPayment payment = new PayPalPayment(new BigDecimal(String.valueOf(pa)),"USD","Simplified Coding Fee",PayPalPayment.PAYMENT_INTENT_SALE);
        Intent in = new Intent(this , PaymentActivity.class);
        in.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config);
        in.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
        startActivityForResult(in,PAYPAL_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PAYPAL_REQUEST_CODE)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                PaymentConfirmation cnfrm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if(cnfrm!= null)
                {
                    try {
                        String PaDetails = cnfrm.toJSONObject().toString(4);
                        Log.i("pa",PaDetails);
                        startActivity(new Intent(this, cnfrmac.class).putExtra("paymentDetails",PaDetails).putExtra("paymentAmount",pa));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }
            else if (resultCode==Activity.RESULT_CANCELED)
            {
                Log.i("paymentEx","User Canclld");

            }
            else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID)
            {
                Log.i("Paymentex", "invalid payment submitted");
            }
        }
      //  super.onActivityResult(requestCode, resultCode, data);
    }
}




