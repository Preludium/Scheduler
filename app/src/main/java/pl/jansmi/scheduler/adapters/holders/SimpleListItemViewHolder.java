package pl.jansmi.scheduler.adapters.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pl.jansmi.scheduler.R;

public class SimpleListItemViewHolder extends RecyclerView.ViewHolder {

    public TextView title;

    public SimpleListItemViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.simple_select_listitem_title);
    }

}