package com.veeyaar.myscanner.utility;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.veeyaar.myscanner.interfaces.ProductDao;
import com.veeyaar.myscanner.model.db.Entry;
import com.veeyaar.myscanner.model.db.Product;

@Database(entities = {Product.class, Entry.class}, exportSchema = false, version = 3)
public abstract class ProductDatabase extends RoomDatabase {

  private static final String DB_NAME = "product_db";
  private static ProductDatabase instance;

  public static synchronized ProductDatabase getInstance(Context context) {
    if (instance == null) {
      instance =
          Room.databaseBuilder(context.getApplicationContext(), ProductDatabase.class, DB_NAME)
              .fallbackToDestructiveMigration()
              .build();
    }
    return instance;
  }

  public abstract ProductDao productDao();
}
