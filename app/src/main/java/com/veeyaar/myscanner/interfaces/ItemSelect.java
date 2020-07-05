package com.veeyaar.myscanner.interfaces;

import com.veeyaar.myscanner.model.ItemModel;
import com.veeyaar.myscanner.model.db.Product;

public interface ItemSelect {
    void onSelectItem(Product value);
}
