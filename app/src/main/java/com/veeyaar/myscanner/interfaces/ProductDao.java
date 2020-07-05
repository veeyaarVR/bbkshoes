package com.veeyaar.myscanner.interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import com.veeyaar.myscanner.model.db.Entry;
import com.veeyaar.myscanner.model.db.Product;
import com.veeyaar.myscanner.model.db.ProductWithEntries;
import io.reactivex.Completable;
import io.reactivex.Observable;
import java.util.List;

@Dao
public interface ProductDao {

  @Query("SELECT * FROM PRODUCTS")
  Observable<List<Product>> getProductsList();

  @Insert()
  Completable insertProduct(Product product);

  @Query("DELETE FROM products")
  Completable nukeTable();

  @Query("DELETE FROM entry")
  Completable deleteEntries();

  @Query("SELECT * FROM PRODUCTS WHERE id= :prodId")
  Observable<Product> getProductById(int prodId);

  @Update()
  Completable updateProduct(Product... product);

  @Transaction
  @Query("SELECT * FROM PRODUCTS")
  Observable<List<ProductWithEntries>> getProductsWithEntries();

  @Insert()
  Completable insertEntry(Entry entry);

  @Delete()
  Completable deleteEntry(Entry entry);
}
