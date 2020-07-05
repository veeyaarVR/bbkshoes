package com.veeyaar.myscanner.utility;

import com.veeyaar.myscanner.model.db.Entry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EntrySorter {

  List<Entry> entries = new ArrayList<>();

  public EntrySorter(List<Entry> entries) {
    this.entries = entries;
  }

  public List<Entry> getSortedEntries() {
    Collections.sort(entries);
    return entries;
  }
}
