package com.veeyaar.myscanner.view.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.veeyaar.myscanner.interfaces.ItemScanCallback;
import me.dm7.barcodescanner.zbar.BarcodeFormat;
import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class QrScannerFragment extends Fragment implements ZBarScannerView.ResultHandler {

  private ZBarScannerView mScannerView;
  private ItemScanCallback mInterface;
  private String TAG = "QrScannerFragment";

  public QrScannerFragment(ItemScanCallback mInterface) {
    this.mInterface = mInterface;
  }

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    mScannerView = new ZBarScannerView(getActivity());
    return mScannerView;
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

  @Override public void handleResult(Result rawResult) {
    if (rawResult.getBarcodeFormat().getName().equals(BarcodeFormat.QRCODE.getName())) {

      if (mInterface.onScanCompleted(rawResult.getContents())) {
        //freezing camera preview for two seconds
        Log.d(TAG, "handleResult: freezeMethodCalled");
        freezeForTwoSeconds();
      } else {
        Log.d(TAG, "handleResult: falseMethodCalled");
        mScannerView.stopCamera();
      }
    } else {
      mScannerView.resumeCameraPreview(this);
    }
  }

  private void resumeCamera() {
    mScannerView.resumeCameraPreview(this);
  }

  private void stopCamera() {
    mScannerView.stopCameraPreview();
  }

  private void freezeForTwoSeconds() {
    Log.d(TAG, "method In");
    stopCamera();
    new Handler().postDelayed(new Runnable() {
      @Override public void run() {
        Log.d(TAG, "resumeCameraCalled");
        resumeCamera();
      }
    }, 2000);
  }
}
