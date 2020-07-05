package com.veeyaar.myscanner.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.veeyaar.myscanner.R;
import com.veeyaar.myscanner.interfaces.DeleteEntryCallback;
import com.veeyaar.myscanner.interfaces.ItemSelect;
import com.veeyaar.myscanner.model.FirstReturnResponse;
import com.veeyaar.myscanner.model.HeaderData;
import com.veeyaar.myscanner.model.ItemModel;
import com.veeyaar.myscanner.model.SecondReturn;
import com.veeyaar.myscanner.model.SessionResponse;
import com.veeyaar.myscanner.model.db.Entry;
import com.veeyaar.myscanner.model.db.Product;
import com.veeyaar.myscanner.model.db.ProductWithEntries;
import com.veeyaar.myscanner.presenter.ItemPresenter;
import com.veeyaar.myscanner.utility.Constants;
import com.veeyaar.myscanner.view.adapter.ObjectListAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListActivity extends BaseActivity
    implements ItemPresenter.ItemInterface, ItemSelect, DeleteEntryCallback {

  private static final String TAG = "ListActivity";
  @BindView(R.id.recy_objects)
  RecyclerView recyObjects;
  @BindView(R.id.progressBar)
  ProgressBar progressBar;
  @BindView(R.id.btn_submit)
  Button btnSubmit;
  int ListActivityReqCode = 8;

  String scannedCode = "";
  @BindView(R.id.toolbar) Toolbar toolbar;

  HeaderData headerData = new HeaderData();
  List<ProductWithEntries> itemList = new ArrayList<>();
  List<ItemModel.Value> apiResponse = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list);
    ButterKnife.bind(this);
    Intent intent = getIntent();
    scannedCode = intent.getStringExtra("scanned_code");
    assert scannedCode != null;
    if (scannedCode.equals("911") || scannedCode.equals("912")) {
      toolbar.setTitle("Item Code : " + scannedCode);
      getItemList();
      getHeaderContent();
    } else {
      finish();
      Toast.makeText(activity, "Invalid code", Toast.LENGTH_SHORT).show();
    }
    btnSubmit.setVisibility(View.GONE);
    btnSubmit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        setUpFirstApi();
        Toast.makeText(activity, "Submit success", Toast.LENGTH_SHORT).show();
      }
    });
  }

  private void setUpSecondApi() {
    SecondReturn secondReturn = new SecondReturn();
    secondReturn.setuDocDate("2020-06-25");
    secondReturn.setuBaseNum(headerData.getValue().get(0).getDocNum());
    secondReturn.setuBaseEntry(headerData.getValue().get(0).getDocEntry());
    secondReturn.setuBaseSeries(headerData.getValue().get(0).getSeries());
    secondReturn.setuProcess(headerData.getValue().get(0).getProcess());
    secondReturn.setuCardCode(headerData.getValue().get(0).getCardCode());
    secondReturn.setuTarNum("2");
    secondReturn.setuTarEntry("2");

    List<SecondReturn.AISGDI1Collection> collectionList = new ArrayList<>();
    for(ProductWithEntries product : itemList){
      SecondReturn.AISGDI1Collection collection = new SecondReturn.AISGDI1Collection();
      int index = apiResponse.indexOf(product);
      ItemModel.Value value = apiResponse.get(index);
      collection.setuItemCode(value.getITEMCODE());
      collection.setuItemName(value.getITEMNAME());
      collection.setuColour(value.getColor());
      collection.setuActualQty(value.getQUANTITY());
      collection.setuQuantity(getSumCount(product));
      collection.setuUOM(value.getInvntryUom());
      collection.setuRMLineID(value.getLineId());
      collection.setuWhsCode(value.getWhsCode());
      collection.setuBranch(value.getBranch());
      collection.setuCostCenter(value.getCostCenter());
      collection.setuPBaseEntry(value.getpBaseEntry());
      collection.setuPBaseNum(value.getpBaseNum());
      collection.setuInstock(value.getInStock());
      collection.setuVendorCode(value.getVendorCode());
      collection.setuRemarks("");
      collection.setuHideQty("0.0");
      collection.setuSideQty("0.0");
    }
  }

  String getSumCount(ProductWithEntries product){
    double output = 0.0;
    for(Entry entry : product.getEntryList()){
      output = output + Double.parseDouble(entry.getQuantity());
    }
    return String.valueOf(output);
  }

  private void getHeaderContent() {
    ItemPresenter itemPresenter = new ItemPresenter(this, activity);
    String sessionId = sharedHelper.retrieveValue(Constants.SessionIdKey);
    itemPresenter.getHeaderContent(sessionId, scannedCode);
  }

  private void setUpFirstApi() {
    ItemPresenter itemPresenter = new ItemPresenter(this, activity);
    itemPresenter.getNewList(true);

    FirstReturnResponse firstReturnResponse = new FirstReturnResponse();
    firstReturnResponse.setDocType("dDocument_Items");
    firstReturnResponse.setbPLIDAssignedToInvoice(headerData.getValue().get(0).getFrmBranch());
    firstReturnResponse.setuBaseObject(headerData.getValue().get(0).getObject());
    firstReturnResponse.setuBaseNum(headerData.getValue().get(0).getDocNum());
    firstReturnResponse.setuBaseSeries(headerData.getValue().get(0).getSeries());
    firstReturnResponse.setuCardCode(headerData.getValue().get(0).getCardCode());
    firstReturnResponse.setComments("");

    List<FirstReturnResponse.DocumentLine> documentLineList = new ArrayList<>();
    for (ProductWithEntries product : itemList) {
      FirstReturnResponse.DocumentLine documentLine = new FirstReturnResponse.DocumentLine();
      documentLine.setItemCode(product.getProduct().getItemCode());
      int index = itemList.indexOf(product);
      ItemModel.Value value = apiResponse.get(index);
      documentLine.setBaseLine(0);
      documentLine.setQuantity(Double.parseDouble(getSumCount(product)));
      documentLine.setWarehouseCode(value.getWhsCode());
      documentLine.setuColour(value.getColor());
      documentLine.setuVendorCode(value.getVendorCode());
      documentLine.setuBranch(value.getBranch());
      documentLine.setuPBaseNum(value.getpBaseNum());
      documentLine.setuPBaseEntry(value.getpBaseNum());
      documentLine.setuRMLineID(value.getLineId());
      documentLine.setuActualQty(value.getQUANTITY());

      List<FirstReturnResponse.BatchNumber> batchNumberList = new ArrayList<>();
      for (Entry entry : updatedEntryList(product)) {
        FirstReturnResponse.BatchNumber batchNumber = new FirstReturnResponse.BatchNumber();
        batchNumber.setBatchNumber(entry.getProductCode());
        batchNumber.setQuantity(entry.getQuantity());
        batchNumberList.add(batchNumber);
      }
      documentLine.setBatchNumbers(batchNumberList);

      List<FirstReturnResponse.DocumentLinesBinAllocation> binAllocationList = new ArrayList<>();
      for (Entry entry : updatedCollection(product)) {
        FirstReturnResponse.DocumentLinesBinAllocation bin =
            new FirstReturnResponse.DocumentLinesBinAllocation();
        bin.setBinAbsEntry(entry.getLocationCode());
        bin.setQuantity(entry.getQuantity());
        bin.setSerialAndBatchNumbersBaseLine(entry.getProductId().toString());
        binAllocationList.add(bin);
      }

      documentLine.setDocumentLinesBinAllocations(binAllocationList);
      documentLineList.add(documentLine);
    }
    firstReturnResponse.setDocumentLines(documentLineList);
    Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    Log.d(TAG, "setUpFirstApi2: " + GSON.toJson(firstReturnResponse));
    itemPresenter.firstApiCall(firstReturnResponse);
  }

  List<Entry> updatedEntryList(ProductWithEntries product) {
    List<Entry> newEntryList = new ArrayList<>();
    Log.d(TAG, "updatedEntryList: " + product.getEntryList().size());
    for (Entry entry : product.getEntryList()) {
      if (newEntryList.size() > 0) {
        for (Entry freshEntry : newEntryList) {
          if (entry.getProductCode().equals(freshEntry.getProductCode())) {
            String oldQuant = freshEntry.getQuantity();
            int newQuant = Integer.parseInt(oldQuant) + Integer.parseInt(entry.getQuantity());
            freshEntry.setQuantity(Integer.toString(newQuant));
          } else {
            newEntryList.add(entry);
          }
        }
      } else {
        newEntryList.add(entry);
      }
    }
    Log.d(TAG, "newEntryList: " + newEntryList.toString());
    return newEntryList;
  }

  List<Entry> updatedCollection(ProductWithEntries product) {
    List<Entry> newEntryList = new ArrayList<>();
    Log.d(TAG, "updatedEntryList: " + product.getEntryList().size());
    for (Entry entry : product.getEntryList()) {
      entry.setProductId(0);
      newEntryList.add(entry);
    }
    Log.d(TAG, "newEntryList: " + newEntryList.toString());
    return newEntryList;
  }

  private void getItemList() {
    showLoader();
    ItemPresenter itemPresenter = new ItemPresenter(this, activity);
    String sessionId = sharedHelper.retrieveValue(Constants.SessionIdKey);
    itemPresenter.getItemList(sessionId, scannedCode);
  }

  private void setUpRecyclerView(List<ProductWithEntries> itemList) {
    btnSubmit.setVisibility(View.VISIBLE);
    ObjectListAdapter adapter =
        new ObjectListAdapter(this, itemList, getApplicationContext(), this, this);
    recyObjects.setAdapter(adapter);
  }

  @Override
  public void getItemListSuccess(List<ProductWithEntries> itemList) {
    hideLoader();
    this.itemList = itemList;
    setUpRecyclerView(itemList);
  }

  @Override public void getListFromApi(List<ItemModel.Value> itemList) {
    this.apiResponse = itemList;
  }

  @Override
  public void getItemListFailure(String error) {
    hideLoader();
    Toast.makeText(activity, "" + error, Toast.LENGTH_SHORT).show();
    Toast.makeText(activity, "Make sure you are connected to Internet!", Toast.LENGTH_SHORT).show();
  }

  @Override public void fetchLocalDbSuccess(List<ProductWithEntries> itemList) {
    this.itemList = itemList;
  }

  @Override public void fetchHeaderDataSuccess(HeaderData data) {
    headerData = data;
  }

  @Override public void firstApiSuccess(SessionResponse body) {
    setUpSecondApi();
  }

  @Override public void secondApiSuccess(SessionResponse body) {
    Toast.makeText(activity, "api call success", Toast.LENGTH_SHORT).show();
    finish();
  }

  private void showLoader() {
    progressBar.setVisibility(View.VISIBLE);
  }

  private void hideLoader() {
    progressBar.setVisibility(View.GONE);
  }

  @Override
  public void onSelectItem(Product value) {

    //ItemPresenter itemPresenter = new ItemPresenter(this, activity);
    //itemPresenter.addNewEntry(new Entry("1", "a", "b", value.getId()));

    Intent intent = new Intent(activity, EntryScannerActivity.class);
    intent.putExtra("product_id", value.getId());
    startActivityForResult(intent, ListActivityReqCode);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == RESULT_OK && requestCode == ListActivityReqCode) {
      assert data != null;
      Entry entry = (Entry) Objects.requireNonNull(data.getExtras()).getSerializable("ENTRY_DATA");

      Log.d("ListActivity", "onActivityResult: " + entry.toString());
      ItemPresenter itemPresenter = new ItemPresenter(this, activity);
      itemPresenter.addNewEntry(entry);
    }
  }

  @Override public void deleteEntry(Entry entry) {
    ItemPresenter itemPresenter = new ItemPresenter(this, activity);
    itemPresenter.deleteEntry(entry);
  }
}
