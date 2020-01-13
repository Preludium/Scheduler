package pl.jansmi.scheduler.adapters.holders;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pl.jansmi.scheduler.R;

public class ArrangementListItemViewHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public TextView desc;
    public TextView tags;
    public ImageButton menuBtn;

    public ArrangementListItemViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.arrangement_listitem_title);
        desc = itemView.findViewById(R.id.arrangement_listitem_desc);
        tags = itemView.findViewById(R.id.arrangement_listitem_tags);
        menuBtn = itemView.findViewById(R.id.arrangement_listitem_menu);
    }
}
