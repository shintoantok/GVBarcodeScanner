package com.bookbook.research.gvbarcodescanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.api.CommonStatusCodes;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView barcodeValue;

    private static final int RC_BARCODE_CAPTURE = 9001;
    private static final String TAG = "BarcodeMain";
    public static final String BarcodeObject = "Barcode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        barcodeValue = (TextView)findViewById(R.id.barcode_value);

        findViewById(R.id.read_barcode).setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, BarcodeScanner.class);


        startActivityForResult(intent, RC_BARCODE_CAPTURE);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == RC_BARCODE_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    String barcodes = data.getExtras().getString(BarcodeObject);
                    barcodeValue.setText(barcodes);
                    Log.d(TAG, "Barcode read: " + barcodes);

                }else {
                    Log.d(TAG, "No barcode captured, intent data is null");
                }
            }else{

            }
        }else {
            super.onActivityResult(requestCode, resultCode, data);

        }
    }
}
