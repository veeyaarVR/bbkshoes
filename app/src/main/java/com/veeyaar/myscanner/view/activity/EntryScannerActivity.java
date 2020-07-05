package com.veeyaar.myscanner.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.veeyaar.myscanner.R;
import com.veeyaar.myscanner.interfaces.ItemScanCallback;
import com.veeyaar.myscanner.model.db.Entry;
import com.veeyaar.myscanner.view.fragments.QrScannerFragment;

public class EntryScannerActivity extends BaseActivity
    implements ItemScanCallback, View.OnClickListener {

  @BindView(R.id.fragment_loader) FrameLayout fragmentLoader;
  @BindView(R.id.tv_location_code) TextView tvLocationCode;
  @BindView(R.id.tv_product_code) TextView tvProductCode;
  @BindView(R.id.edtQuantity) EditText edtQuantity;
  @BindView(R.id.btn_cancel) Button btnCancel;
  @BindView(R.id.btn_submit) Button btnSubmit;
  Boolean isSecondScan = false;
  String locCode = "";
  String prodCode = "";
  String quantity = "";
  Integer productId;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_entry_scanner);
    Intent intent = getIntent();
    productId = intent.getIntExtra("product_id", 0);
    ButterKnife.bind(this);
    loadFragment();
    btnSubmit.setOnClickListener(this);
    btnCancel.setOnClickListener(this);
  }

  private void loadFragment() {
    QrScannerFragment fragment = new QrScannerFragment(this);
    getSupportFragmentManager().beginTransaction()
        .add(R.id.fragment_loader, fragment, "scanner_fragment")
        .commit();
  }

  @Override
  public boolean onScanCompleted(String scanCode) {
    if (!isSecondScan) {
      locCode = scanCode;
      tvLocationCode.setText("Scanned Location : " + scanCode);
      tvLocationCode.setTextColor(ContextCompat.getColor(this, R.color.green));
      tvLocationCode.setTextSize(16);
      isSecondScan = true;
      Toast.makeText(activity, "scanned code is " + scanCode, Toast.LENGTH_SHORT).show();
      return true;
    } else {
      prodCode = scanCode;
      tvProductCode.setText("Scanned Product : " + scanCode);
      tvProductCode.setTextColor(ContextCompat.getColor(this, R.color.green));
      tvProductCode.setTextSize(16);
      Toast.makeText(activity, "scanned code is " + scanCode, Toast.LENGTH_SHORT).show();
      return false;
    }
  }

  @Override public void onClick(View view) {
    if (view.getId() == R.id.btn_submit) {
      if (validation()) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("ENTRY_DATA",
            new Entry(edtQuantity.getText().toString(), locCode, prodCode, productId));
        setResult(RESULT_OK, resultIntent);
        finish();
      } else {
        Toast.makeText(activity, "Scan and enter quantity to save", Toast.LENGTH_SHORT).show();
      }
    } else if (view.getId() == R.id.btn_cancel) {
      Intent resultIntent = new Intent();
      setResult(RESULT_CANCELED, resultIntent);
      finish();
    }
  }

  private boolean validation() {
    if (!locCode.isEmpty() && !prodCode.isEmpty()) {
      if (edtQuantity.getText().toString().length() > 0) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }
}
