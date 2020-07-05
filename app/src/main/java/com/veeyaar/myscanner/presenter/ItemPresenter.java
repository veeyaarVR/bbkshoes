package com.veeyaar.myscanner.presenter;

import android.app.Activity;
import android.util.Log;
import com.veeyaar.myscanner.model.FirstReturnResponse;
import com.veeyaar.myscanner.model.HeaderData;
import com.veeyaar.myscanner.model.ItemModel;
import com.veeyaar.myscanner.model.SecondReturn;
import com.veeyaar.myscanner.model.SessionLogin;
import com.veeyaar.myscanner.model.SessionResponse;
import com.veeyaar.myscanner.model.db.Entry;
import com.veeyaar.myscanner.model.db.Product;
import com.veeyaar.myscanner.model.db.ProductWithEntries;
import com.veeyaar.myscanner.network.ApiClient;
import com.veeyaar.myscanner.network.ApiInterface;
import com.veeyaar.myscanner.utility.ProductDatabase;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemPresenter {

  private ItemInterface mInterface;
  private ApiInterface apiInterface;
  private Activity activity;
  private CompositeDisposable disposable;
  private String TAG = "ItemP";
  private ProductDatabase database;

  public ItemPresenter(ItemInterface mInterface, Activity activity) {
    this.mInterface = mInterface;
    this.activity = activity;
    apiInterface = ApiClient.getClient().create(ApiInterface.class);
    disposable = new CompositeDisposable();
    database = ProductDatabase.getInstance(activity);
  }

  public void getItemList(String sessionId, String code) {
    clearDataBase();
    String cookies = "B1SESSION=" + sessionId + ";CompanyDB=BBKTEST";
    Call<ItemModel> call = apiInterface.getItems(cookies, code);
    call.enqueue(new Callback<ItemModel>() {
      @Override
      public void onResponse(@NotNull Call<ItemModel> call, @NotNull Response<ItemModel> response) {
        if (response.body() != null && response.isSuccessful()) {
          mInterface.getListFromApi(response.body().getValue());
          storeInDataBase(response.body().getValue());
        }
      }

      @Override
      public void onFailure(@NotNull Call<ItemModel> call, @NotNull Throwable t) {
        mInterface.getItemListFailure(t.getLocalizedMessage());
      }
    });
  }

  public void getHeaderContent(String sessionId, String code) {
    String cookies = "B1SESSION=" + sessionId + ";CompanyDB=BBKTEST";
    Call<HeaderData> call = apiInterface.getHeaderContent(cookies, code);
    call.enqueue(new Callback<HeaderData>() {
      @Override
      public void onResponse(@NotNull Call<HeaderData> call,
          @NotNull Response<HeaderData> response) {
        if (response.body() != null && response.isSuccessful()) {
          mInterface.fetchHeaderDataSuccess(response.body());
        }
      }

      @Override
      public void onFailure(@NotNull Call<HeaderData> call, @NotNull Throwable t) {
        mInterface.getItemListFailure(t.getLocalizedMessage());
      }
    });
  }

  private void clearDataBase() {
    disposable.clear();
    disposable.add(
        database.productDao().nukeTable().subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()).subscribe(() -> {
          Log.d(TAG, "clearDataBase: database cleared");
          deleteEntries();
        }, throwable -> {
          Log.d(TAG, "clearDataBase: failed" + throwable.getLocalizedMessage());
        }));
  }

  private void deleteEntries() {
    disposable.clear();
    disposable.add(
        database.productDao().deleteEntries().subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()).subscribe(() -> {
          Log.d(TAG, "clearDataBase: database cleared");
        }, throwable -> {
          Log.d(TAG, "clearDataBase: failed" + throwable.getLocalizedMessage());
        }));
  }

  private void storeInDataBase(List<ItemModel.Value> productList) {
    ProductDatabase database = ProductDatabase.getInstance(activity);
    List<Entry> entryList = new ArrayList<>();
    for (ItemModel.Value value : productList) {
      Product product =
          new Product(value.getId(), value.getITEMCODE(), value.getITEMNAME(),
              getRoundedValue(value.getQUANTITY()), entryList);
      disposable.add(
          database.productDao().insertProduct(product).subscribeOn(Schedulers.io()).observeOn(
              AndroidSchedulers.mainThread()).subscribe(() -> {

          }, throwable -> {
            Log.d(TAG, "storeInDataBase: failed");
          }));
    }
    getNewList(false);
  }

  private String getRoundedValue(String input) {
    double dInput = Double.parseDouble(input);
    long output = Math.round(dInput);
    return String.valueOf(output);
  }

  private void getProductList() {
    disposable.clear();
    disposable.add(
        database.productDao().getProductsList().subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()).subscribe((productList) -> {
          Log.d("ItemPresenter", "getItemList: success" + productList.size());
          getNewList(false);
          //mInterface.getItemListSuccess(productList);
        }, throwable -> {
          Log.d("ItemPresenter", "getItemList: failed" + throwable.getLocalizedMessage());
        }));
  }

  public void getNewList(Boolean isLocalDbReq) {
    disposable.clear();
    disposable.add(
        database.productDao().getProductsWithEntries().subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()).subscribe((productList) -> {
          Log.d("ItemPresenter",
              "getProductsWithEntries: success" + productList.get(0).getEntryList().size());
          if (!isLocalDbReq) {
            mInterface.getItemListSuccess(productList);
          } else {
            mInterface.fetchLocalDbSuccess(productList);
          }
        }, throwable -> {
          Log.d("ItemPresenter",
              "getProductsWithEntries: failed" + throwable.getLocalizedMessage());
        }));
  }

  public void addNewEntry(Entry entry) {
    disposable.clear();
    disposable.add(
        database.productDao().insertEntry(entry).subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()).subscribe(() -> {
          Log.d(TAG, "addNewEntry: success");
          getNewList(false);
        }, throwable -> {
          Log.d(TAG, "addNewEntry: failed" + throwable.getLocalizedMessage());
        }));
  }

  private void updateProduct(Product product1) {
    disposable.clear();
    disposable.add(
        database.productDao().updateProduct(product1).subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()).subscribe(() -> {
          Log.d(TAG, "updateProduct: success");
          getProductList();
        }, throwable -> {
          Log.d(TAG, "updateProduct: failed");
        }));
  }

  public void deleteEntry(Entry entry) {
    disposable.clear();
    disposable.add(
        database.productDao().deleteEntry(entry).subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()).subscribe(() -> {
          Log.d(TAG, "updateProduct: success");
          getProductList();
        }, throwable -> {
          Log.d(TAG, "updateProduct: failed");
        }));
  }

  public void firstApiCall(FirstReturnResponse requestBody) {

    Call<SessionResponse> call =
        apiInterface.firstApi(requestBody);
    call.enqueue(new Callback<SessionResponse>() {
      @Override
      public void onResponse(@NotNull Call<SessionResponse> call,
          @NotNull Response<SessionResponse> response) {
        Log.d(TAG, "onResponse: success");
        if (response.body() != null && response.isSuccessful()) {
          mInterface.firstApiSuccess(response.body());
        } else {
          mInterface.getItemListFailure("Something went wrong");
        }
      }

      @Override
      public void onFailure(@NotNull Call<SessionResponse> call, @NotNull Throwable t) {
        Log.d(TAG, "onResponse: failed");
        mInterface.getItemListFailure(t.getLocalizedMessage());
      }
    });
  }

  public void secondApi(SecondReturn requestBody) {

    Call<SessionResponse> call =
        apiInterface.secondApi(requestBody);
    call.enqueue(new Callback<SessionResponse>() {
      @Override
      public void onResponse(@NotNull Call<SessionResponse> call,
          @NotNull Response<SessionResponse> response) {
        Log.d(TAG, "onResponse: success");
        if (response.body() != null && response.isSuccessful()) {
          mInterface.secondApiSuccess(response.body());
        } else {
          mInterface.getItemListFailure("Something went wrong!");
        }
      }

      @Override
      public void onFailure(@NotNull Call<SessionResponse> call, @NotNull Throwable t) {
        Log.d(TAG, "onResponse: failed");
        mInterface.getItemListFailure(t.getLocalizedMessage());
      }
    });
  }

  public interface ItemInterface {
    void getItemListSuccess(List<ProductWithEntries> itemList);

    void getListFromApi(List<ItemModel.Value> itemList);

    void getItemListFailure(String error);

    void fetchLocalDbSuccess(List<ProductWithEntries> itemList);

    void fetchHeaderDataSuccess(HeaderData data);

    void firstApiSuccess(SessionResponse body);

    void secondApiSuccess(SessionResponse body);
  }
}




