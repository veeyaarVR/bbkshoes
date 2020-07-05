package com.veeyaar.myscanner.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.veeyaar.myscanner.R;
import com.veeyaar.myscanner.interfaces.DeleteEntryCallback;
import com.veeyaar.myscanner.interfaces.ItemSelect;
import com.veeyaar.myscanner.model.db.Entry;
import com.veeyaar.myscanner.model.db.ProductWithEntries;
import java.util.List;

public class ObjectListAdapter extends RecyclerView.Adapter<ObjectListAdapter.MyViewHolder> {

  private Activity activity;
  private List<ProductWithEntries> itemList;
  private Context applicationContext;
  private ItemSelect itemSelectInterface;
  private DeleteEntryCallback deleteEntryCallback;

  public ObjectListAdapter(Activity activity, List<ProductWithEntries> itemList,
      Context applicationContext, ItemSelect itemSelectInterface,
      DeleteEntryCallback deleteEntryCallback) {
    this.activity = activity;
    this.itemList = itemList;
    this.applicationContext = applicationContext;
    this.itemSelectInterface = itemSelectInterface;
    this.deleteEntryCallback = deleteEntryCallback;
  }

  @NonNull
  @Override
  public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(activity).inflate(R.layout.adapter_list_content, parent, false);
    return new MyViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    holder.txtItemName.setText(itemList.get(position).getProduct().getItemName());
    holder.txtItemCode.setText(itemList.get(position).getProduct().getItemCode());
    holder.txtTotalQuant.setText(itemList.get(position).getProduct().getItemQuant());
    String remainingQuant = getRemainingQuant(itemList.get(position).getEntryList(),itemList.get(position).getProduct().getItemQuant());
    holder.txtRemainQuant.setText(remainingQuant);
    holder.entryRecy.setAdapter(
        new EntryAdapter(activity, itemList.get(position).getEntryList(), deleteEntryCallback));
    if (itemList.get(position).getEntryList().size() > 0) {
      holder.entryHeader.setVisibility(View.VISIBLE);
    } else {
      holder.entryHeader.setVisibility(View.GONE);
    }
    holder.itemView.setOnClickListener(view -> {
      itemSelectInterface.onSelectItem(itemList.get(position).getProduct());
    });
  }

  private String getRemainingQuant(List<Entry> entryList, String totalQuant) {
    int quantity = Integer.parseInt(totalQuant);
    double output = 0.0;
    for (Entry value : entryList) {
      output = output + Double.parseDouble(value.getQuantity());
    }
    return String.valueOf(quantity - (Math.round(output)));
  }

  @Override
  public int getItemCount() {
    return itemList.size();
  }

  static class MyViewHolder extends RecyclerView.ViewHolder {

    TextView txtItemName;
    TextView txtItemCode;
    TextView txtTotalQuant;
    TextView txtRemainQuant;
    RecyclerView entryRecy;
    LinearLayout entryHeader;

    MyViewHolder(@NonNull View itemView) {
      super(itemView);
      txtItemName = itemView.findViewById(R.id.txt_item_name);
      txtItemCode = itemView.findViewById(R.id.txt_item_code);
      entryRecy = itemView.findViewById(R.id.recy_entry_list);
      entryHeader = itemView.findViewById(R.id.entry_header);
      txtTotalQuant = itemView.findViewById(R.id.txt_tot_quant);
      txtRemainQuant = itemView.findViewById(R.id.txt_remain_quant);
    }
  }
}
