package com.veeyaar.myscanner.view.bottomSheets;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.veeyaar.myscanner.R;
import com.veeyaar.myscanner.view.activity.SimpleScannerActivity;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;

public class EntrySheet extends BottomSheetDialogFragment implements View.OnClickListener {

  private Button btnLocation;
  private TextView locationCode;
  private Button btnEntry;
  private TextView entryCode;
  private EditText edtQuantity;
  private Button btnClear;
  private Button btnSave;
  private Boolean isLocation = false;

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.activity_detail, container, false);
    return view;
  }

  @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    btnLocation = view.findViewById(R.id.btnLocation);
    locationCode = view.findViewById(R.id.locationCode);
    btnEntry = view.findViewById(R.id.btnEntry);
    entryCode = view.findViewById(R.id.entryCode);
    edtQuantity = view.findViewById(R.id.edtQuantity);
    btnClear = view.findViewById(R.id.btn_clear);
    btnSave = view.findViewById(R.id.btn_save);

    btnLocation.setOnClickListener(this);
    btnEntry.setOnClickListener(this);
    btnSave.setOnClickListener(this);
    btnClear.setOnClickListener(this);
  }

  @Override public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btnLocation: {
        isLocation = true;
        goToScanScreen();
      }
      break;
      case R.id.btnEntry: {
        isLocation = false;
        goToScanScreen();
      }
      break;
      case R.id.btn_save: {
        goToPrevScreen();
        Toast.makeText(getActivity(), "Entry Saved", Toast.LENGTH_SHORT).show();
      }
      break;
      case R.id.btn_clear: {
        clearData();
      }
      break;
    }
  }

  private void goToScanScreen() {
    Intent intent = new Intent(getActivity(), SimpleScannerActivity.class);
    startActivityForResult(intent, 1);
  }

  private void goToPrevScreen() {
    dismiss();
  }

  private void clearData() {
    locationCode.setVisibility(View.GONE);
    btnLocation.setVisibility(View.VISIBLE);
    entryCode.setVisibility(View.GONE);
    btnEntry.setVisibility(View.VISIBLE);
    edtQuantity.setText("");
  }

  @Override public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == 1) {
      if (resultCode == RESULT_OK) {
        assert data != null;
        String scannedCode = Objects.requireNonNull(data.getExtras()).getString("SCANNED_CODE");
        if (isLocation) {
          locationCode.setText(scannedCode);
          locationCode.setVisibility(View.VISIBLE);
          btnLocation.setVisibility(View.GONE);
        } else {
          entryCode.setText(scannedCode);
          entryCode.setVisibility(View.VISIBLE);
          btnEntry.setVisibility(View.GONE);
        }
      }
    }
  }
}

