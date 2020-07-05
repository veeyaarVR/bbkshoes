package com.veeyaar.myscanner.view.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.veeyaar.myscanner.R;
import java.util.Objects;

public class MainActivity extends BaseActivity implements View.OnClickListener {

  @BindView(R.id.btn_scan)
  Button btnScan;
  BottomSheetBehavior bottomSheetBehavior;
  @BindView(R.id.btn_cancel)
  Button btnCancel;
  @BindView(R.id.btn_proceed)
  Button btnProceed;
  @BindView(R.id.txt_scanned_code)
  TextView txtScannedCode;

  Boolean isCameraPermissionEnabled = false;
  @BindView(R.id.btn_report) Button btnReport;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    requestCamPermission();
    btnScan.setOnClickListener(this);
    btnProceed.setOnClickListener(this);
    btnCancel.setOnClickListener(this);
    btnReport.setOnClickListener(this);

    //setting up bottomSheet
    LinearLayout llBottomSheet = (LinearLayout) findViewById(R.id.bottom_sheet);
    bottomSheetBehavior = BottomSheetBehavior.from(llBottomSheet);
    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
  }

  private void requestCamPermission() {
    if (ContextCompat.checkSelfPermission(this,
        Manifest.permission.CAMERA)
        != PackageManager.PERMISSION_GRANTED) {
      // Permission is not granted
      ActivityCompat
          .requestPermissions(
              MainActivity.this,
              new String[] {Manifest.permission.CAMERA},
              2);
    } else {
      isCameraPermissionEnabled = true;
    }
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btn_scan: {
        //goToScanScreen();
        goToListScreen();
      }
      break;
      case R.id.btn_proceed: {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        goToListScreen();
      }
      break;
      case R.id.btn_cancel: {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
      }
      case R.id.btn_report: {
        Toast.makeText(activity, "This feature is under progress", Toast.LENGTH_SHORT).show();
      }
      break;
    }
  }

  private void goToScanScreen() {
    if (isCameraPermissionEnabled) {
      Intent intent = new Intent(this, SimpleScannerActivity.class);
      startActivityForResult(intent, 1);
    } else {
      requestCamPermission();
    }
  }

  private void goToListScreen() {
    String code = txtScannedCode.getText().toString();
    code = "911";
    Intent intent = new Intent(this, ListActivity.class);
    intent.putExtra("scanned_code", code);
    startActivity(intent);
  }

  private void showBottomSheet(String scannedCode) {
    txtScannedCode.setText(scannedCode);
    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == 1) {
      if (resultCode == RESULT_OK) {
        assert data != null;
        String scannedCode = Objects.requireNonNull(data.getExtras()).getString("SCANNED_CODE");
        showBottomSheet(scannedCode);
      }
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode == 2) {
      if (grantResults.length > 0
          && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        isCameraPermissionEnabled = true;
      } else {
        isCameraPermissionEnabled = false;
      }
    }
  }
}
