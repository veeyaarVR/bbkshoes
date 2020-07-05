package com.veeyaar.myscanner.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.veeyaar.myscanner.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btnLocation)
    Button btnLocation;
    @BindView(R.id.locationCode)
    TextView locationCode;
    @BindView(R.id.btnEntry)
    Button btnEntry;
    @BindView(R.id.entryCode)
    TextView entryCode;
    @BindView(R.id.edtQuantity)
    EditText edtQuantity;
    @BindView(R.id.btn_clear)
    Button btnClear;
    @BindView(R.id.btn_save)
    Button btnSave;

    Boolean isLocation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        btnLocation.setOnClickListener(this);
        btnEntry.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnClear.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
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
                Toast.makeText(this, "Entry Saved", Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.btn_clear: {
                clearData();
            }
            break;

        }
    }

    private void goToPrevScreen() {
        finish();
    }

    private void clearData() {
        locationCode.setVisibility(View.GONE);
        btnLocation.setVisibility(View.VISIBLE);
        entryCode.setVisibility(View.GONE);
        btnEntry.setVisibility(View.VISIBLE);
        edtQuantity.setText("");
    }

    void goToScanScreen() {
        Intent intent = new Intent(this, SimpleScannerActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
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
