package com.veeyaar.myscanner.interfaces;

import com.veeyaar.myscanner.model.db.Entry;

public interface DeleteEntryCallback {

  void deleteEntry(Entry entry);
}
