package pl.jansmi.scheduler.adapters.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pl.jansmi.scheduler.R;

public class MainWithoutBinListItemViewHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public TextView desc;

    public MainWithoutBinListItemViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.main_without_bin_listitem_title);
        desc = itemView.findViewById(R.id.main_without_bin_listitem_desc);
    }
}