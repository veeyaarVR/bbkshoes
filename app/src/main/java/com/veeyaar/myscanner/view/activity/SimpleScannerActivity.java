package com.veeyaar.myscanner.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import me.dm7.barcodescanner.zbar.BarcodeFormat;
import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class SimpleScannerActivity extends BaseActivity implements ZBarScannerView.ResultHandler {

  private ZBarScannerView mScannerView;

  @Override
  public void onCreate(Bundle state) {
    super.onCreate(state);
    mScannerView = new ZBarScannerView(this);    // Programmatically initialize the scanner view
    setContentView(mScannerView);                // Set the scanner view as the content view
  }

  @Nullable @Override public View onCreateView(@NonNull String name, @NonNull Context context,
      @NonNull AttributeSet attrs) {
    return super.onCreateView(name, context, attrs);
  }

  @Override
  public void onResume() {
    super.onResume();
    mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
    mScannerView.startCamera();          // Start camera on resume
  }

  @Override
  public void onPause() {
    super.onPause();
    mScannerView.stopCamera();           // Stop camera on pause
  }

  @Override
  public void handleResult(Result rawResult) {
    String TAG = "SimpleScannerActivity";
    if (rawResult.getBarcodeFormat().getName().equals(BarcodeFormat.QRCODE.getName())) {
      Intent resultIntent = new Intent();
      resultIntent.putExtra("SCANNED_CODE", rawResult.getContents());
      setResult(Activity.RESULT_OK, resultIntent);
      finish();
      Log.v(TAG, rawResult.getContents()); // Prints scan results
    } else {
      mScannerView.resumeCameraPreview(this);
    }
    Log.v(TAG,
        rawResult.getBarcodeFormat().getName()); // Prints the scan format (qrcode, pdf417 etc.)
  }
}