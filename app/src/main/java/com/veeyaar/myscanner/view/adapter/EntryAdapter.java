package com.veeyaar.myscanner.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.veeyaar.myscanner.R;
import com.veeyaar.myscanner.interfaces.DeleteEntryCallback;
import com.veeyaar.myscanner.model.db.Entry;
import java.util.List;

public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.MyViewHolder>{

  private Activity activity;
  private List<Entry> entryList;
  private DeleteEntryCallback deleteEntryCallback;

  public EntryAdapter(Activity activity,
      List<Entry> entryList,DeleteEntryCallback deleteEntryCallback) {
    this.activity = activity;
    this.entryList = entryList;
    this.deleteEntryCallback = deleteEntryCallback;
  }

  @NonNull @Override
  public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(activity).inflate(R.layout.adapter_entry_content, parent, false);
    return new EntryAdapter.MyViewHolder(view);  }

  @Override
  public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      holder.txtLocCode.setText(entryList.get(position).getLocationCode());
      holder.txtProdCode.setText(entryList.get(position).getProductCode());
      holder.txtQuantity.setText(entryList.get(position).getQuantity());
      holder.btnDelete.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
          deleteEntryCallback.deleteEntry(entryList.get(position));
        }
      });
  }

  @Override public int getItemCount() {
    return entryList.size();
  }

  static class MyViewHolder extends RecyclerView.ViewHolder {

    TextView txtLocCode;
    TextView txtProdCode;
    TextView txtQuantity;
    FrameLayout btnDelete;

    MyViewHolder(@NonNull View itemView) {
      super(itemView);
      txtLocCode = itemView.findViewById(R.id.tv_location_code);
      txtProdCode = itemView.findViewById(R.id.tv_product_code);
      txtQuantity = itemView.findViewById(R.id.tv_quantity);
      btnDelete = itemView.findViewById(R.id.btn_delete);
    }
  }
}
