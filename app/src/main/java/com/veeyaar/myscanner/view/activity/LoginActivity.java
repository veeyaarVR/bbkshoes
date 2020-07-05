package com.veeyaar.myscanner.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.veeyaar.myscanner.R;
import com.veeyaar.myscanner.model.SessionResponse;
import com.veeyaar.myscanner.model.db.Entry;
import com.veeyaar.myscanner.presenter.LoginPresenter;
import com.veeyaar.myscanner.utility.Constants;
import com.veeyaar.myscanner.utility.EntrySorter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class LoginActivity extends BaseActivity implements LoginPresenter.LoginInterface {

  @BindView(R.id.edit_username)
  EditText editUsername;
  @BindView(R.id.edt_password)
  EditText edtPassword;
  @BindView(R.id.btn_submit)
  Button btnSubmit;
  @BindView(R.id.progress)
  ProgressBar progress;
  Gson GSON;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    ButterKnife.bind(this);
    hideLoader();
    GSON = new GsonBuilder().setPrettyPrinting().create();

    btnSubmit.setOnClickListener(view -> {
      setUpList();

      if (validationCheck()) {
        loginWithCred();
      }
    });

    //todo: remove this after testing
    //Intent intent = new Intent(this, MainActivity.class);
    //startActivity(intent);
    //finish();
  }

  private void loginWithCred() {
    showLoader();
    LoginPresenter loginPresenter = new LoginPresenter(this);
    loginPresenter.doSessionLogin(editUsername.getText().toString(),
        edtPassword.getText().toString());
  }

  private void setUpList() {
    List<Entry> productList = new ArrayList<>();
    Entry entry = new Entry("2", "11-aa", "bb", 0);
    Entry entry2 = new Entry("5", "a-14a", "bbaa", 0);
    Entry entry1 = new Entry("4", "aaa", "bba", 0);
    productList.add(entry);
    productList.add(entry1);
    productList.add(entry2);
    productList.add(entry);
    productList.add(entry2);
    productList.add(entry1);
    Log.d("login", "setUpList: " + GSON.toJson(productList));

    EntrySorter jobCandidateSorter = new EntrySorter(productList);
    List<Entry> sortedJobCandidate = jobCandidateSorter.getSortedEntries();

    HashMap<String, List<Entry>> hashMap = new HashMap<String, List<Entry>>();
    for (Entry custEntry : sortedJobCandidate) {
      String locId = custEntry.getLocationCode();
      if (!hashMap.containsKey(locId)) {
        List<Entry> entries = new ArrayList<>();
        entries.add(custEntry);
        hashMap.put(locId, entries);
      } else {
        hashMap.get(locId).add(entry);
      }
    }
    Log.d("login", "finalList: " + hashMap.keySet().size());

    List<Entry> superFinal = new ArrayList<>();
    for(String key : hashMap.keySet()){
      Log.d("login", "keyName:  " +key);
      Log.d("login", GSON.toJson(Objects.requireNonNull(hashMap.get(key))));
      int init = 0;
      for(Entry finalEntry : Objects.requireNonNull(hashMap.get(key))){

        finalEntry.setProductId(init);
        init++;
        superFinal.add(finalEntry);
      }
    }

    Log.d("login", "finalList: " + GSON.toJson(superFinal));


  }

  private boolean validationCheck() {
    if (editUsername.getText().length() > 0) {
      if (edtPassword.getText().length() > 0) {
        return true;
      } else {
        Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();
        return false;
      }
    } else {
      Toast.makeText(this, "Enter username", Toast.LENGTH_SHORT).show();
      return false;
    }
  }

  @Override
  public void loginSuccess(SessionResponse response) {
    hideLoader();
    if (response.sessionId != null) {
      sharedHelper.storeValue(Constants.SessionIdKey, response.sessionId);
      Intent intent = new Intent(this, MainActivity.class);
      startActivity(intent);
      finish();
    }
  }

  @Override
  public void loginFailed(String error) {
    hideLoader();
    Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
  }

  void showLoader() {
    progress.setVisibility(View.VISIBLE);
  }

  void hideLoader() {
    progress.setVisibility(View.GONE);
  }
}
